package ru.akanshin.jschool.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.akanshin.jschool.data.model.User;

@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {

	private EntityManager entityManager;

	public UsersDaoImpl() {
		super();
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ru.akanshin.jschool.data");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public List<User> getAllUsers() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}

	public User getUserById(long id) {
		Query query = entityManager.createQuery("from User where id = :ID", User.class);
		query.setParameter("ID", id);

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.isEmpty())
			return null;

		return users.get(0);
	}

	public User getUserByLogin(String login) {
		if (login == null)
			return null;
		Query query = entityManager.createQuery("from User where upper(login) = upper(:LOGIN)", User.class);
		query.setParameter("LOGIN", login);

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.isEmpty())
			return null;

		return users.get(0);
	}

	public void createUser(User user) {
		if (user == null)
			return;

		entityManager.persist(user);
	}

	public void updateUser(User user) {
		if (user == null)
			return;

		entityManager.merge(user);
	}
	
	public void deleteUserById(long id) {
		Query query = entityManager.createQuery("delete User where id = :ID");
		query.setParameter("ID", id);
		
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}

	public void deleteUserByLogin(String login) {
		if (login == null)
			return;
		Query query = entityManager.createQuery("delete User where upper(login) = upper(:LOGIN)");
		query.setParameter("LOGIN", login);
		
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}

	public void deleteAllUsers() {
		Query query = entityManager.createQuery("delete User");
		
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}

	public boolean isUserExist(User user) {
		return getUserByLogin(user.getLogin()) != null;
	}
}
