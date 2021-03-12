package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Book;

public interface BookDAO {
	public boolean createBook(String author, String isbn, String title, String imgLink, int currentUserId);
	public List<Book> listAll();
	public Book getBookByID(int book_id);
}
