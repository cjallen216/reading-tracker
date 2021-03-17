package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Book;

public interface BookDAO
{
	public Book createBook(Book bookToCreate, int currentUserId);

	public List<Book> listAll();

	public Book getBookByID(int book_id);	
	
	public List<Book> getBooksByUserId(int user_id);

	public Book getBookByIsbn(String isbn);
	
	public Book getBookByTitle(String title);
	
	public int getBookUserId(int book_id, int user_id);
	
	public Book updateReaderDetails(Book book, int userId);
	
	public boolean checkForDuplicateBook(Book bookToCreate, int userId);
}
