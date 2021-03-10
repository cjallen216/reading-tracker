package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.BookDAO;
import com.techelevator.model.Book;

@RequestMapping(path = "books/")
@RestController
@CrossOrigin
public class BookController {
	@Autowired
	private BookDAO booksDAO;

	@PostMapping("")
	public void createBook(@Valid @RequestBody Book newBook) {
		booksDAO.createBook(newBook.getFirstName(), newBook.getLastName(), newBook.getIsbnNumber(), newBook.getTitle(), newBook.getImgLink(), newBook.getAuthorId());
	}
	
	@GetMapping("")
	public List<Book> listAll() {
		return booksDAO.listAll();
		
	
	}
	 
}
