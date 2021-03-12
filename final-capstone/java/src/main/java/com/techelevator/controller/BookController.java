package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.BookDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Book;

@RequestMapping(path = "books/")
@RestController
@CrossOrigin
public class BookController {
	@Autowired
	private BookDAO booksDAO;
	private UserDAO userDAO;

	@PostMapping("")
	public void createBook(@Valid @RequestBody Book newBook, Principal currentUser) {
		int currentUserId = userDAO.findIdByUsername(currentUser.getName());
		booksDAO.createBook(newBook.getAuthor(), newBook.getIsbn(), newBook.getTitle(), newBook.getImgLink(), currentUserId);
	}
	
	@GetMapping("")
	public List<Book> listAll() {
		return booksDAO.listAll();	
	}	 
}
