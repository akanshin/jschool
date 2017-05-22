package ru.akanshin.jschool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.akanshin.jschool.data.IUsersDao;
import ru.akanshin.jschool.data.model.User;
import ru.akanshin.jschool.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUsersDao usersDao;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public User getUserById(long id) {
		return usersDao.getUserById(id);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public User getUserByLogin(String login) {
		if (login == null) {
			return null;
		}
		return usersDao.getUserByLogin(login);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createUser(User user) {
		if (user == null) {
			return;
		}
		usersDao.createUser(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateUser(User user) {
		if (user == null) {
			return;
		}
		usersDao.updateUser(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteUserById(long id) {
		usersDao.deleteUserById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteUserByLogin(String login) {
		if (login == null) {
			return;
		}

		usersDao.deleteUserByLogin(login);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAllUsers() {
		usersDao.deleteAllUsers();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean isUserExist(User user) {
		if (user == null) {
			return false;
		}
		return usersDao.isUserExist(user);
	}
	
}
