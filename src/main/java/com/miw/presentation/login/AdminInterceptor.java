package com.miw.presentation.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminInterceptor extends AbstractInterceptor implements StrutsStatics {

	private static final long serialVersionUID = -4077390198609734426L;
	Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void init() {
		logger.debug("Interceptor admin initialized");
		super.init();
	}

	@Override
	public void destroy() {
		logger.debug("Interceptor admin destroyed");
		super.destroy();
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.debug("Interceptor login fired");

		ActionContext ctx = invocation.getInvocationContext();

		boolean admin = (boolean) ctx.getSession().get("admin");

		// If the user is not logger we redirect to login
		if (!admin) {
			logger.debug("Redirecting to Login");
			return "login-error";
		}

		// Otherwise, we let them pass through
		logger.debug("Admin interceptor value: " + admin + " redirected to " + invocation.invoke());
		return invocation.invoke();
	}

}
