package com.techelevator.dao;
import java.util.List;

import com.techelevator.model.Books;
public interface BooksDAO {
List<Books> listAll(int accountID);

Books getBookbyISBN(String isbnNumber);

void createBook(String isbnNumber,String title);
}
