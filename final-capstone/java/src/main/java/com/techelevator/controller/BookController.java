package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.BookDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Book;

@RestController
@CrossOrigin
public class BookController
{
	@Autowired
	private BookDAO booksDAO;
	@Autowired
	private UserDAO userDAO;

	@PostMapping("/addBook")
	public ResponseEntity<String> createBook(@RequestBody Book newBook, Principal currentUser)
	{
		HttpStatus status = HttpStatus.BAD_REQUEST;
		boolean bookCreated = false;

		try {
			Book duplicate = booksDAO.getBookByTitle(newBook.getTitle());
			boolean isDuplicate = duplicate.getTitle().equals(newBook.getTitle());
			
			if(isDuplicate) {
				status = HttpStatus.CONFLICT;
			}
		} catch (RuntimeException e) {
			String expected = "Book title: " + newBook.getTitle() + " was not found.";
			if (e.getMessage().equals(expected)) {
				int currentUserId = userDAO.findIdByUsername(currentUser.getName());
				bookCreated = booksDAO.createBook(newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn(), newBook.getImgLink(), currentUserId);
			
				if(bookCreated == true) {
					System.out.println("----------------------------- Book Created == true");
					status = HttpStatus.CREATED;
				} else {
					status = HttpStatus.EXPECTATION_FAILED;
				}
			} 
		}		
		return new ResponseEntity<String>(status);
	}

	@GetMapping("")
	public List<Book> getBooksByUserId(Principal currentUser)
	{
		int currentUserId = userDAO.findIdByUsername(currentUser.getName());
		return booksDAO.getBooksByUserId(currentUserId);
	}

}