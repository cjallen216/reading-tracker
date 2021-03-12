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
	public boolean createBook(String author, String isbn, String title, String imgLink)
	{
		String insertBook = "INSERT INTO books (isbn, title, author, cover_img_link) VALUES (?,?,?,?)";
		GeneratedKeyHolder bookKeyHolder = new GeneratedKeyHolder();
		String book_id_column = "book_id";
		boolean bookCreated = false;
		bookCreated = jdbcTemplate.update(con ->
		{
			PreparedStatement prepared = con.prepareStatement(insertBook, new String[]
			{ book_id_column });
			prepared.setString(1, isbn);
			prepared.setString(2, title);
			prepared.setString(3, author);
			prepared.setString(4, imgLink);
			return prepared;
		}, bookKeyHolder) == 1;
		int newBookId = (int) bookKeyHolder.getKeys().get(book_id_column);
		return bookCreated;
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
		String sql = "SELECT b.book_id\r\n" + 
						"	, u.user_id\r\n" +
						"	, b.isbn\r\n" +
						"	, b.title\r\n" +
						"	, b.author\r\n" +
						"	, b.cover_img_link\r\n"	+
					"FROM books AS b\r\n" +
					"INNER JOIN books_users AS bu\r\n" +
					"	ON b.book_id = bu.book_id\r\n" +
					"INNER JOIN users AS u\r\n" +
					"	ON bu.user_id = u.user_id\r\n" +
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
		String sql = "SELECT book_id\r\n" + 
				"        , isbn\r\n" + 
				"        , title\r\n" + 
				"        , author\r\n" + 
				"        , cover_img_link\r\n" + 
				"FROM books\r\n" + 
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
		String sql = "SELECT book_id\r\n" + 
				"        , isbn\r\n" + 
				"        , title\r\n" + 
				"        , author\r\n" + 
				"        , cover_img_link\r\n" + 
				"FROM books\r\n" + 
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
