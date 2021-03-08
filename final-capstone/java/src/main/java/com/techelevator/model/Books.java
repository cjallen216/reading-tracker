package com.techelevator.model;

public class Books {
	private int bookId;

	private String isbnNumber;
	private String title;
	private int authorPeopleId;
	private String imgLink;

	public Books() {
	}

	public Books(int bookId, String isbnNumber, String title, int authorPeopleId, String imgLink) {
		super();
		this.bookId = bookId;
		this.isbnNumber = isbnNumber;
		this.title = title;
		this.authorPeopleId = authorPeopleId;
		this.imgLink = imgLink;
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

	public int getAuthorPeopleId() {
		return authorPeopleId;
	}

	public void setAuthorPeopleId(int authorPeopleId) {
		this.authorPeopleId = authorPeopleId;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

}
