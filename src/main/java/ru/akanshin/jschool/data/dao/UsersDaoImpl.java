package ru.akanshin.jschool.data.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("from User where upper(login) = upper(:LOGIN)", User.class);
		query.setParameter("LOGIN", login);

		return query.uniqueResult();
	}

	public void createUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public void updateUser(User user) {
		Logger.getLogger("Hibernate").log(Level.INFO, "#### update: " + user.toString());
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		sessionFactory.getCurrentSession().merge(user);
		sessionFactory.getCurrentSession().flush();
		transaction.commit();
	}

	@SuppressWarnings("rawtypes")
	public void deleteUserById(long id) {
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		
		Query query = sessionFactory.getCurrentSession()
				.createQuery("delete User where id = :ID");
		query.setParameter("ID", id);

		query.executeUpdate();
		
		transaction.commit();
	}

	@SuppressWarnings("rawtypes")
	public void deleteUserByLogin(String login) {
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		
		
		Query query = sessionFactory.getCurrentSession()
				.createQuery("delete User where upper(login) = upper(:LOGIN)");
		query.setParameter("LOGIN", login);

		query.executeUpdate();
		
		transaction.commit();
	}

	@SuppressWarnings("rawtypes")
	public void deleteAllUsers() {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("delete User");

		query.executeUpdate();
	}

	public boolean isUserExist(User user) {
		return getUserByLogin(user.getLogin()) != null;
	}
}
