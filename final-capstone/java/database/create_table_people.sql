BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS people;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_people_id;

--create sequences manually - so that you can set the value when inserting new data
CREATE SEQUENCE seq_people_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE people(
	people_id INTEGER DEFAULT nextval('seq_people_id') PRIMARY KEY NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50)
        
);

COMMIT TRANSACTION;