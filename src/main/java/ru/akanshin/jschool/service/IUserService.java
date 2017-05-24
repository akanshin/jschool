package ru.akanshin.jschool.service;

import java.util.List;

import ru.akanshin.jschool.data.model.User;

/**
 * @author akanshin
 * @version 1.0
 */

public interface IUserService {
	List<User> getAllUsers();
	
	User getUserById(long id);
	
	void createUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);
	
	boolean isUserExist(User user);
}
