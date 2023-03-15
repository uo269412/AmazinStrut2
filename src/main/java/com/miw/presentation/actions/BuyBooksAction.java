package com.miw.presentation.actions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.miw.model.Book;
import com.miw.model.ShoppingCart;
import com.miw.presentation.book.BookManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;

public class BuyBooksAction extends ActionSupport implements ApplicationAware, RequestAware, SessionAware {

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private Map<String, Object> application;
	private Map<String, Object> session;
	private Map<String, Object> request;

	@Override
	public void validate() {
		logger.debug("Invoking validate!");
		super.validate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() {
		if (session.get("shoppingcart") != null) {
			ShoppingCart shopcart = (ShoppingCart) session.get("shoppingcart");
			List<Book> books = (List<Book>) application.get("books");
			for (Book b : books) {
				if (shopcart.getList().containsKey(b.getTitle())) {
					if (shopcart.getList().get(b.getTitle()) > b.getStock()) {
						request.put("purchaseError", "El libro " + b.getTitle() + " no tiene el stock necesario (tiene " + b.getStock() +" unidades)");
						return ERROR;
					}
				}
			}
			updateBookStock(books, shopcart);
			updateDatabase(books);
			session.put("shoppingcart", new ShoppingCart());
			return SUCCESS;
		}
		request.put("purchaseError", "No hay ningún elemento en el carrito de la compra");
		return ERROR;

	}

	private void updateDatabase(List<Book> books) {
		for (Book b : books) {
			updateBook(b);
		}

	}

	private void updateBook(Book book) {
		logger.debug("Executing UpdateBookCommand");
		BookManagerServiceHelper helper = new BookManagerServiceHelper();
		try {
			helper.updateBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateBookStock(List<Book> books, ShoppingCart shopcart) {
		for (Book b : books) {
			if (shopcart.getList().containsKey(b.getTitle())) {
				b.setStock(b.getStock() - shopcart.getList().get(b.getTitle()));
			}
		}
		application.put("books", books);

	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}

}