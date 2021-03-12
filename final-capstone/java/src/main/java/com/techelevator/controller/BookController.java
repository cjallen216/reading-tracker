package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.BookDAO;
import com.techelevator.model.Book;
import com.techelevator.model.BookAlreadyExistsException;
import com.techelevator.model.User;

@RequestMapping(path = "books/")
@RestController
@CrossOrigin
public class BookController
{
	@Autowired
	private BookDAO booksDAO;

	@PostMapping("")
	public void createBook(@Valid @RequestBody Book newBook)
	{
		try
		{
			Book book = booksDAO.getBookByTitle(newBook.getTitle());
			throw new BookAlreadyExistsException();
		} catch (Exception e)
		{
			booksDAO.createBook(newBook.getTitle(), newBook.getIsbn(), newBook.getAuthor(), newBook.getImgLink());
		}
	}

	@GetMapping("")
	public List<Book> listAll()
	{
		return booksDAO.listAll();
	}

	@GetMapping("{user_id}/")
	public List<Book> getBooksByUserId(@PathVariable int user_id)
	{
		return booksDAO.getBooksByUserId(user_id);
	}

}
