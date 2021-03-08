BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS prizes_users;

CREATE TABLE prizes_users(
    prizes_users_id serial,
    prize_id INTEGER,
    user_id INTEGER,
    awarded_date date NOT NULL,

    CONSTRAINT pk_prizes_users PRIMARY KEY (prizes_users_id)
);

COMMIT TRANSACTION;