package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Book;

public interface BookDAO {
	public void createBook(String firstName, String lastName, String isbnNumber, String title, String imgLink,
			int authorId);
public List<Book> listAll();
}
