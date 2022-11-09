CREATE TABLE "DVACHHA"."SERVICECENTER"
(	"SC_ID" NUMBER(5,0) NOT NULL ENABLE,
     "ADDRESS" VARCHAR2(100 BYTE) NOT NULL ENABLE,
     "TELEPHONE_NO" NUMBER(20,0) NOT NULL ENABLE,
     "STATE" VARCHAR2(100 BYTE) NOT NULL ENABLE,
     "OPEN_SAT" NUMBER(1,0) DEFAULT 0 NOT NULL ENABLE,
     "MIN_WAGE" NUMBER NOT NULL ENABLE,
     "MAX_WAGE" NUMBER NOT NULL ENABLE,
     "HOURLY_WAGE" NUMBER NOT NULL ENABLE,
     CONSTRAINT "SERVICECENTER_PK" PRIMARY KEY ("SC_ID") ENABLE,
     CONSTRAINT "SERVICECENTER_CHECK_HOURLY_WAGE" CHECK (HOURLY_WAGE BETWEEN MIN_WAGE AND MAX_WAGE) ENABLE,
     CONSTRAINT "CHECK_OPEN_SAT_VALID" CHECK (OPEN_SAT in (0, 1)) ENABLE
);

CREATE TABLE "DVACHHA"."USERS"
(	"USER_ID" NUMBER(9,0) NOT NULL ENABLE,
     "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
     "USERNAME" VARCHAR2(100 BYTE) NOT NULL ENABLE,
     "PASSWORD" VARCHAR2(100 BYTE) NOT NULL ENABLE,
     "ROLE" VARCHAR2(100 BYTE) NOT NULL ENABLE,
     CONSTRAINT "USERS_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
     CONSTRAINT "USERS_UNIQUE_USERNAME" UNIQUE ("USERNAME") ENABLE,
     CONSTRAINT "USERS_CHECK_ROLE" CHECK (ROLE IN ('ADMIN','RECEPTIONIST','MANAGER','MECHANIC','CUSTOMER')) ENABLE,
     CONSTRAINT "USERS_FK_SC_ID" FOREIGN KEY ("SC_ID")
         REFERENCES "DVACHHA"."SERVICECENTER" ("SC_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "DVACHHA"."EMPLOYEE" (
                                       "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                       "NAME" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                       "ADDRESS" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                       "EMAIL" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                       "PHONE_NO" NUMBER NOT NULL ENABLE,
                                       "ROLE" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                       "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                       CONSTRAINT "EMP_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                       CONSTRAINT "EMP_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                           REFERENCES "DVACHHA"."USERS" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                       CONSTRAINT "EMAIL_UNIQUE" UNIQUE ("EMAIL") ENABLE,
                                       CONSTRAINT "EMP_CHECK_ROLE" CHECK (ROLE IN ('ADMIN','RECEPTIONIST','MANAGER','MECHANIC')) ENABLE
);

CREATE TABLE "DVACHHA"."MANAGER" (
                                      "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                      "ANNUAL_SALARY" NUMBER NOT NULL ENABLE,
                                      "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                      CONSTRAINT "MANAGER_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                      CONSTRAINT "MANAGER_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                          REFERENCES "DVACHHA"."EMPLOYEE" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                      CONSTRAINT "MANAGER_SC_ID_UNIQUE" UNIQUE ("SC_ID") ENABLE
);

CREATE TABLE "DVACHHA"."RECEPTIONIST" (
                                           "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                           "ANNUAL_SALARY" NUMBER NOT NULL ENABLE,
                                           CONSTRAINT "RECEPTIONIST_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                           CONSTRAINT "RECEPTIONIST_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                               REFERENCES "DVACHHA"."EMPLOYEE" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "RECEPTIONIST_SC_ID_UNIQUE" UNIQUE ("SC_ID") ENABLE
);

CREATE TABLE "DVACHHA"."MECHANIC" (
                                       "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                       "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                       "HOURS_WORKED_WEEK1" NUMBER DEFAULT 0 NOT NULL ENABLE,
                                       "HOURS_WORKED_WEEK2" NUMBER DEFAULT 0 NOT NULL ENABLE,
                                       "HOURS_WORKED_WEEK3" NUMBER DEFAULT 0 NOT NULL ENABLE,
                                       "HOURS_WORKED_WEEK4" NUMBER DEFAULT 0 NOT NULL ENABLE,
                                       CONSTRAINT "MECHANIC_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                       CONSTRAINT "MECHANIC_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                           REFERENCES "DVACHHA"."EMPLOYEE" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                       CONSTRAINT "MECHANIC_HOURS_WORKED_WEEK1" CHECK (HOURS_WORKED_WEEK1 <= 50) ENABLE,
                                       CONSTRAINT "MECHANIC_HOURS_WORKED_WEEK2" CHECK (HOURS_WORKED_WEEK2 <= 50) ENABLE,
                                       CONSTRAINT "MECHANIC_HOURS_WORKED_WEEK3" CHECK (HOURS_WORKED_WEEK3 <= 50) ENABLE,
                                       CONSTRAINT "MECHANIC_HOURS_WORKED_WEEK4" CHECK (HOURS_WORKED_WEEK4 <= 50) ENABLE
);

CREATE TABLE "DVACHHA"."CARSERVICE" (
                                         "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                         "NAME" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                         "TYPE" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                         CONSTRAINT "CARSERVICE_PK" PRIMARY KEY ("S_ID") ENABLE,
                                         CONSTRAINT "CARSERVICE_UNIQUE_NAME" UNIQUE ("NAME") ENABLE,
                                         CONSTRAINT "CARSERVICE_CHECK_TYPE" CHECK (TYPE IN ('M','R','MR')) ENABLE
);

CREATE TABLE "DVACHHA"."MAINTAINANCE" (
                                           "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "BUNDLE" VARCHAR2(100BYTE) NOT NULL ENABLE,
                                           CONSTRAINT "MAINTAINANCEPK" PRIMARY KEY ("S_ID") ENABLE,
                                           CONSTRAINT "MAINTAINANCE_FK_S_ID" FOREIGN KEY ("S_ID")
                                               REFERENCES "DVACHHA"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "MAINTAINANCE_CHECK_BUNDLE" CHECK (BUNDLE IN ('A','B','C')) ENABLE
);

CREATE TABLE "DVACHHA"."REPAIR" (
                                     "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                     "CATEGORY" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                     CONSTRAINT "REPAIR_PK" PRIMARY KEY ("S_ID") ENABLE,
                                     CONSTRAINT "REPAIR_FK_S_ID" FOREIGN KEY ("S_ID")
                                         REFERENCES "DVACHHA"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE,
                                     CONSTRAINT "CHECK_CATEGORY" CHECK ( CATEGORY IN ('ENGINESERVICES', 'EXHAUSTSERVICES' , 'ELECTRICALSERVICES', 'TRANSMISSIONSERVICES', 'TIRESERVICES', 'HEATINGANDACSERVICES')) ENABLE
);

CREATE TABLE "DVACHHA"."OFFEREDPRICE" (
                                           "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                           "BRAND" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                           "PRICE" NUMBER NOT NULL ENABLE,
                                           CONSTRAINT "OFFEREDPRICE_PK" PRIMARY KEY ("S_ID", "SC_ID" ,"BRAND") ENABLE,
                                           CONSTRAINT "OFFEREDPRICE_CHECK_BRAND" CHECK (BRAND IN ('HONDA','NISSAN','TOYOTA','LEXUS','INFINITI')) ENABLE,
                                           CONSTRAINT "OFFEREDPRICE_FK_S_ID" FOREIGN KEY ("S_ID")
                                               REFERENCES "DVACHHA"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "OFFEREDPRICE_FK_SC_ID" FOREIGN KEY ("SC_ID")
                                               REFERENCES "DVACHHA"."SERVICECENTER" ("SC_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "DVACHHA"."OFFEREDTIME" (
                                          "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                          "BRAND" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                          "HRS" NUMBER NOT NULL ENABLE,
                                          CONSTRAINT "OFFEREDTIME_PK" PRIMARY KEY ("S_ID", "BRAND") ENABLE,
                                          CONSTRAINT "OFFEREDTIME_CHECK_BRAND" CHECK (BRAND IN ('HONDA','NISSAN','TOYOTA','LEXUS','INFINITI')) ENABLE,
                                          CONSTRAINT "OFFEREDTIME_FK_S_ID" FOREIGN KEY ("S_ID")
                                              REFERENCES "DVACHHA"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "DVACHHA"."CUSTOMER" (
                                       "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                       "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                       "F_NAME" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                       "L_NAME" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                       "ADDRESS" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                       "EMAIL" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                       "PHONE_NO" NUMBER NOT NULL ENABLE,
                                       "STANDING" NUMBER(1,0) NOT NULL ENABLE,
                                       "STATUS" NUMBER(1,0) NOT NULL ENABLE,
                                       CONSTRAINT "CUSTOMER_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                       CONSTRAINT "CUSTOMER_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                           REFERENCES "DVACHHA"."USERS" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                       CONSTRAINT "CUSTOMER_EMAIL_UNIQUE" UNIQUE ("EMAIL") ENABLE
);

CREATE TABLE "DVACHHA"."VEHICLE" (
                                      "VIN" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                      "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                      "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                      "BRAND" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                      "MILEAGE" NUMBER NOT NULL ENABLE,
                                      "YEAR" NUMBER NOT NULL ENABLE,
                                      "LAST_SERVICE" VARCHAR2(100 BYTE) DEFAULT 'O' NOT NULL ENABLE,
                                      CONSTRAINT "VEHICLE_PK" PRIMARY KEY ("VIN") ENABLE,
                                      CONSTRAINT "VEHICLE_CHECK_BRAND" CHECK (BRAND IN ('HONDA','NISSAN','TOYOTA','LEXUS','INFINITI')) ENABLE,
                                      CONSTRAINT "VEHICLE_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                          REFERENCES "DVACHHA"."CUSTOMER" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "DVACHHA"."SERVICEEVENT" (
                                           "SE_ID" NUMBER(5,0) NOT NULL ENABLE,
                                           "VIN" VARCHAR2(100 BYTE) NOT NULL ENABLE,
                                           "MECH_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                           "CUST_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "AMOUNT_PAID" NUMBER DEFAULT 0 NOT NULL ENABLE,
                                           "AMOUNT_CHARGED" NUMBER NOT NULL ENABLE,
                                           "SERVICE_DATE" NUMBER NOT NULL ENABLE,
                                           "STATUS" VARCHAR2(100 BYTE) DEFAULT 'UNPAID' NOT NULL ENABLE,
                                           "TOTAL_HOURS" NUMBER(2,0) NOT NULL ENABLE,
                                           CONSTRAINT "SERVICEEVENT_PK" PRIMARY KEY ("SE_ID") ENABLE,
                                           CONSTRAINT "SERVICEEVENT_FK_VIN" FOREIGN KEY ("VIN")
                                               REFERENCES "DVACHHA"."VEHICLE" ("VIN") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "SERVICEEVENT_FK_MECH_ID_SC_ID" FOREIGN KEY ("MECH_ID", "SC_ID")
                                               REFERENCES "DVACHHA"."MECHANIC" ("USER_ID", "SC_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "SERVICEEVENT_FK_CUST_ID" FOREIGN KEY ("CUST_ID","SC_ID")
                                               REFERENCES "DVACHHA"."CUSTOMER" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "SERVICEEVENT_CHECK_AMOUNT_PAID" CHECK (STATUS IN ('UNPAID','PAID')) ENABLE
);

CREATE TABLE "DVACHHA"."SERVICEEVENTDETAILS" (
                                                  "SE_ID" NUMBER(5,0) NOT NULL ENABLE,
                                                  "S_ID" NUMBER(5,0) NOT NULL ENABLE,
                                                  CONSTRAINT "SERVICEEVENTDETAILS_PK" PRIMARY KEY ("SE_ID","S_ID") ENABLE,
                                                  CONSTRAINT "SERVICEEVENTDETAILS_FK_SE_ID" FOREIGN KEY ("SE_ID")
                                                      REFERENCES "DVACHHA"."SERVICEEVENT" ("SE_ID") ON DELETE CASCADE ENABLE,
                                                  CONSTRAINT "SERVICEEVENTDETAILS_FK_S_ID" FOREIGN KEY ("S_ID")
                                                      REFERENCES "DVACHHA"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "DVACHHA"."SLOTS"
(
    "WEEK" NUMBER(1,0) NOT NULL ENABLE,
    "SLOT_DAY" NUMBER(1,0) NOT NULL ENABLE,
    "SLOTS" NUMBER(2,0) NOT NULL ENABLE,
    "SLOT_ID" NUMBER(5,0) NOT NULL ENABLE,
    CONSTRAINT "SLOTS_PK" PRIMARY KEY ("SLOT_ID") ENABLE,
    CONSTRAINT "SLOTS_CHECK_WEEK" CHECK (WEEK BETWEEN 1 AND 4) ENABLE,
    CONSTRAINT "SLOTS_CHECK_DAY" CHECK (SLOT_DAY BETWEEN 1 AND 6) ENABLE,
    CONSTRAINT "SLOTS_CHECK_SLOTS" CHECK (SLOTS BETWEEN 1 AND 11) ENABLE
);

CREATE TABLE "DVACHHA"."MECHANICSCHEDULE"
(
    "MS_ID" NUMBER(9,0) NOT NULL ENABLE,
    "MECH_ID" NUMBER(9,0) NOT NULL ENABLE,
    "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
    "SLOT_ID" NUMBER(5,0) NOT NULL ENABLE,
    "AVAILABLE" VARCHAR2(100 BYTE) NOT NULL ENABLE,
    CONSTRAINT "MECHANICSCHEDULE_PK" PRIMARY KEY ("MS_ID") ENABLE,
    CONSTRAINT "MECHANICSCHEDULE_FK_MECH_ID_SC_ID" FOREIGN KEY ("MECH_ID","SC_ID")
        REFERENCES "DVACHHA"."MECHANIC" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
    CONSTRAINT "MECHANICSCHEDULE_FK_SLOT_ID" FOREIGN KEY ("SLOT_ID")
        REFERENCES "DVACHHA"."SLOTS" ("SLOT_ID") ON DELETE CASCADE ENABLE,
    CONSTRAINT "MECHANICSCHEDULE_AVAILABLE" CHECK (AVAILABLE IN ('AVAILABLE','WORKING','TIMEOFF','CLOSED')) ENABLE
);

CREATE TABLE "DVACHHA"."MECHANICSCHEDULESWAP"
(
    "REGISTER_ID" NUMBER(9,0) NOT NULL ENABLE,
    "MS_ID1" NUMBER(9,0) NOT NULL ENABLE,
    "MS_ID2" NUMBER(9,0) NOT NULL ENABLE,
    "APPROVED" NUMBER(1,0),
    CONSTRAINT "MECHANICSCHEDULESWAP_PK" PRIMARY KEY ("REGISTER_ID") ENABLE,
    CONSTRAINT "MECHANICSCHEDULESWAP_FK_MS_ID1" FOREIGN KEY ("MS_ID1")
        REFERENCES "DVACHHA"."MECHANICSCHEDULE" ("MS_ID") ON DELETE CASCADE ENABLE,
    CONSTRAINT "MECHANICSCHEDULESWAP_FK_MS_ID2" FOREIGN KEY ("MS_ID2")
        REFERENCES "DVACHHA"."MECHANICSCHEDULE" ("MS_ID") ON DELETE CASCADE ENABLE
);
-- TRIGGERS

-- CREATE TRIGGER MECHANIC_TIMEOFF
--     AFTER UPDATE ON MECHANICSCHEDULE
-- FOR EACH ROW
-- BEGIN
--     IF :OLD.AVAILABLE <> 'TIMEOFF' AND :NEW.AVAILABLE = 'TIMEOFF' THEN
--         IF :NEW.SLOT_ID BETWEEN 1 AND 100 THEN
--             UPDATE MECHANIC M
--             SET M.HOURS_WORKED = M.HOURS_WORKED-1
--             WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
--         END IF;
--     END IF;
-- END;
--
-- CREATE TRIGGER MECHANIC_UPDATE_TOTAL_HOURS
--     AFTER INSERT ON SERVICEEVENT
--     FOR EACH ROW
-- BEGIN
--     IF :NEW.SERVICE_DATE BETWEEN 1 AND 100 THEN
--         UPDATE MECHANIC M
--         SET M.HOURS_WORKED = M.HOURS_WORKED + :NEW.TOTAL_HOURS
--         WHERE M.USER_ID = :NEW.MECH_ID AND M.SC_ID = :NEW.SC_ID;
--     END IF;
-- END;


