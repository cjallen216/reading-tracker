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
<<<<<<< HEAD
	public boolean createBook(String author, String isbn, String title, String imgLink, int booksUsersId, int bookId, int userId, boolean currentBook, boolean completed) {
=======
	public boolean createBook(String author, String isbn, String title, String imgLink, int currentUserId) {
>>>>>>> 00b14d7583174423972eb2ddfdbcc059cbc0e7d0
		String insertBook = "INSERT INTO books (isbn, title, author, cover_img_link) VALUES (?,?,?,?)";
		GeneratedKeyHolder bookKeyHolder = new GeneratedKeyHolder();
		String book_id_column = "book_id";
		boolean bookCreated = false;
		
		bookCreated = jdbcTemplate.update(connection -> {
			PreparedStatement prepared = connection.prepareStatement(insertBook, new String[] { book_id_column });
			prepared.setString(1, isbn);
			prepared.setString(2, title);
			prepared.setString(3, author);
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
		
<<<<<<< HEAD
		String bookStuff = "Insert into books_users (book_id, user_id,current_book, completed) values (?,?,?,?)";
		jdbcTemplate.update(bookStuff, )
		
=======
		return (bookCreated && bookUserCreated);
>>>>>>> 00b14d7583174423972eb2ddfdbcc059cbc0e7d0
	}

	@Override
	public List<Book> listAll() {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT book_id " + "	, isbn " + " , title " + "	, author " + "	, cover_img_link "
				+ "FROM books ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Book book = mapRowToBook(results);
			books.add(book);
		}
		return books;
	}

	private Book mapRowToBook(SqlRowSet rs) {

		Book book = new Book();
		book.setBookId(rs.getInt("book_id"));
		book.setIsbn(rs.getString("isbn"));
		book.setAuthor(rs.getString("author"));
		book.setTitle(rs.getString("title"));
		book.setImgLink(rs.getString("cover_img_link"));
		return book;
	}

	@Override
	public Book getBookByID(int bookId) {
		String sql = "SELECT book_id "
				+ "	, isbn "
				+ " , title "
				+ "	, author "
				+ "	, cover_img_link "
				+ "FROM books "
				+ "WHERE book_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bookId);
		if(results.next()) {
			return mapRowToBook(results);			
		} else {
			throw new RuntimeException("Book ID "+ bookId +" was not found.");
		}
}}
