package com.techelevator.dao;

public interface BookDAO {
public void createBook(String firstName, String lastName, String isbnNumber, String title, String imgLink, int authorId);
}
