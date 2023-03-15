package com.miw.presentation.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.miw.infrastructure.Factories;
import com.miw.model.User;

public class UserManagerServiceHelper {

	Logger logger = LogManager.getLogger(this.getClass());

	public List<User> getUsers() throws Exception {
		logger.debug("Retrieving Users from Business Layer");
		return (Factories.services.getUserManagerService()).getUsers();
	}
	
	public void addUser(User user) throws Exception {
		logger.debug("Adding Users from Business Layer");
		(Factories.services.getUserManagerService()).addUser(user);
	}
	
	public Optional<User> getUser (String login) throws Exception {
		logger.debug("Retrieving User from Business Layer");
		return (Factories.services.getUserManagerService()).getUser(login);
	}
}
