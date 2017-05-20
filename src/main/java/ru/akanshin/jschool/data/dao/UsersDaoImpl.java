package ru.akanshin.jschool.data.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.akanshin.jschool.data.IUsersDao;
import ru.akanshin.jschool.data.model.User;

@Repository("usersDao")
public class UsersDaoImpl implements IUsersDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> getAllUsers() {
		List<User> users = sessionFactory.getCurrentSession()
				.createQuery("from User", User.class).list();
		
		return users;
	}

	public User getUserById(long id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	public User getUserByLogin(String login) {
		if (login == null) {
			return null;
		}
		
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("from User where upper(login) = upper(:LOGIN)", User.class);
		query.setParameter("LOGIN", login);

		return query.uniqueResult();
	}

	public void createUser(User user) {
		if (user == null) {
			return;
		}
		
		sessionFactory.getCurrentSession().save(user);
	}

	public void updateUser(User user) {
		if (user == null) {
			return;
		}

		sessionFactory.getCurrentSession().merge(user);
	}
	
	public void deleteUserById(long id) {
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("delete User where id = :ID", User.class);
		query.setParameter("ID", id);
		
		query.executeUpdate();
	}

	public void deleteUserByLogin(String login) {
		if (login == null) {
			return;
		}
		
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("delete User where upper(login) = upper(:LOGIN)", User.class);
		query.setParameter("LOGIN", login);
		
		query.executeUpdate();
	}

	public void deleteAllUsers() {
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("delete User", User.class);
		
		query.executeUpdate();
	}

	public boolean isUserExist(User user) {
		return getUserByLogin(user.getLogin()) != null;
	}
}
