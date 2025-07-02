-- liquibase formatted sql

-- changeset LENOVO_P51S:1751139625539-1
CREATE TABLE "user"
(
    id           BYTEA NOT NULL,
    email        VARCHAR(255),
    mot_de_passe VARCHAR(255),
    role         INTEGER,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

