package com.techelevator.model;

public class Book {
private int bookId;
private String isbnNumber;
private String title;
private int AuthorId;
private String ImgLink;
private String firstName;
private String lastName;


public Book(int bookId, String isbnNumber, String title, int authorId, String imgLink, String firstName,
		String lastName) {
	super();
	this.bookId = bookId;
	this.isbnNumber = isbnNumber;
	this.title = title;
	AuthorId = authorId;
	ImgLink = imgLink;
	this.firstName = firstName;
	this.lastName = lastName;
}
public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
public String getIsbnNumber() {
	return isbnNumber;
}
public void setIsbnNumber(String isbnNumber) {
	this.isbnNumber = isbnNumber;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getAuthorId() {
	return AuthorId;
}
public void setAuthorId(int authorId) {
	AuthorId = authorId;
}
public String getImgLink() {
	return ImgLink;
}
public void setImgLink(String imgLink) {
	ImgLink = imgLink;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}



}
