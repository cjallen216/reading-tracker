BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS books_users;

CREATE TABLE books_users(
        books_users_id serial NOT NULL,
        book_id INTEGER NOT NULL,
        user_id INTEGER NOT NULL,
        current_book boolean NOT NULL,
        completed boolean NOT NULL,
     
     CONSTRAINT pk_books_users PRIMARY KEY (books_users_id)  
);

COMMIT TRANSACTION