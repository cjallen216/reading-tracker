BEGIN TRANSACTION;

--prizes-- 
ALTER TABLE prizes ADD CONSTRAINT fk_prizes_families FOREIGN KEY (family_id) REFERENCES families (family_id);

--prizes_users--  
ALTER TABLE prizes_users ADD CONSTRAINT fk_prizes_users_prizes FOREIGN KEY (prize_id) REFERENCES prizes (prize_id);

  ALTER TABLE prizes_users ADD CONSTRAINT fk_prizes_users_users FOREIGN KEY (user_id) REFERENCES users (user_id);

--families-- 
ALTER TABLE families ADD CONSTRAINT fk_families_users FOREIGN KEY (user_id) REFERENCES users (user_id);

--books_users-- 
ALTER TABLE books_users ADD CONSTRAINT fk_books_users_books  FOREIGN KEY (book_id)  REFERENCES books (book_id);

--books-- 
ALTER TABLE books  ADD CONSTRAINT fk_books_people  FOREIGN KEY (author_people_id)  REFERENCES people (people_id);

--reading_time 
ALTER TABLE reading_time ADD CONSTRAINT fk_time_users  FOREIGN KEY (user_id)  REFERENCES users (user_id);
ALTER TABLE reading_time ADD CONSTRAINT fk_time_books  FOREIGN KEY (book_id)  REFERENCES books (book_id);

--users--
--ALTER TABLE users ADD CONSTRAINT fk_users_people FOREIGN KEY (people_id)  REFERENCES people (people_id);
 
ROLLBACK TRANSACTION;
