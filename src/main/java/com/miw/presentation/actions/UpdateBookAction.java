package com.miw.presentation.actions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;

import com.miw.model.Book;
import com.miw.presentation.book.BookManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Validations(requiredFields = {
		@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "updatetitle", message = "You must enter a value for title."),
		@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "updatestock", message = "You must enter a value for stock.") })

public class UpdateBookAction extends ActionSupport implements RequestAware, ApplicationAware {

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private Map<String, Object> request;
	private Map<String, Object> application;
	String updatetitle;
	int updatestock;

	@Override
	public void validate() {
		logger.debug("Invoking validate!");
		super.validate();
	}

	@Override
	public String execute() throws Exception {
		boolean bookUpdated = false;
		if (inputStockWrong()) {
			return "update-error";
		}
		BookManagerServiceHelper helper = new BookManagerServiceHelper();
		try {
			List<Book> books = helper.getBooks();
			for (Book book : books) {
				if (book.getTitle().equals(getUpdatetitle())) {
					book.setStock(getUpdatestock());
					updateBook(book);
					bookUpdated = true;
				}
			}
			application.put("books", books);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!bookUpdated) {
			request.put("mymessageUpdate", "No existe ningún libro con ese título en la base de datos");
			return "update-error";
		}
		request.put("mymessageCorrectUpdate", "Se ha actualizado correctamente el libro");
		return SUCCESS;
	}

	private boolean inputStockWrong() {
		if (getUpdatestock() < 0 || getUpdatestock() > 10) {
			request.put("mymessageUpdate", "Las unidades del libro no pueden ser inferiores a 0 ni superiores a 10");
			return true;
		}
		return false;
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

	public String getUpdatetitle() {
		return this.updatetitle;
	}

	public void setUpdatetitle(String updatetitle) {
		this.updatetitle = updatetitle;
	}

	public int getUpdatestock() {
		return this.updatestock;
	}

	public void setUpdatestock(int updatestock) {
		this.updatestock = updatestock;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;

	}
}
