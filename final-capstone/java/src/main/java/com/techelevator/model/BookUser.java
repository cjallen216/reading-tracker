package com.techelevator.model;

public class BookUser {
private int bookUserID;
private int bookId;
private int userId;
private boolean currentBook;
private boolean completed;

public BookUser() {
	
}
public BookUser(int bookUserID, int bookId, int userId, boolean currentBook, boolean completed) {
	super();
	this.bookUserID = bookUserID;
	this.bookId = bookId;
	this.userId = userId;
	this.currentBook = currentBook;
	this.completed = completed;
}

public int getBookUserID() {
	return bookUserID;
}
public void setBookUserID(int bookUserID) {
	this.bookUserID = bookUserID;
}
public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public boolean isCurrentBook() {
	return currentBook;
}
public void setCurrentBook(boolean currentBook) {
	this.currentBook = currentBook;
}
public boolean isCompleted() {
	return completed;
}
public void setCompleted(boolean completed) {
	this.completed = completed;
}


}
