CREATE TABLE PATIENT
(
    ENTITY_ID         numeric(19, 0)        NOT NULL,
    PATIENT_ID        varchar(255)          NOT NULL,
    AGE               numeric(19, 0),
    COMPANY           varchar(255),
    CITY_DISTRICT     varchar(255),
    VISION_DEFECT     varchar(255),
    CONSTRAINT PATIENT_PK PRIMARY KEY (ENTITY_ID),
    CONSTRAINT PATIENT_ID_UNIQUE UNIQUE (PATIENT_ID)
);