BEGIN TRANSACTION;

DROP TABLE IF EXISTS prizes;
CREATE TABLE prizes (
    prize_id serial,
    family_id int NOT NULL,
    prize_name varchar(50) NOT NULL, 
    minutes_needed int NOT NULL,
    max_award_count int,
    awarded_count int, /*Date format should be yyyy-mm-dd by default, so we will need to account for that in how it interacts with the date formatter on the front end.*/

    CONSTRAINT PK_prizes PRIMARY KEY (prize_id),
	CONSTRAINT fk_prizes_families FOREIGN KEY (family_id) REFERENCES families (family_id)
    
);

COMMIT TRANSACTION;