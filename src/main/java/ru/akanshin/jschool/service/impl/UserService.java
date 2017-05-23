package ru.akanshin.jschool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.akanshin.jschool.data.IUserDao;
import ru.akanshin.jschool.data.model.User;
import ru.akanshin.jschool.service.IUserService;

/**
 * @author akanshin
 * @version 1.0
 */
@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	
	/** Получение списка всех пользователей **/
	
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	
	/** Получение пользователя по id **/

	public User getUserById(long id) {
		return userDao.getUserById(id);
	}

	
	/** Создание пользователя **/
	
	public void createUser(User user) {
		if (user == null) {
			return;
		}
		userDao.createUser(user);
	}

	
	/** Обновление пользователя **/
	
	public void updateUser(User user) {
		if (user == null) {
			return;
		}
		
		userDao.updateUser(user);
	}

	
	/** Удаление пользователя по id **/
	
	public void deleteUserById(long id) {
		userDao.deleteUserById(id);
	}
	
	
	/** Проверка наличия пользователя **/
	
	public boolean isUserExist(User user) {
		if (user == null) {
			return false;
		}
		return userDao.isUserExist(user);
	}

}
