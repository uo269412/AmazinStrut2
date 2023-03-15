package com.miw.presentation.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="miw.Amazin")
@InterceptorRefs({
    @InterceptorRef("amazinAdminStack")
})
public class ManageBooksAction extends ActionSupport {

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;

	@Override
	public String execute() {
		return SUCCESS;
	}
}