CREATE TABLE SEQUENCE
(
    ENTITY_ID numeric(19,0) NOT NULL,
    NAME varchar(255) NOT NULL,
    SEQUENCE numeric(19,0) NOT NULL,
    CONSTRAINT SEQUENCE_PK PRIMARY KEY (ENTITY_ID),
    CONSTRAINT SEQUENCE_UNIQUE UNIQUE (NAME)
);

INSERT INTO SEQUENCE VALUES (nextval('hibernate_sequence'), 'PTN', 0);
INSERT INTO SEQUENCE VALUES (nextval('hibernate_sequence'), 'SMP', 0);
INSERT INTO SEQUENCE VALUES (nextval('hibernate_sequence'), 'RCK', 0);