package com.techelevator.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.BookDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Book;
import com.techelevator.model.BookList;

@RestController
@CrossOrigin
public class BookController
{
	@Autowired
	private BookDAO booksDAO;
	@Autowired
	private UserDAO userDAO;

	@PostMapping("/addBook")
	public ResponseEntity<Book> createBook(@RequestBody Book newBook, Principal currentUser)
	{	
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400 status code
		Book createdBook = null;
		int currentUserId = userDAO.findIdByUsername(currentUser.getName());
		int newBookUserId = -1;

		try {
			Book existingBook = booksDAO.getBookByTitle(newBook.getTitle());
			int bookUserId = booksDAO.getBookUserId(existingBook.getBookId(), currentUserId);
			
			if(bookUserId > -1) {
				status = HttpStatus.NO_CONTENT; // 204 status code
			} else {
				newBookUserId = booksDAO.insertBookUser(existingBook.getBookId(), currentUserId);
				
				if(newBookUserId > -1) {
					createdBook = existingBook;
					status = HttpStatus.CREATED; // 201 status code
				} else {
					status = HttpStatus.EXPECTATION_FAILED; //417 status code
				}				
			}				
		} catch (Exception e) {
			String expected = "Book title: " + newBook.getTitle() + " was not found.";
			if (e.getMessage().equals(expected)) {				
				createdBook = booksDAO.createBook(newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn(), newBook.getImgLink(), currentUserId);
				
				if(createdBook.getTitle().equals(newBook.getTitle())) {
					status = HttpStatus.CREATED; // 201 status code
				} else {
					status = HttpStatus.EXPECTATION_FAILED; //417 status code
				}
			}			
		}
			
		
		
		return new ResponseEntity<Book>(createdBook, status);
	}

	@GetMapping("/myBooks")
	public ResponseEntity<BookList> getBooksByUserId(Principal user)
	{	
		String userName = user.getName();
		int currentUserId = userDAO.findIdByUsername(userName);
		BookList bookList = new BookList(booksDAO.getBooksByUserId(currentUserId));
		return ResponseEntity.ok(bookList);
	}
	
	@PutMapping("/myBooks")
	public ResponseEntity<Book> updateBookDetails(Book bookToUpdate, String statusType, boolean value, Principal currentUser){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Book updatedBook = null;
		String userName = currentUser.getName();
		int currentUserId = userDAO.findIdByUsername(userName);
		
		if(statusType.equals("reading")) {
			bookToUpdate.setCurrentBook(value);
		} else if(statusType.equals("read")) {
			bookToUpdate.setCompleted(value);
		}

		int bookUserId = booksDAO.getBookUserId(bookToUpdate.getBookId(), currentUserId);	
			
		if(bookUserId != -1) {
			updatedBook = booksDAO.updateBook(bookToUpdate, currentUserId);
		}
		
		if(updatedBook != null) {
			status = HttpStatus.ACCEPTED;
		} else {
			status = HttpStatus.EXPECTATION_FAILED;
		}		
		return new ResponseEntity<Book>(updatedBook, status);
	}

}