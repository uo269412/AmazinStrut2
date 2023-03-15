
package com.miw.business.bookmanager;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.miw.business.BookDataServiceHelper;
import com.miw.model.Book;

public class BookManager implements BookManagerService {
	Logger logger = LogManager.getLogger(this.getClass());

	public List<Book> getBooks() throws Exception {
		logger.debug("Asking for books");
		List<Book> books = (new BookDataServiceHelper()).getBooks();

		for (Book book : books) {
			calculatePrice(book);
		}

		// We calculate the final price with the VAT value
		return books;
	}

	public void calculatePrice(Book book) {
		book.setPrice(book.getBasePrice() * (1 + book.getVat().getValue()));
		if (book.getStock() == 10) {
			book.setPrice(book.getPrice()- (book.getPrice() * 0.05));
			book.setMessage("¡Oferta!");
		} else if (book.getStock() < 3 && book.getStock() > 0) {
			book.setPrice(book.getPrice() + (book.getPrice() * 0.05));
			book.setMessage("¡Últimas unidades!");
		} else {
			book.setPrice(book.getPrice());
			book.setMessage("");
		}
		book.setPrice(Math.round(book.getPrice() * 100.0) / 100.0);
	}

	public Book getSpecialOffer() throws Exception {
		List<Book> books = getBooks();
		int number = (int) (Math.random() * books.size());
		logger.debug("Applying disccount to " + books.get(number).getTitle());
		books.get(number).setPrice((double) books.get(number).getPrice() * 0.85);
		return books.get(number);
	}

	@Override
	public void addBook(Book book) throws Exception {
		logger.debug("Inserting book");
		calculatePrice(book);
		(new BookDataServiceHelper()).addBook(book);

	}

	@Override
	public Optional<Book> getBook(int id) throws Exception {
		logger.debug("Getting book with id " + id);
		return (new BookDataServiceHelper()).getBook(id);
	}

	@Override
	public void updateBook(Book book) throws Exception {
		logger.debug("Updating book  " + book.toString());
		calculatePrice(book);
		(new BookDataServiceHelper()).updateBook(book);
	}

	@Override
	public Optional<Book> getBookByTitle(String title) throws Exception {
		logger.debug("Getting book with title " + title);
		return (new BookDataServiceHelper()).getBookByTitle(title);
	}
}
