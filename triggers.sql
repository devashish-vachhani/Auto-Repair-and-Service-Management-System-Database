-- create or replace TRIGGER MECHANIC_TIMEOFF
--     AFTER UPDATE ON MECHANICSCHEDULE
-- FOR EACH ROW
-- BEGIN
--     IF :OLD.AVAILABLE <> 'TIMEOFF' AND :NEW.AVAILABLE = 'TIMEOFF' THEN
--         IF :NEW.SLOT_ID BETWEEN 1 AND 60 THEN
--             UPDATE MECHANIC M
--             SET M.HOURS_WORKED_WEEK1 = M.HOURS_WORKED_WEEK1-1
--             WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
--         ELSE
--             IF :NEW.SLOT_ID BETWEEN 61 AND 120 THEN
--                 UPDATE MECHANIC M
--                 SET M.HOURS_WORKED_WEEK2 = M.HOURS_WORKED_WEEK2-1
--                 WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
--             ELSE
--                 IF :NEW.SLOT_ID BETWEEN 121 AND 180 THEN
--                         UPDATE MECHANIC M
--                         SET M.HOURS_WORKED_WEEK3 = M.HOURS_WORKED_WEEK3-1
--                         WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
--                 ELSE
--                         UPDATE MECHANIC M
--                         SET M.HOURS_WORKED_WEEK4 = M.HOURS_WORKED_WEEK4-1
--                         WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
--                 END IF;
--             END IF;
--         END IF;
-- END IF;
-- END;

create or replace TRIGGER MECHANIC_UPDATE_TOTAL_HOURS
    AFTER INSERT ON SERVICEEVENT
    FOR EACH ROW
BEGIN
        IF MOD(:NEW.SERVICE_DATE,10) = 1  THEN
            UPDATE MECHANIC M
            SET M.HOURS_WORKED_WEEK1 = M.HOURS_WORKED_WEEK1 + :NEW.TOTAL_HOURS
            WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
        ELSE
            IF MOD(:NEW.SERVICE_DATE,10) = 2  THEN
                UPDATE MECHANIC M
                SET M.HOURS_WORKED_WEEK2 = M.HOURS_WORKED_WEEK2 + :NEW.TOTAL_HOURS
                WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
            ELSE
                IF MOD(:NEW.SERVICE_DATE,10) = 3  THEN
                    UPDATE MECHANIC M
                    SET M.HOURS_WORKED_WEEK3 = M.HOURS_WORKED_WEEK3 + :NEW.TOTAL_HOURS
                    WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
                ELSE
                    UPDATE MECHANIC M
                    SET M.HOURS_WORKED_WEEK4 = M.HOURS_WORKED_WEEK4 + :NEW.TOTAL_HOURS
                    WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
                END IF;
            END IF;
        END IF;
END;

create sequence register_auto_inc start with 1;

create or replace trigger register_auto_inc_trig_final
before insert on MECHANICSCHEDULESWAP
for each row
begin
select register_auto_inc.nextval
into :new.REGISTER_ID
from dual;
end;

create sequence mechanic_schedule_auto_inc start with 1;

create or replace trigger mechanic_schedule_auto_inc_trig_final
before insert on MECHANICSCHEDULE
for each row
begin
select mechanic_schedule_auto_inc.nextval
into :new.MS_ID
from dual;
end;

create sequence slots_auto_inc start with 1;

create or replace trigger slots_auto_inc_trig_final
before insert on SLOTS
for each row
begin
select slots_auto_inc.nextval
into :new.SLOT_ID
from dual;
end;

create or replace TRIGGER VEHICLE_DELETE AFTER DELETE ON VEHICLE
FOR EACH ROW
DECLARE
    pragma autonomous_transaction;
    count_cars number;
    BEGIN
        SELECT COUNT(*) into count_cars
        FROM VEHICLE V
        WHERE :old.SC_ID = V.SC_ID AND :old.USER_ID = V.USER_ID;
        IF(count_cars = 0) THEN
            UPDATE CUSTOMER C
            SET C.STATUS = 0
            WHERE C.SC_ID=:OLD.SC_ID AND C.USER_ID=:OLD.USER_ID;
        END IF;
    END;

create or replace TRIGGER VEHICLE_INSERT AFTER INSERT ON VEHICLE
FOR EACH ROW
    BEGIN
        UPDATE CUSTOMER C
        SET C.STATUS = 1
        WHERE C.SC_ID = :NEW.SC_ID AND C.USER_ID = :NEW.USER_ID;
    END;

create or replace TRIGGER UPDATE_LAST_SERVICE
AFTER INSERT ON SERVICEEVENTDETAILS
FOR EACH ROW
DECLARE vehicle_id varchar(8);
BEGIN
    SELECT VIN into vehicle_id
    FROM SERVICEEVENT SE
    WHERE SE.SE_ID = :NEW.SE_ID;
    IF :NEW.S_ID BETWEEN 113 AND 115 THEN
        IF :NEW.S_ID = 113 THEN
            UPDATE VEHICLE V
            SET V.LAST_SERVICE = 'A'
            WHERE V.VIN = vehicle_id;
        END IF;
        IF :NEW.S_ID = 114 THEN
            UPDATE VEHICLE V
            SET V.LAST_SERVICE = 'B'
            WHERE V.VIN = vehicle_id;
        END IF;
        IF :NEW.S_ID = 115 THEN
            UPDATE VEHICLE V
            SET V.LAST_SERVICE = 'C'
            WHERE V.VIN = vehicle_id;
        END IF;
    END IF;
END;