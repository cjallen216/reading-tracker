package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.BookDAO;
import com.techelevator.dao.UserDAO;
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
	@Autowired
	private UserDAO userDAO;

	@PostMapping("")
	public void createBook(@Valid @RequestBody Book newBook, Principal currentUser)
	{
		try {
			Book book = booksDAO.getBookByTitle(newBook.getTitle());
			
			if (book !=null) {
				throw new BookAlreadyExistsException();
			}
		}
		catch (Exception e) {
			int currentUserId = userDAO.findIdByUsername(currentUser.getName());
			booksDAO.createBook(newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn(), newBook.getImgLink(), currentUserId);
		}
		
//		Book book = booksDAO.getBookByTitle(newBook.getTitle());
//			if (book !=null) {
//				throw new BookAlreadyExistsException();
//			}
//			else {
//				int currentUserId = userDAO.findIdByUsername(currentUser.getName());
//				booksDAO.createBook(newBook.getTitle(), newBook.getIsbn(), newBook.getAuthor(), newBook.getImgLink(), currentUserId);
//		}
	}

	//@GetMapping("")
	//public List<Book> listAll()
	//{
		//return booksDAO.listAll();
	//}

	@GetMapping("")
	public List<Book> getBooksByUserId(Principal currentUser)
	{
		int currentUserId = userDAO.findIdByUsername(currentUser.getName());
		return booksDAO.getBooksByUserId(currentUserId);
	}

}
