CREATE TABLE "RSHUKLA3"."SERVICECENTER"
(	"SC_ID" NUMBER(5,0) NOT NULL ENABLE,
     "ADDRESS" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "TELEPHONE_NO" NUMBER(20,0) NOT NULL ENABLE,
     "STATE" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "OPEN_SAT" NUMBER(1,0) DEFAULT 0 NOT NULL ENABLE,
     "MIN_WAGE" NUMBER NOT NULL ENABLE,
     "MAX_WAGE" NUMBER NOT NULL ENABLE,
     "HOURLY_WAGE" NUMBER NOT NULL ENABLE,
     CONSTRAINT "SERVICECENTER_PK" PRIMARY KEY ("SC_ID") ENABLE,
     CONSTRAINT "SERVICECENTER_CHECK_HOURLY_WAGE" CHECK (HOURLY_WAGE BETWEEN MIN_WAGE AND MAX_WAGE) ENABLE
);

CREATE TABLE "RSHUKLA3"."USERS"
(	"USER_ID" NUMBER(9,0) NOT NULL ENABLE,
     "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
     "USERNAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "PASSWORD" VARCHAR2(20 BYTE) DEFAULT 123456789 NOT NULL ENABLE,
     "ROLE" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     CONSTRAINT "USERS_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
     CONSTRAINT "USERS_UNIQUE_USERNAME" UNIQUE ("USERNAME") ENABLE,
     CONSTRAINT "USERS_CHECK_ROLE" CHECK (ROLE IN ('ADMIN','RECEPTIONIST','MANAGER','MECHANIC','CUSTOMER')) ENABLE,
     CONSTRAINT "USERS_FK_SC_ID" FOREIGN KEY ("SC_ID")
         REFERENCES "RSHUKLA3"."SERVICECENTER" ("SC_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "RSHUKLA3"."EMPLOYEE" (
                                       "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                       "NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                       "ADDRESS" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                       "EMAIL" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                       "PHONE_NO" NUMBER NOT NULL ENABLE,
                                       "ROLE" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                       "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                       CONSTRAINT "EMP_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                       CONSTRAINT "EMP_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                           REFERENCES "RSHUKLA3"."USERS" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                       CONSTRAINT "EMAIL_UNIQUE" UNIQUE ("EMAIL") ENABLE,
                                       CONSTRAINT "EMP_CHECK_ROLE" CHECK (ROLE IN ('ADMIN','RECEPTIONIST','MANAGER','MECHANIC')) ENABLE
);

CREATE TABLE "RSHUKLA3"."MANAGER" (
                                      "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                      "ANNUAL_SALARY" NUMBER NOT NULL ENABLE,
                                      "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                      CONSTRAINT "MANAGER_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                      CONSTRAINT "MANAGER_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                          REFERENCES "RSHUKLA3"."EMPLOYEE" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                      CONSTRAINT "MANAGER_SC_ID_UNIQUE" UNIQUE ("SC_ID") ENABLE
);

CREATE TABLE "RSHUKLA3"."RECEPTIONIST" (
                                           "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                           "ANNUAL_SALARY" NUMBER NOT NULL ENABLE,
                                           CONSTRAINT "RECEPTIONIST_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                           CONSTRAINT "RECEPTIONIST_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                               REFERENCES "RSHUKLA3"."EMPLOYEE" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "RECEPTIONIST_SC_ID_UNIQUE" UNIQUE ("SC_ID") ENABLE
);

CREATE TABLE "RSHUKLA3"."MECHANIC" (
                                       "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                       "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                       "HOURS_WORKED" NUMBER NOT NULL ENABLE,
                                       CONSTRAINT "MECHANIC_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                       CONSTRAINT "MECHANIC_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                           REFERENCES "RSHUKLA3"."EMPLOYEE" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                       CONSTRAINT "CHECK_IF_LESS_THAN_50HRS" CHECK (HOURS_WORKED < 50) ENABLE
);

CREATE TABLE "RSHUKLA3"."CARSERVICE" (
                                         "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                         "NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                         "TYPE" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                         CONSTRAINT "CARSERVICE_PK" PRIMARY KEY ("S_ID") ENABLE,
                                         CONSTRAINT "CARSERVICE_UNIQUE_NAME" UNIQUE ("NAME") ENABLE,
                                         CONSTRAINT "CARSERVICE_CHECK_TYPE" CHECK (TYPE IN ('MAINTENANCE','REPAIR','MAINTENANCEREPAIR')) ENABLE
);

CREATE TABLE "RSHUKLA3"."MAINTAINANCE" (
                                           "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "BUNDLE" VARCHAR2(1 BYTE) NOT NULL ENABLE,
                                           CONSTRAINT "MAINTAINANCEPK" PRIMARY KEY ("S_ID") ENABLE,
                                           CONSTRAINT "MAINTAINANCE_FK_S_ID" FOREIGN KEY ("S_ID")
                                               REFERENCES "RSHUKLA3"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "MAINTAINANCE_CHECK_BUNDLE" CHECK (BUNDLE IN ('A','B','C')) ENABLE
);

CREATE TABLE "RSHUKLA3"."REPAIR" (
                                     "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                     "CATEGORY" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                     CONSTRAINT "REPAIR_PK" PRIMARY KEY ("S_ID") ENABLE,
                                     CONSTRAINT "REPAIR_FK_S_ID" FOREIGN KEY ("S_ID")
                                         REFERENCES "RSHUKLA3"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "RSHUKLA3"."OFFEREDPRICE" (
                                           "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                           "BRAND" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                           "PRICE" NUMBER NOT NULL ENABLE,
                                           CONSTRAINT "OFFEREDPRICE_PK" PRIMARY KEY ("S_ID", "SC_ID" ,"BRAND") ENABLE,
                                           CONSTRAINT "OFFEREDPRICE_CHECK_BRAND" CHECK (BRAND IN ('HONDA','NISSAN','TOYOTA','LEXUS','INFINITI')) ENABLE,
                                           CONSTRAINT "OFFEREDPRICE_FK_S_ID" FOREIGN KEY ("S_ID")
                                               REFERENCES "RSHUKLA3"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "OFFEREDPRICE_FK_SC_ID" FOREIGN KEY ("SC_ID")
                                               REFERENCES "RSHUKLA3"."SERVICECENTER" ("SC_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "RSHUKLA3"."OFFEREDTIME" (
                                          "S_ID" NUMBER(9,0) NOT NULL ENABLE,
                                          "BRAND" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                          "HRS" NUMBER NOT NULL ENABLE,
                                          CONSTRAINT "OFFEREDTIME_PK" PRIMARY KEY ("S_ID", "BRAND") ENABLE,
                                          CONSTRAINT "OFFEREDTIME_CHECK_BRAND" CHECK (BRAND IN ('HONDA','NISSAN','TOYOTA','LEXUS','INFINITI')) ENABLE,
                                          CONSTRAINT "OFFEREDTIME_FK_S_ID" FOREIGN KEY ("S_ID")
                                              REFERENCES "RSHUKLA3"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "RSHUKLA3"."CUSTOMER" (
                                       "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                       "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                       "F_NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                       "L_NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                       "ADDRESS" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                       "EMAIL" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                       "PHONE_NO" NUMBER NOT NULL ENABLE,
                                       "STANDING" NUMBER(1,0) NOT NULL ENABLE,
                                       "STATUS" NUMBER(1,0) NOT NULL ENABLE,
                                       CONSTRAINT "CUSTOMER_PK" PRIMARY KEY ("USER_ID", "SC_ID") ENABLE,
                                       CONSTRAINT "CUSTOMER_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                           REFERENCES "RSHUKLA3"."USERS" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                       CONSTRAINT "CUSTOMER_EMAIL_UNIQUE" UNIQUE ("EMAIL") ENABLE
);

CREATE TABLE "RSHUKLA3"."VEHICLE" (
                                      "VIN" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                      "USER_ID" NUMBER(9,0) NOT NULL ENABLE,
                                      "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                      "BRAND" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                      "MILEAGE" NUMBER NOT NULL ENABLE,
                                      "YEAR" NUMBER NOT NULL ENABLE,
                                      "LAST_SERVICE" VARCHAR(1 BYTE) DEFAULT '0' NOT NULL ENABLE,
                                      CONSTRAINT "VEHICLE_PK" PRIMARY KEY ("VIN") ENABLE,
                                      CONSTRAINT "VEHICLE_CHECK_BRAND" CHECK (BRAND IN ('HONDA','NISSAN','TOYOTA','LEXUS','INFINITI')) ENABLE,
                                      CONSTRAINT "VEHICLE_FK_USER_ID_SC_ID" FOREIGN KEY ("USER_ID","SC_ID")
                                          REFERENCES "RSHUKLA3"."CUSTOMER" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "RSHUKLA3"."SERVICEEVENT" (
                                           "SE_ID" NUMBER(5,0) NOT NULL ENABLE,
                                           "VIN" VARCHAR2(20 BYTE) NOT NULL ENABLE,
                                           "MECH_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
                                           "CUST_ID" NUMBER(9,0) NOT NULL ENABLE,
                                           "AMOUNT_PAID" NUMBER DEFAULT 0 NOT NULL ENABLE,
                                           "AMOUNT_CHARGED" NUMBER NOT NULL ENABLE,
                                           "SERVICE_DATE" DATE NOT NULL ENABLE,
                                           "STATUS" VARCHAR2(20 BYTE) DEFAULT 'UNPAID' NOT NULL ENABLE,
                                           CONSTRAINT "SERVICEEVENT_PK" PRIMARY KEY ("SE_ID") ENABLE,
                                           CONSTRAINT "SERVICEEVENT_FK_VIN" FOREIGN KEY ("VIN")
                                               REFERENCES "RSHUKLA3"."VEHICLE" ("VIN") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "SERVICEEVENT_FK_MECH_ID_SC_ID" FOREIGN KEY ("MECH_ID", "SC_ID")
                                               REFERENCES "RSHUKLA3"."MECHANIC" ("USER_ID", "SC_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "SERVICEEVENT_FK_CUST_ID" FOREIGN KEY ("CUST_ID","SC_ID")
                                               REFERENCES "RSHUKLA3"."CUSTOMER" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
                                           CONSTRAINT "SERVICEEVENT_CHECK_AMOUNT_PAID" CHECK (STATUS IN ('UNPAID','PAID')) ENABLE
);

CREATE TABLE "RSHUKLA3"."SERVICEEVENTDETAILS" (
                                                  "SE_ID" NUMBER(5,0) NOT NULL ENABLE,
                                                  "S_ID" NUMBER(5,0) NOT NULL ENABLE,
                                                  CONSTRAINT "SERVICEEVENTDETAILS_PK" PRIMARY KEY ("SE_ID","S_ID") ENABLE,
                                                  CONSTRAINT "SERVICEEVENTDETAILS_FK_SE_ID" FOREIGN KEY ("SE_ID")
                                                      REFERENCES "RSHUKLA3"."SERVICEEVENT" ("SE_ID") ON DELETE CASCADE ENABLE,
                                                  CONSTRAINT "SERVICEEVENTDETAILS_FK_S_ID" FOREIGN KEY ("S_ID")
                                                      REFERENCES "RSHUKLA3"."CARSERVICE" ("S_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "RSHUKLA3"."SLOTS"
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

CREATE TABLE "RSHUKLA3"."MECHANICSCHEDULE"
(
    "MS_ID" NUMBER(9,0) NOT NULL ENABLE,
    "MECH_ID" NUMBER(9,0) NOT NULL ENABLE,
    "SC_ID" NUMBER(5,0) NOT NULL ENABLE,
    "SLOT_ID" NUMBER(5,0) NOT NULL ENABLE,
    "AVAILABLE" NUMBER(1,0) NOT NULL ENABLE,
    "WORKING" NUMBER(1,0) NOT NULL ENABLE,
    CONSTRAINT "MECHANICSCHEDULE_PK" PRIMARY KEY ("MS_ID") ENABLE,
    CONSTRAINT "MECHANICSCHEDULE_FK_MECH_ID_SC_ID" FOREIGN KEY ("MECH_ID","SC_ID")
        REFERENCES "RSHUKLA3"."MECHANIC" ("USER_ID","SC_ID") ON DELETE CASCADE ENABLE,
    CONSTRAINT "MECHANICSCHEDULE_FK_SLOT_ID" FOREIGN KEY ("SLOT_ID")
        REFERENCES "RSHUKLA3"."SLOTS" ("SLOT_ID") ON DELETE CASCADE ENABLE
);

CREATE TABLE "RSHUKLA3"."MECHANICSCHEDULESWAP"
(
    "REGISTER_ID" NUMBER(9,0) NOT NULL ENABLE,
    "MS_ID1" NUMBER(9,0) NOT NULL ENABLE,
    "MS_ID2" NUMBER(9,0) NOT NULL ENABLE,
    "APPROVED" NUMBER(1,0),
    CONSTRAINT "MECHANICSCHEDULESWAP_PK" PRIMARY KEY ("REGISTER_ID") ENABLE,
    CONSTRAINT "MECHANICSCHEDULESWAP_FK_MS_ID1" FOREIGN KEY ("MS_ID1")
        REFERENCES "RSHUKLA3"."MECHANICSCHEDULE" ("MS_ID") ON DELETE CASCADE ENABLE,
    CONSTRAINT "MECHANICSCHEDULESWAP_FK_MS_ID2" FOREIGN KEY ("MS_ID2")
        REFERENCES "RSHUKLA3"."MECHANICSCHEDULE" ("MS_ID") ON DELETE CASCADE ENABLE
);