CREATE TABLE user_address
(
    id         UUID NOT NULL,
    continent  VARCHAR(255),
    country    VARCHAR(255),
    city       VARCHAR(255),
    region     VARCHAR(255),
    department VARCHAR(255),
    district   VARCHAR(255),
    CONSTRAINT pk_useraddress PRIMARY KEY (id)
);