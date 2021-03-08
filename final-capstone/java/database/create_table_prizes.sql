BEGIN TRANSACTION;

DROP TABLE IF EXISTS prizes;
CREATE TABLE prizes (
    prize_id serial primary key,
    family_id integer NOT NULL,
    prize_name varchar(50) NOT NULL, 
    minutes_needed integer NOT NULL,
    max_award_count integer,
    awarded_count integer
     /*Date format should be yyyy-mm-dd by default, so we will need to account for that in how it interacts with the date formatter on the front end.*/
);

COMMIT TRANSACTION;