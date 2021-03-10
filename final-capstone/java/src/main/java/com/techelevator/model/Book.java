package com.techelevator.model;

public class Book {
	private int bookId;
	private String isbn;
	private String title;
	private String author;
	private String imgLink;	
	
	public Book(int bookId, String isbn, String title, String author, String imgLink) {
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.imgLink = imgLink;	
	}

	public Book(String isbn, String title, String author, String imgLink) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.imgLink = imgLink;	
	}
	
	public Book () {}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public String getisbn() {
		return isbn;
	}
	
	public void setisbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getImgLink() {
		return imgLink;
	}
	
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
}
