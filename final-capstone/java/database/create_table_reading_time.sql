BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS reading_time;

CREATE TABLE reading_time(
    time_id SERIAL PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    minutes_read INTEGER

);

COMMIT TRANSACTION;