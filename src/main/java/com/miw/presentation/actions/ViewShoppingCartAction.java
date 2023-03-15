package com.miw.presentation.actions;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.miw.model.ShoppingCart;
import com.opensymphony.xwork2.ActionSupport;

//@Results({ @Result(name = "success", location = "view-shopping-cart.jsp")})

public class ViewShoppingCartAction extends ActionSupport implements SessionAware{

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private ShoppingCart shoppingcart = null;
	private Map<String, Object> session;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String execute() throws Exception {
		if (session.get("shoppingcart") == null) {
			shoppingcart = new ShoppingCart();
			session.put("shoppingcart", shoppingcart);		
		} 
		ShoppingCart shopcart = (ShoppingCart) session.get("shoppingcart");
		shopcart.setCost(Math.round(shopcart.getCost() * 100.0) / 100.0);
		return SUCCESS;
	}


}
