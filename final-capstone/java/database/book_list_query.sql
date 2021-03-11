/*
Select title, isbn_number, cover_img_link, b.book_id
FROM books as b
JOIN books_users as bu
        ON b.book_id = bu.book_id
JOIN users as u
        ON bu.user_id = u.user_id
LEFT JOIN people as p
        ON b.author_people_id = p.people_id
WHERE BU.USER_ID = ?
*/


--Gets all child logs for family
    SELECT reading_log.log_id, reading_log.user_id, users.username, books.book_id, books.title, reading_time.reading_format,
    reading_time.time_spent, reading_time.notes
    FROM families,
    JOIN users ON families.family_id = users.user_id
    JOIN books ON reading_time.book_id = books.book_id
    WHERE families.family_id = 1 AND users.role = 'child';
 
    
--Gets the books someone is reading based on the books they have logged
 SELECT books.book_id, books.family_id, isbn, title, author, image, description
 FROM users
 JOIN book_id ON users.id = reading_log.user_id
 JOIN books ON reading_time.book_id = books.book_id
 WHERE users.id = 1 AND reading_time.completed_book = false 
 ORDER BY title;


--Adds a book to someones personal bookshelf
  INSERT INTO books (book_id)
  VALUES (1, 2)




--Select *
--FROM books

/*
INSERT INTO people
        (first_name,last_name)
VALUES ('Theodore', 'Suess')





UPDATE books
        set author_people_id = 1
WHERE title = 'green eggs and ham';

INSERT INTO books_users (book_id, user_id, current_book, completed)
values (1,2,false,false),
        (2,2,false,false),
        (2,3,false,false),
        (3,3,false,false),
        (3,4,false,false),
        (4,4,false,false),
        (4,5,false,false),
        (5,5,false,false);

*/