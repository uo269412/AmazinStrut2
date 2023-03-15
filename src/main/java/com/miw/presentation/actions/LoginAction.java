package com.miw.presentation.actions;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.*;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.miw.model.LoginInfo;
import com.miw.model.User;
import com.miw.presentation.user.UserManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Results({ @Result(name = "success", location = "counter.action", type = "redirectAction"),
		@Result(name = "login-error", location = "/index.jsp"),
		@Result(name = "captcha-error", location = "/index.jsp"),

		// For validation
		@Result(name = "input", location = "/index.jsp")

})

@Validations(requiredStrings = {
		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "loginInfo.login", message = "You must enter a value for login."),
		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "loginInfo.password", message = "You must enter a value for password.") })

public class LoginAction extends ActionSupport implements RequestAware, SessionAware {

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private LoginInfo login = null;
	private Map<String, Object> session = null;
	private Map<String, Object> request;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public LoginInfo getLoginInfo() {
		return login;
	}

	public void setLoginInfo(LoginInfo login) {
		this.login = login;
	}

	@Override
	public void validate() {
		logger.debug("Invoking validate!");
		super.validate();
	}

	@Override
	public String execute() throws Exception {
		if (!login.getCaptcha().equals("23344343")) {
			request.put("mymessage", "Captcha is wrong");
			return "captcha-error";
		}
		Optional<User> optionalUser = getUser();
		if (optionalUser.isEmpty()) {
			request.put("mymessage", "El usuario no existe dentro del sistema");
			return "login-error";
		} else {
			User user = optionalUser.get();
			if (login.getLogin().equals(user.getLogin()) && login.getPassword().equals(user.getPassword())) {
				session.put("loginInfo", login);
				session.put("admin", user.isAdmin());
				return SUCCESS;
			} else {
				logger.debug("Credentials are wrong: " + login);
				request.put("mymessage", "Credenciales erróneas");
				return "login-error";
			}
		}
	}

	private Optional<User> getUser() {
		logger.debug("Executing GetUserCommand");
		Optional<User> result = Optional.empty();
		UserManagerServiceHelper helper = new UserManagerServiceHelper();
		try {
			result = helper.getUser(login.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

//	@Override
//	public String execute() throws Exception {
//		if (!login.getCaptcha().equals("23344343")) {
//			request.put("mymessage", "Captcha is wrong");
//			return "captcha-error";
//		}
//		// We do a very basic authentication :).
//		if (login.getLogin().equals("admin") && login.getPassword().equals("amazin")) {
//			logger.debug("Loggin in!: " + login);
//			session.put("loginInfo", login);
//			// session.put("admin", user.isAdmin());
//			return SUCCESS;
//		} else {
//			logger.debug("Credentials are wrong: " + login);
//			request.put("mymessage", "Wrong credentials");
//			return "login-error";
//		}
//	}

}
