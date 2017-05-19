package ru.akanshin.jschool.data;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.akanshin.jschool.data.model.User;

@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> getAllUsers() {
		System.out.println("lol");
		List<User> users = sessionFactory.getCurrentSession()
				.createQuery("from User", User.class).list();
		
		for (User user : users) {
			System.out.println("id=" + user.getId());
		}
		
		return users;
	}

	public User getUserById(long id) {
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("from User where id = :ID", User.class);
		query.setParameter("ID", id);

		List<User> users = query.getResultList();
		if (users.isEmpty())
			return null;
		
		return users.get(0);
	}

	public User getUserByLogin(String login) {
		if (login == null)
			return null;
		
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("from User where upper(login) = upper(:LOGIN)", User.class);
		query.setParameter("LOGIN", login);

		List<User> users = query.getResultList();
		if (users.isEmpty())
			return null;

		return users.get(0);
	}

	public void createUser(User user) {
		if (user == null)
			return;

		System.out.println("creating user in database: " + user.getFirstName());
		
		sessionFactory.getCurrentSession().persist(user);
		
	}

	public void updateUser(User user) {
		if (user == null)
			return;

		sessionFactory.getCurrentSession().merge(user);
	}
	
	public void deleteUserById(long id) {
		Query<User> query = sessionFactory.getCurrentSession()
				.createQuery("delete User where id = :ID", User.class);
		query.setParameter("ID", id);
		
		query.executeUpdate();
	}

	public void deleteUserByLogin(String login) {
		if (login == null)
			return;
		
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
