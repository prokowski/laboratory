CREATE TABLE SAMPLE
(
    ENTITY_ID         numeric(19, 0)        NOT NULL,
    SAMPLE_ID         varchar(255)          NOT NULL,
    PATIENT_ID        varchar(255)          NOT NULL,
    RACK_ID           varchar(255),
    CONSTRAINT SAMPLE_PK PRIMARY KEY (ENTITY_ID),
    CONSTRAINT SAMPLE_ID_UNIQUE UNIQUE (SAMPLE_ID)
);