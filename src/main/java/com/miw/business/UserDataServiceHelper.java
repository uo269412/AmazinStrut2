package com.miw.business;

import java.util.List;
import java.util.Optional;

import com.miw.infrastructure.Factories;
import com.miw.model.User;

public class UserDataServiceHelper {

	public List<User> getUsers() throws Exception {
		return (Factories.dataServices.getUserDataService()).getUsers();
	}
	
	public void addUser(User user) throws Exception {
		(Factories.dataServices.getUserDataService()).addUser(user);
	}
	
	public Optional<User> getUser(String login) throws Exception {
		return (Factories.dataServices.getUserDataService()).getUser(login);
	}
}
