package com.miw.presentation.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;

import com.miw.business.VATDataServiceHelper;
import com.miw.model.Book;
import com.miw.model.VAT;
import com.miw.presentation.book.BookManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Validations(requiredFields = {
		@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "title", message = "You must enter a value for title."),
		@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "description", message = "You must enter a value for description."),
		@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "taxgroup", message = "You must enter a value for taxgroup."),
		@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "basePrice", message = "You must enter a value for basePrice.") })

public class AddBookAction extends ActionSupport implements ModelDriven<Book>, RequestAware, ApplicationAware {

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private Map<String, Object> request;
	private Map<String, Object> application;
	Book book = new Book();
	int taxgroup;

	@Override
	public void validate() {
		logger.debug("Invoking validate!");
		super.validate();

	}

	private boolean checkTaxGroup() {
		if (getTaxgroup() == 1 || getTaxgroup() == 2) {
			VAT vat = getVATFromTaxingGroup();
			if (vat != null) {
				book.setVat(vat);
				return true;
			} else {
				request.put("mymessageCreation", "Error obteniendo el VAT");
			}
		} else {
			request.put("mymessageCreation", "No se puede añadir un libro que no está en un taxgroup 1 o 2");
		}
		return false;
	}

//	@Override
//	public String execute() throws Exception {
//		if (checkIfBookTitleExists()) {
//			request.put("mymessageCreation", "Ya existe un libro con ese título en la base de datos");
//			return "creation-error";
//		}
//		if (!checkTaxGroup()) {
//			return "creation-error";
//		}
//		updateApplicationContext();
//		insertIntoDatabase();
//		request.put("mymessageCorrectCreation", "Se ha creado correctamente el libro " + book.getTitle());
//		return SUCCESS;
//	}

	@Override
	public String execute() throws Exception {
		List<Book> books = getBooks();
		if (checkIfBookTitleExists(books)) {
			request.put("mymessageCreation", "Ya existe un libro con ese título en la base de datos");
			return "creation-error";
		}
		if (!checkTaxGroup()) {
			return "creation-error";
		}
		//updateApplicationContext();
		books.add(book);
		insertIntoDatabase();
		request.put("mymessageCorrectCreation", "Se ha creado correctamente el libro " + book.getTitle());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		if (application.get("books") == null) {
			try {
				BookManagerServiceHelper helper = new BookManagerServiceHelper();
				books = helper.getBooks();
				application.put("books", books);
				return books;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (List<Book>) application.get("books");
	}
	
	private boolean checkIfBookTitleExists(List<Book> books) {
		for (Book b : books) {
			if (b.getTitle().equals(book.getTitle())) {
				return true;
			}
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	private void updateApplicationContext() {
		List<Book> books = new ArrayList<Book>();
		if (application.get("books") == null) {
			try {
				BookManagerServiceHelper helper = new BookManagerServiceHelper();
				books = helper.getBooks();
				application.put("books", books);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		books.add(book);
	}

	private boolean checkIfBookTitleExists() {
		logger.debug("Executing GetBookCommand");
		Optional<Book> result = Optional.empty();
		BookManagerServiceHelper helper = new BookManagerServiceHelper();
		try {
			result = helper.getBookByTitle(book.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.isPresent();
	}

//	private VAT getVATFromTaxingGroup() {
//		logger.debug("Executing GetVatCommand");
//		VAT result = null;
//		VATDataServiceHelper helper = new VATDataServiceHelper();
//		try {
//			result = helper.getVAT(getTaxgroup());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(result.toString());
//		return result;
//	}

	private VAT getVATFromTaxingGroup() {
		logger.debug("Executing GetVatCommand");
		VAT result = null;
		List<VAT> list = new ArrayList<VAT>();
		VATDataServiceHelper helper = new VATDataServiceHelper();
		try {
			list = helper.getVATs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (VAT vat : list) {
			if (vat.getId() == getTaxgroup()) {
				return vat;
			}
		}
		return result;
	}

	private void insertIntoDatabase() {
		this.book.setStock(10);
		logger.debug("Executing AddBookCommand");
		BookManagerServiceHelper helper = new BookManagerServiceHelper();
		try {
			helper.addBook(this.book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book getModel() {
		return this.book;
	}

	public int getTaxgroup() {
		return this.taxgroup;
	}

	public void setTaxgroup(int taxgroup) {
		this.taxgroup = taxgroup;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;

	}
}
