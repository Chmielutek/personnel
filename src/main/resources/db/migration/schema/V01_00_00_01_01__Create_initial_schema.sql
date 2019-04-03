CREATE SEQUENCE hibernate_sequence START 1000 INCREMENT 1;

DROP TABLE employee_entity if exists;
DROP TABLE address_entity if exists;

CREATE TABLE employee (
    id                  BIGINT
    GENERATED BY DEFAULT AS IDENTITY,
    first_name          VARCHAR(30),
    last_name           VARCHAR(30),
    PRIMARY KEY ( id )
);

CREATE TABLE address (
    id                  BIGINT
    GENERATED BY DEFAULT AS IDENTITY,
    street_name         VARCHAR(40),
    street_number       VARCHAR(40),
    city                VARCHAR(40),
    postal_code         VARCHAR(10),
    employee_id         bigint,
    PRIMARY KEY ( id )
);

ALTER TABLE address
    ADD CONSTRAINT fk_address_2_employee
    FOREIGN KEY ( employee_id )
    REFERENCES employee;