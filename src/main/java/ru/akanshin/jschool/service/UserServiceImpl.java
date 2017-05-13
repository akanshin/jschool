package ru.akanshin.jschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.akanshin.jschool.data.UsersDao;
import ru.akanshin.jschool.data.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	public UserServiceImpl() {
		UsersDao.getInstance();
	}
	
	@Override
	public List<User> getAllUsers() {
		return UsersDao.getInstance().getAllUsers();
	}

	@Override
	public User getUserById(long id) {
		return UsersDao.getInstance().getUserById(id);
	}

	@Override
	public User getUserByLogin(String login) {
		return UsersDao.getInstance().getUserByLogin(login);
	}

	@Override
	public void createUser(User user) {
		UsersDao.getInstance().createUser(user);
	}

	@Override
	public void updateUser(User user) {
		UsersDao.getInstance().updateUser(user);
	}

	@Override
	public void deleteUserById(long id) {
		UsersDao.getInstance().deleteUserById(id);
	}

	@Override
	public void deleteUserByLogin(String login) {
		UsersDao.getInstance().deleteUserByLogin(login);
	}

	@Override
	public void deleteAllUsers() {
		UsersDao.getInstance().deleteAllUsers();
	}

	@Override
	public boolean isUserExist(User user) {
		return UsersDao.getInstance().isUserExist(user);
	}
	
}
