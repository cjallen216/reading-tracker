
Select title, isbn_number, cover_img_link, b.book_id
FROM books as b
JOIN books_users as bu
        ON b.book_id = bu.book_id
JOIN users as u
        ON bu.user_id = u.user_id
LEFT JOIN people as p
        ON b.author_people_id = p.people_id
WHERE BU.USER_ID = ?


/*
INSERT INTO people
        (first_name,last_name)
VALUES ('Theodore', 'Suess')


Select *
FROM books_users


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