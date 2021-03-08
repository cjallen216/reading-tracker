package com.techelevator.dao;
import java.util.List;

import com.techelevator.model.Books;
public interface BooksDAO {
List<Books> listAll();

Books getBookbyISBN(String isbnNumber);

void createBook(String isbnNumber,String title,int authorPeopleId, String imgLink);
}
