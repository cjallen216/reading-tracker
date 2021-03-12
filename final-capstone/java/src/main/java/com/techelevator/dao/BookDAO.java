package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Book;

public interface BookDAO {
<<<<<<< HEAD
	public boolean createBook(String author, String isbn, String title, String imgLink, int booksUsersId, int bookId,
			int userId, boolean currentBook, boolean completed);

	public List<Book> listAll();

=======
	public boolean createBook(String author, String isbn, String title, String imgLink, int currentUserId);
	public List<Book> listAll();
>>>>>>> 00b14d7583174423972eb2ddfdbcc059cbc0e7d0
	public Book getBookByID(int book_id);
}
