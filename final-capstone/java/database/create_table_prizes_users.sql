BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS prizes_users;

CREATE TABLE prizes_users(
    prizes_users_id SERIAL PRIMARY KEY NOT NULL,
    prize_id INTEGER,
    user_id INTEGER,
    awarded_date date NOT NULL

);

COMMIT TRANSACTION;