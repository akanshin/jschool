package ru.akanshin.jschool.data;

import java.util.List;

import org.hibernate.Session;
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
		Session session = this.sessionFactory.openSession();
		List<User> users = session.createQuery("from User", User.class).list();
		session.close();
		
		for (User user : users) {
			System.out.println("id=" + user.getId());
		}
		
		return users;
	}

	public User getUserById(long id) {
		Session session = this.sessionFactory.openSession();
		
		Query<User> query = session.createQuery("from User where id = :ID", User.class);
		query.setParameter("ID", id);

		List<User> users = query.getResultList();
		if (users.isEmpty())
			return null;

		session.close();
		
		return users.get(0);
	}

	public User getUserByLogin(String login) {
		if (login == null)
			return null;
		
		Session session = this.sessionFactory.openSession();
		
		Query<User> query = session.createQuery("from User where upper(login) = upper(:LOGIN)", User.class);
		query.setParameter("LOGIN", login);

		List<User> users = query.getResultList();
		if (users.isEmpty())
			return null;
		
		session.close();

		return users.get(0);
	}

	public void createUser(User user) {
		if (user == null)
			return;

		System.out.println("creating user in database: " + user.getFirstName());
		
		Session session = this.sessionFactory.openSession();
		session.persist(user);
		session.close();
	}

	public void updateUser(User user) {
		if (user == null)
			return;

		Session session = this.sessionFactory.openSession();
		session.merge(user);
		session.close();
	}
	
	public void deleteUserById(long id) {
		Session session = this.sessionFactory.openSession();
		
		Query<User> query = session.createQuery("delete User where id = :ID", User.class);
		query.setParameter("ID", id);
		
		query.executeUpdate();
		
		session.close();
	}

	public void deleteUserByLogin(String login) {
		if (login == null)
			return;
		
		Session session = this.sessionFactory.openSession();
		
		Query<User> query = session.createQuery("delete User where upper(login) = upper(:LOGIN)", User.class);
		query.setParameter("LOGIN", login);
		
		query.executeUpdate();
		
		session.close();
	}

	public void deleteAllUsers() {
		Session session = this.sessionFactory.openSession();
		
		Query<User> query = session.createQuery("delete User", User.class);
		
		query.executeUpdate();
		
		session.close();
	}

	public boolean isUserExist(User user) {
		return getUserByLogin(user.getLogin()) != null;
	}
}
