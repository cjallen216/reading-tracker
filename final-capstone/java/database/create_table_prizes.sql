BEGIN TRANSACTION;

DROP TABLE IF EXISTS prizes;
CREATE TABLE prizes(
    prize_id serial PRIMARY KEY,
    family_id INTEGER NOT NULL,
    prize_name varchar NOT NULL, --Did not add unique constraint as the same prize name could be added more than once...But is this what we want? 
    minutes_needed int NOT NULL,
    max_awarded int NOT NULL, --Do we want not null here? What if it's a small prize always available?
    awarded int NOT NULL, --Date format should be yyyy-mm-dd by default, so we will need to account for that in how it interacts with the date formatter on the front end.

    CONSTRAINT fk_prizes_families FOREIGN KEY (family_id) REFERENCES families (family_id),
    
);