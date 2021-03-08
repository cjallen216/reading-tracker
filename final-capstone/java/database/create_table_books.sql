BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS books;

CREATE TABLE books(
        book_id SERIAL primary key NOT NULL,
        isbn_number varchar(13),
        title char(200),
        author_people_id INTEGER,
        cover_img_link varchar(50)
        
);
COMMIT TRANSACTION;
