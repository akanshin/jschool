package ru.akanshin.jschool.data;

import java.util.List;

import ru.akanshin.jschool.data.model.User;

public interface UsersDao {
	
	List<User> getAllUsers();
	
	User getUserById(long id);
	
	User getUserByLogin(String login);
	
	void createUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);
	
	void deleteUserByLogin(String login);
	
	void deleteAllUsers();
	
	boolean isUserExist(User user);
}
