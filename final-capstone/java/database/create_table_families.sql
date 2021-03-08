BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS families;

CREATE TABLE families(
    family_id SERIAL NOT NULL,
    user_id INTEGER NOT NULL,
    family_name varchar(50) NOT NULL,

    CONSTRAINT pk_families PRIMARY KEY (family_id),
    CONSTRAINT fk_families_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

COMMIT TRANSACTION;