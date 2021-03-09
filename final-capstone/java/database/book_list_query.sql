INSERT INTO books_users (book_id, user_id, current_book, completed)
values (1,2,false,false),
        (2,2,false,false),
        (2,3,false,false),
        (3,3,false,false),
        (3,4,false,false),
        (4,4,false,false),
        (4,5,false,false),
        (5,5,false,false);



Select *
FROM books_users
INNER JOIN book_id;

Select *
FROM users
insert into