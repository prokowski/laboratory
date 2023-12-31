CREATE TABLE RACK
(
    ENTITY_ID           numeric(19, 0)        NOT NULL,
    RACK_ID             varchar(255)          NOT NULL,
    CAPACITY            numeric(19, 0),
    CONSTRAINT RACK_PK PRIMARY KEY (ENTITY_ID),
    CONSTRAINT RACK_ID_UNIQUE UNIQUE (RACK_ID)
);

------------------------------------------------------------------------------

CREATE TABLE RACK_SAMPLE
(
    ENTITY_ID           numeric(19,0)          NOT NULL,
    RACK_ID             varchar(255)           NOT NULL,
    SAMPLE_ID           varchar(255)           NOT NULL,
    PATIENT_ID          varchar(255)          NOT NULL,
    AGE                 numeric(19, 0),
    COMPANY             varchar(255),
    CITY_DISTRICT       varchar(255),
    VISION_DEFECT       varchar(255),
    CONSTRAINT RACK_SAMPLE_PK PRIMARY KEY (ENTITY_ID),
    CONSTRAINT RACK_SAMPLE_UNIQUE UNIQUE (ENTITY_ID, SAMPLE_ID)
);