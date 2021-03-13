package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Book;

@Service
public class BookSqlDAO implements BookDAO
{
	private JdbcTemplate jdbcTemplate;

	public BookSqlDAO(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean createBook(String title, String author, String isbn, String imgLink, int currentUserId) {
		String insertBook = "INSERT INTO books (title, author, isbn, cover_img_link) VALUES (?,?,?,?)";
		GeneratedKeyHolder bookKeyHolder = new GeneratedKeyHolder();
		String book_id_column = "book_id";
		boolean bookCreated = false;
		
		bookCreated = jdbcTemplate.update(connection -> {
			PreparedStatement prepared = connection.prepareStatement(insertBook, new String[] { book_id_column });
			prepared.setString(1, title);
			prepared.setString(2, author);
			prepared.setString(3, isbn);
			prepared.setString(4, imgLink);
			return prepared;
		}, bookKeyHolder) == 1;
		int newBookId = (int) bookKeyHolder.getKeys().get(book_id_column);

		String insertBookUser = "INSERT INTO books_users (user_id, book_id, current_book, completed) VALUES (?,?,?,?)";
		GeneratedKeyHolder bookUserKeyHolder = new GeneratedKeyHolder();
		String book_user_id_column = "books_users_id";
		boolean bookUserCreated = false;
		
		bookUserCreated = jdbcTemplate.update(connection -> {
			PreparedStatement prepared = connection.prepareStatement(insertBookUser, new String[] { book_user_id_column });
			prepared.setInt(1, currentUserId);
			prepared.setInt(2, newBookId);
			prepared.setBoolean(3, false);
			prepared.setBoolean(4, false);
			return prepared;
		}, bookUserKeyHolder) == 1;
		int newBookUserId = (int) bookUserKeyHolder.getKeys().get(book_user_id_column);
		
		return (bookCreated && bookUserCreated);
	}

	@Override
	public List<Book> listAll()
	{
		List<Book> books = new ArrayList<>();
		String sql = "SELECT book_id " +
						"	, isbn " +
						"	, title " +
						"	, author " +
						"	, cover_img_link " +
					"FROM books ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next())
		{
			Book book = mapRowToBook(results);
			books.add(book);
		}
		return books;
	}

	@Override
	public Book getBookByID(int bookId)
	{
		String sql = "SELECT book_id " +
						"	, isbn " +
						"	, title " +
						"	, author " +
						"	, cover_img_link " +
					"FROM books " +
					"WHERE book_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bookId);
		if (results.next())
		{
			return mapRowToBook(results);
		} else
		{
			throw new RuntimeException("Book ID " + bookId + " was not found.");
		}
	}

	@Override
	public List<Book> getBooksByUserId(int user_id)
	{
		List<Book> books = new ArrayList<>();
		String sql = "SELECT b.book_id " + 
						"	, u.user_id " +
						"	, b.isbn " +
						"	, b.title " +
						"	, b.author " +
						"	, b.cover_img_link "	+
					"FROM books AS b " +
					"INNER JOIN books_users AS bu " +
					"	ON b.book_id = bu.book_id " +
					"INNER JOIN users AS u " +
					"	ON bu.user_id = u.user_id " +
					"WHERE u.user_id = ?;";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
		while (results.next())
		{
			Book book = mapRowToBook(results);
			books.add(book);
		}
		return books;
	}

	public Book getBookByIsbn(String isbn)
	{
		String sql = "SELECT book_id " + 
				"        , isbn " + 
				"        , title " + 
				"        , author " + 
				"        , cover_img_link " + 
				"FROM books " + 
				"WHERE isbn = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, isbn);
		if (results.next())
		{
			return mapRowToBook(results);
		} else
		{
			throw new RuntimeException("Book isbn " + isbn + " was not found.");
		}
	}
	
	@Override
	public Book getBookByTitle(String title)
	{
		String sql = "SELECT book_id " + 
				"        , isbn " + 
				"        , title " + 
				"        , author " + 
				"        , cover_img_link " + 
				"FROM books " + 
				"WHERE title = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, title);
		if (results.next())
		{
			return mapRowToBook(results);
		} else
		{
			throw new RuntimeException("Book title: " + title + " was not found.");
		}
	}

	private Book mapRowToBook(SqlRowSet rs)
	{
		Book book = new Book();
		book.setBookId(rs.getInt("book_id"));
		book.setIsbn(rs.getString("isbn"));
		book.setAuthor(rs.getString("author"));
		book.setTitle(rs.getString("title"));
		book.setImgLink(rs.getString("cover_img_link"));
		return book;
	}
}
