package ru.akanshin.jschool.data;

import java.util.List;

import ru.akanshin.jschool.data.model.User;


/**
 * @author akanshin
 * @version 1.0
 */
public interface IUserDao {

	List<User> getAllUsers();

	User getUserById(long id);
	
	User getUserByLogin(String login);

	void createUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	boolean isUserExist(User user);
}
