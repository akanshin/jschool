package ru.akanshin.jschool.data.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.akanshin.jschool.data.IUserDao;
import ru.akanshin.jschool.data.model.User;

/**
 * @author akanshin
 * @version 1.0
 */

@Repository("userDao")
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Logger logger = Logger.getLogger(this.getClass());

	
	/** Получение списка всех пользователей **/
	
	public List<User> getAllUsers() {
		List<User> users = sessionFactory.getCurrentSession()
				.createQuery("from User", User.class).list();

		return users;
	}

	
	/** Получение пользователя по id **/
	
	public User getUserById(long id) {
		logger.debug("getUserById(id = " + id + ")");
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	
	/** Получение пользователя по логину **/
	
	public User getUserByLogin(String login) {
		logger.debug("getUserByLogin(login = " + login + ")");
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("from User where upper(login) = upper(:LOGIN)", User.class);
		query.setParameter("LOGIN", login);

		return query.uniqueResult();
	}

	
	/** Создание пользователя **/
	
	public void createUser(User user) {
		logger.debug("createUser(user = " + user.toString() + ")");
		sessionFactory.getCurrentSession().save(user);
	}

	
	/** Обновление пользователя **/
	
	public void updateUser(User user) {
		logger.debug("updateUser(user = " + user.toString() + ")");
		sessionFactory.getCurrentSession().merge(user);
		sessionFactory.getCurrentSession().flush();
	}

	
	/** Удаление пользователя по id **/
	
	@SuppressWarnings("rawtypes")
	public void deleteUserById(long id) {
		logger.debug("deleteUserById(id = " + id + ")");
		Query query = sessionFactory.getCurrentSession()
				.createQuery("delete User where id = :ID");
		query.setParameter("ID", id);

		query.executeUpdate();
	}
	
	
	/** Проверка наличия пользователя **/

	public boolean isUserExist(User user) {
		logger.debug("isUserExist(user = " + user.toString() + ")");
		return getUserByLogin(user.getLogin()) != null;
	}
}
