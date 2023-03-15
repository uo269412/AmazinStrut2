package com.miw.presentation.book;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.miw.infrastructure.Factories;
import com.miw.model.Book;

public class BookManagerServiceHelper {

	Logger logger = LogManager.getLogger(this.getClass());

	public List<Book> getBooks() throws Exception {
		logger.debug("Retrieving Books from Business Layer");
		return (Factories.services.getBookManagerService()).getBooks();
	}

	public Book getSpecialOffer() throws Exception {
		logger.debug("Retrieving Special Offer from Business Layer");
		return (Factories.services.getBookManagerService()).getSpecialOffer();
	}
	
	public void addBook(Book book) throws Exception {
		logger.debug("Adding book");
		(Factories.services.getBookManagerService()).addBook(book);;
	}
	
	public Optional<Book> getBook(int i) throws Exception {
		logger.debug("Retrieving Book");
		return (Factories.services.getBookManagerService()).getBook(i);
	}
	
	public void updateBook(Book book) throws Exception {
		logger.debug("Updating book");
		(Factories.services.getBookManagerService()).updateBook(book);;
	}

	public Optional<Book> getBookByTitle(String title) throws Exception {
		logger.debug("Retrieving Book");
		return (Factories.services.getBookManagerService()).getBookByTitle(title);
	}
	
}
