
package com.miw.business.usermanager;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.miw.business.UserDataServiceHelper;
import com.miw.model.User;

public class UserManager implements UserManagerService {
	Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public List<User> getUsers() throws Exception {
		logger.debug("Asking for users");
		List<User> users = (new UserDataServiceHelper()).getUsers();
		return users;
	}

	@Override
	public void addUser(User user) throws Exception {
		logger.debug("Inserting users");
		(new UserDataServiceHelper()).addUser(user);
		
	}

	@Override
	public Optional<User> getUser(String login) throws Exception {
		logger.debug("Getting user with login " + login);
		return (new UserDataServiceHelper()).getUser(login);
	}
}
