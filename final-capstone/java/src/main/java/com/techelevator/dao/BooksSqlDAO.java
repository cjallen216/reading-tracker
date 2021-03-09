package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Books;

public class BooksSqlDAO implements BooksDAO {

	@Override
	public List<Books> listAll(int accountID) {
		return null;
	}

	@Override
	public Books getBookbyISBN(String isbnNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBook(String isbnNumber, String title) {
		String sql = "Insert into books"
				+ "("
				+ "isbn_number, title"
				+ ")"
				+ "Values (?, ?)";
	}

}
