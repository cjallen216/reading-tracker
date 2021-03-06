BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS prizes_users;

CREATE TABLE prizes_users(
    prize_user_id serial,
    prize_id INTEGER,
    user_id INTEGER,
    awarded_date date NOT NULL,

    CONSTRAINT pk_prizes_linker PRIMARY KEY (prize_user_id),
    CONSTRAINT fk_linker_prizes FOREIGN KEY (prize_id) REFERENCES prizes (prize_id),
    CONSTRAINT fk_linker_user FOREIGN KEY (user_id) REFERENCES users (id)
);

COMMIT TRANSACTION;