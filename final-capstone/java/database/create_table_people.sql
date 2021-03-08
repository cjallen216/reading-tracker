BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS people;

CREATE TABLE people(
        people_id SERIAL NOT NULL,
        first_name varchar(50) NOT NULL,
        last_name varchar(50)
        
);
COMMIT TRANSACTION;