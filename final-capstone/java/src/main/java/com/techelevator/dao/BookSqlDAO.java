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
public class BookSqlDAO implements BookDAO {
	private JdbcTemplate jdbcTemplate;

	public BookSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Book createBook(Book bookToCreate, int userId) {
		boolean isExisting = bookToCreate.getBookId() > 0;
		String title = bookToCreate.getTitle();
		String author = bookToCreate.getAuthor();
		String isbn = bookToCreate.getIsbn();
		String imgLink = bookToCreate.getImgLink();
		
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
		
		int bookUserCreated = insertBookUser(newBookId, userId);

		Book newBook = null;
		
		if (bookCreated == true && bookUserCreated > 0) {
			newBook = getBookByTitle(title);
		}
		return newBook;
	}
	
	private int insertBookUser(int bookId, int userId) {
		String insertBookUser = "INSERT INTO books_users (user_id, book_id, read, reading) VALUES (?,?,?,?)";
		GeneratedKeyHolder bookUserKeyHolder = new GeneratedKeyHolder();
		String book_user_id_column = "books_users_id";
		boolean bookUserCreated = false;
		int newBookUserId = 0;
		
		bookUserCreated = jdbcTemplate.update(connection -> {
			PreparedStatement prepared = connection.prepareStatement(insertBookUser, new String[] { book_user_id_column });
			prepared.setInt(1, userId);
			prepared.setInt(2, bookId);
			prepared.setBoolean(3, false);
			prepared.setBoolean(4, false);
			return prepared;
		}, bookUserKeyHolder) == 1;
		newBookUserId = (int) bookUserKeyHolder.getKeys().get(book_user_id_column);
		
		return newBookUserId;
	}
	
	public boolean checkForDuplicateBook(Book bookToCreate, int userId) {
		boolean isDuplicate = false;
		int bookId = bookToCreate.getBookId();
		int bookUserId = 0;
		
		if(bookId != 0) {
			bookUserId = getBookUserId(bookId, userId);
			
			if(bookUserId != 0) {
				isDuplicate = true;
			}		
		}
		
		return isDuplicate;
	}
	
	@Override
	public List<Book> listAll()	{
		List<Book> books = new ArrayList<>();
		String sql = "SELECT b.book_id "
				+ "        , isbn "
				+ "        , title "
				+ "        , author "
				+ "        , cover_img_link "
				+ "        , read "
				+ "        , reading "
				+ "FROM books AS b "
				+ "JOIN books_users AS bu  "
				+ "        ON b.book_id = bu.book_id;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next())
		{
			Book book = mapRowToBook(results);
			books.add(book);
		}
		return books;
	}

	@Override
	public Book getBookByID(int bookId)	{
		String sql = "SELECT b.book_id "
				+ "        , isbn "
				+ "        , title "
				+ "        , author "
				+ "        , cover_img_link "
				+ "        , read "
				+ "        , reading "
				+ "FROM books AS b "
				+ "JOIN books_users AS bu  "
				+ "        ON b.book_id = bu.book_id "
				+ "WHERE b.book_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bookId);
		if (results.next()){
			return mapRowToBook(results);
		} else {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByUserId(int user_id)	{
		List<Book> books = new ArrayList<>();
		String sql = "SELECT b.book_id "
				+ "        , isbn "
				+ "        , title "
				+ "        , author "
				+ "        , cover_img_link "
				+ "        , read "
				+ "        , reading "
				+ "FROM books AS b "
				+ "JOIN books_users AS bu  "
				+ "        ON b.book_id = bu.book_id "
				+ "WHERE bu.user_id = ? "
				+ "ORDER BY title;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
		while (results.next()) {
			Book book = mapRowToBook(results);
			books.add(book);
		}
		return books;
	}

	public Book getBookByIsbn(String isbn) {
		String sql = "SELECT b.book_id "
				+ "        , isbn "
				+ "        , title "
				+ "        , author "
				+ "        , cover_img_link "
				+ "        , read "
				+ "        , reading "
				+ "FROM books AS b "
				+ "JOIN books_users AS bu  "
				+ "        ON b.book_id = bu.book_id "
				+ "WHERE isbn = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, isbn);
		if (results.next())	{
			return mapRowToBook(results);
		} else	{
			return null;
		}
	}
	
	@Override
	public Book getBookByTitle(String title) {
		String sql = "SELECT b.book_id "
				+ "        , isbn "
				+ "        , title "
				+ "        , author "
				+ "        , cover_img_link "
				+ "        , read "
				+ "        , reading "
				+ "FROM books AS b "
				+ "JOIN books_users AS bu  "
				+ "        ON b.book_id = bu.book_id "
				+ "WHERE title = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, title);
		if (results.next())	{
			return mapRowToBook(results);
		} else	{
			return null;
		}
	}
	
	@Override
	public int getBookUserId(int book_id, int user_id) {
		int booksUsersId = 0;
		String sql = "SELECT books_users_id " 
				+ "FROM books_users "
				+ "WHERE book_id = ? "
				+ "AND user_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, book_id, user_id);

		while(results.next()) {
			booksUsersId  = results.getInt("books_users_id");
		} 

		return booksUsersId;
	}
	
	public Book updateReaderDetails(Book book, int userId) {
		int bookId = book.getBookId();
		boolean reading = book.getReading();
		boolean read = book.getRead();
		
		String sql = "UPDATE books_users "
				+ "SET reading = ? "
				+ ", read = ? "
				+ "WHERE user_id = ? "
				+ "	AND book_id = ?;";		
		jdbcTemplate.update(sql,reading, read, userId, bookId);
		return getBookByTitle(book.getTitle());		
	}

	private Book mapRowToBook(SqlRowSet rs)	{
		Book book = new Book();
		book.setBookId(rs.getInt("book_id"));
		book.setIsbn(rs.getString("isbn"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setImgLink(rs.getString("cover_img_link"));
		book.setRead(rs.getBoolean("read"));
		book.setReading(rs.getBoolean("reading"));
		return book;
	}
}
