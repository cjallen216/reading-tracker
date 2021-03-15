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
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Book createdBook = null;

		try {
			Book duplicate = booksDAO.getBookByTitle(newBook.getTitle());
			boolean isDuplicate = duplicate.getTitle().equals(newBook.getTitle());
			
			if(isDuplicate) {
				status = HttpStatus.CONFLICT;
			}
		} catch (RuntimeException e) {
			String expected = "Book title: " + newBook.getTitle() + " was not found.";
			if (e.getMessage().equals(expected)) {
				int currentUserId = userDAO.getUserIdByUsername(currentUser.getName());
				createdBook = booksDAO.createBook(newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn(), newBook.getImgLink(), currentUserId);
			
				if(createdBook.getTitle() == newBook.getTitle()) {
					status = HttpStatus.CREATED;
				} else {
					status = HttpStatus.EXPECTATION_FAILED;
				}
			} 
		}
		return new ResponseEntity<Book>(createdBook, status);
	}

	@GetMapping("/myBooks")
	public ResponseEntity<BookList> getBooksByUserId(Principal user)
	{	
		String userName = user.getName();
		int currentUserId = userDAO.getUserIdByUsername(userName);
		BookList bookList = new BookList(booksDAO.getBooksByUserId(currentUserId));
		return ResponseEntity.ok(bookList);
	}
	
	@PutMapping("/myBooks")
	public ResponseEntity<Book> updateBookDetails(Book book, String statusType, boolean value, Principal user){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Book updatedBook = null;
		String userName = user.getName();
		int userId = userDAO.getUserIdByUsername(userName);
		
		if(statusType.equals("current")) {
			book.setCurrentBook(value);
		} else if(statusType.equals("read")) {
			book.setCompleted(value);
		}

		try {
			Book bookToUpdate = booksDAO.getBookByTitle(book.getTitle());
			boolean isUpdatable = bookToUpdate.getTitle().equals(book.getTitle());			
			
			if(isUpdatable) {
				updatedBook = booksDAO.updateBook(book, userId);
			}
			
			if(updatedBook != null) {
				status = HttpStatus.ACCEPTED;
			} else {
				status = HttpStatus.EXPECTATION_FAILED;
			}
		} catch (RuntimeException e) {
			String expected = "Book title: " + book.getTitle() + " was not found.";
			if (e.getMessage().equals(expected)) {				
				status = HttpStatus.EXPECTATION_FAILED;				
			} 
		}		
		return new ResponseEntity<Book>(updatedBook, status);
	}

}