select book_id
        , isbn
        , title
        , author
        , cover_img_link
From books

/*
Select *
FROM books_users


UPDATE books
        set author_person_id = 1
WHERE title = 'green eggs and ham';

INSERT INTO books_users (book_id, user_id, current_book, completed)
values (1,2,false,false),
        (2,2,false,false),
        (2,1,false,false),
        (3,1,false,false),
        (3,2,false,false),
        (4,1,false,false),
        (4,2,false,false),
        (5,1,false,false);
        
INSERT INTO books (title, author)
VALUES
        ('The Velveteen Rabbit', 'Margery', 'Williams'),
        ('The Hobbit', 'J. R. R.', 'Tolkien'),
        ('A Wrinkle In Time', 'Madeleine', 'L''Engle'),
        ('Cracking The Coding Interview', 'Gayle', 'Laakmann McDowell'),
        ('Turtles All The Way Down', 'John', 'Green'),
        ('Postcards From The Edge', 'Carrie', 'Fisher');

select * from books
*/