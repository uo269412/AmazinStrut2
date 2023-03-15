package com.miw.presentation.actions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import com.miw.model.Book;
import com.miw.model.ShoppingCart;
import com.opensymphony.xwork2.ActionSupport;

public class AddToShoppingCartAction extends ActionSupport implements SessionAware, ApplicationAware {

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private ShoppingCart shoppingcart = null;
	private Map<String, Object> session;
	private List<String> addedBooks;
	private Map<String, Object> application;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingcart;
	}

	public void setShoppingCart(ShoppingCart shoppingcart) {
		this.shoppingcart = shoppingcart;
	}

	public List<String> getAddedBooks() {
		return addedBooks;
	}

	@Override
	public String execute() throws Exception {
		if (session.get("shoppingcart") == null) {
			shoppingcart = new ShoppingCart();
		} else {
			shoppingcart = (ShoppingCart) session.get("shoppingcart");
		}
		for (String element : getAddedBooks()) {
			addElementToShoppingCart(element);

		}
		session.put("shoppingcart", shoppingcart);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private void addElementToShoppingCart(String element) {
		List<Book> books = (List<Book>) application.get("books");
		for (Book book : books) {
			if (book.getId() == Integer.parseInt(element)) {
				shoppingcart.add(book.getTitle());
				shoppingcart.addCost(book.getPrice());
				System.out.println(book.toString());
			}
		}
	}

	public void setAddedBooks(List<String> addedBooks) {
		this.addedBooks = addedBooks;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

}
