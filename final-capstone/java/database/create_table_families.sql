BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS families;

CREATE TABLE families(
    family_id serial NOT NULL,
    user_id INTEGER NOT NULL,
    family_name varchar(50) NOT NULL,

    CONSTRAINT pk_families PRIMARY KEY (family_id)
);

COMMIT TRANSACTION;