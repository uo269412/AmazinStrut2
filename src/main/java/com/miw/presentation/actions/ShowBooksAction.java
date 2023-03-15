package com.miw.presentation.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.miw.model.Book;
import com.miw.model.ShoppingCart;
import com.miw.presentation.book.BookManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "miw.Amazin")

public class ShowBooksAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {

	private static final long serialVersionUID = -4752542581534740735L;
	Logger logger = LogManager.getLogger(this.getClass());
	Map<String, Object> request = null;
	Map<String, Object> application = null;
	Map<String, Object> session;

	public String execute() {
		logger.debug("Executing ShowBooksCommand");
		BookManagerServiceHelper helper = new BookManagerServiceHelper();
		try {
			List<Book> helperBooks = helper.getBooks();
			List<Book> booklist = new ArrayList<Book>();
			List<Book> books = new ArrayList<Book>();
			booklist.addAll(helperBooks);
			books.addAll(helperBooks);
			getUserBookList(booklist);
			application.put("books", books);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private void getUserBookList(List<Book> auxBooks) {
		List<Book> requestBooks = new ArrayList<Book>();
		if (session.get("shoppingcart") != null) {
			ShoppingCart shopcart = (ShoppingCart) session.get("shoppingcart");
			for (Book b : auxBooks) {
				int actualStock = b.getStock();
				if (shopcart.getList().containsKey(b.getTitle())) {
					actualStock -= shopcart.getList().get(b.getTitle());
				}
				if (actualStock > 0) {
					b.setStock(actualStock);
					calculatePrice(b);
					requestBooks.add(b);
				}
			}
		} else {
			for (Book b : auxBooks) {
				if (b.getStock() > 0) {
					calculatePrice(b);
					requestBooks.add(b);
				}
			}
		}
		session.put("booklist", requestBooks);
	}

	public void calculatePrice(Book book) {
		book.setPrice(book.getBasePrice() * (1 + book.getVat().getValue()));
		if (book.getStock() == 10) {
			book.setPrice(book.getPrice() - (book.getPrice() * 0.05));
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

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
