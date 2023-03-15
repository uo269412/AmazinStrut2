package com.miw.presentation.actions;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.miw.model.User;
import com.miw.presentation.user.UserManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Results({ @Result(name = "success", location = "counter.action", type = "redirectAction"),
		@Result(name = "login-error", location = "/index.jsp"),
		@Result(name = "captcha-error", location = "/index.jsp"),

		// For validation
		@Result(name = "input", location = "/index.jsp")

})

@Validations(requiredStrings = {
		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "login", message = "You must enter a value for login."),
		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "password", message = "You must enter a value for password."),
		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "name", message = "You must enter a value for name."),
		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "dni", message = "You must enter a value for dni") }, 
			stringLengthFields = {
				@StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "9", maxLength = "9", fieldName = "registrodni", message = "El dni tiene que tener 9 caracteres"),
				@StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "5", maxLength = "10", fieldName = "password", message = "Password must have be least 5 chars long")})

public class RegisterAction extends ActionSupport implements ModelDriven<User>, SessionAware, RequestAware {

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private Map<String, Object> session = null;
	private Map<String, Object> request;
	User user = new User();

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void validate() {
		logger.debug("Invoking validate!");
		super.validate();
		checkDNIFormat();
	}

	private void checkDNIFormat() {

		char lastLetter = user.getDni().charAt(user.getDni().length() - 1);
		if (!Character.isLetter(lastLetter)) {
			addFieldError("dni", "El DNI no tiene como último caracter una letra");
		} else {
			try {
				int digits = Integer.parseInt(user.getDni().substring(0, user.getDni().length() - 1));
				checkDNILetter(digits);
			} catch (NumberFormatException ex) {
				addFieldError("dni", "No se han introducido 8 dígitos en el DNI");
			}
		}
	}

	private void checkDNILetter(int digits) {
		String expectedLetter = getCharForNumber(digits % 23);
		if (!expectedLetter.equalsIgnoreCase(user.getDni().substring(user.getDni().length() - 1))) {
			addFieldError("dni", "La letra no se corresponde con lo esperado (se espera la letra " + expectedLetter + ")");
		}

	}

	private String getCharForNumber(int i) {
		return i > 0 && i < 27 ? String.valueOf((char) (i + 'A' - 1)) : null;
	}

	@Override
	public String execute() throws Exception {
		if (checkIfUserExists()) {
			request.put("mymessageRegister", "El usuario ya existe dentro del sistema");
			return "login-error";
		}
		session.put("loginInfo.login", user.getLogin());
		session.put("loginInfo.password", user.getPassword());
		session.put("admin", user.isAdmin());
		insertIntoDatabase();
		return SUCCESS;
	}

	private boolean checkIfUserExists() {
		logger.debug("Executing GetUserCommand");
		Optional<User> result = Optional.empty();
		UserManagerServiceHelper helper = new UserManagerServiceHelper();
		try {
			result = helper.getUser(user.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.isPresent();
	}

	private void insertIntoDatabase() {
		logger.debug("Executing AddUserCommand");
		UserManagerServiceHelper helper = new UserManagerServiceHelper();
		try {
			helper.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getModel() {
		return user;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}
