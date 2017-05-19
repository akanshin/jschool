package ru.akanshin.jschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.akanshin.jschool.data.UsersDao;
import ru.akanshin.jschool.data.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsersDao usersDao;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public User getUserById(long id) {
		return usersDao.getUserById(id);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public User getUserByLogin(String login) {
		return usersDao.getUserByLogin(login);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createUser(User user) {
		usersDao.createUser(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateUser(User user) {
		usersDao.updateUser(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteUserById(long id) {
		usersDao.deleteUserById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteUserByLogin(String login) {
		usersDao.deleteUserByLogin(login);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAllUsers() {
		usersDao.deleteAllUsers();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean isUserExist(User user) {
		return usersDao.isUserExist(user);
	}
	
}
