
CREATE TABLE user_token
(
    id            VARCHAR(255) NOT NULL,
    access_token  TEXT,
    refresh_token TEXT,
    user_id       UUID         NOT NULL,
    CONSTRAINT pk_usertoken PRIMARY KEY (id)
);

ALTER TABLE user_token
    ADD CONSTRAINT FK_USER_TOKEN_USER_ID FOREIGN KEY (user_id) REFERENCES user_app (id);
