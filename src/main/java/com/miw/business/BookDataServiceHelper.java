package com.miw.business;

import java.util.List;
import java.util.Optional;

import com.miw.infrastructure.Factories;
import com.miw.model.Book;

public class BookDataServiceHelper {

	public List<Book> getBooks() throws Exception {
		return (Factories.dataServices.getBookDataService()).getBooks();
	}

	public void addBook(Book book) throws Exception {
		(Factories.dataServices.getBookDataService()).addBook(book);
		
	}
	
	public Optional<Book> getBook(int id) throws Exception {
		return (Factories.dataServices.getBookDataService()).getBook(id);
	}

	public void updateBook(Book book) throws Exception {
		Factories.dataServices.getBookDataService().updateBook(book);
	}

	public Optional<Book> getBookByTitle(String title) {
		return (Factories.dataServices.getBookDataService()).getBookByTitle(title);
	}
}
