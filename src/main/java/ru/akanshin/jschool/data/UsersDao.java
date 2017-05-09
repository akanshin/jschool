package ru.akanshin.jschool.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import ru.akanshin.jschool.data.model.User;

@Service("userService")
public class UsersDao implements UserService {
	private static UsersDao instance;

	public static UsersDao getInstance() {
		if (instance == null)
			instance = new UsersDao();
		return instance;
	}

	private EntityManager entityManager;

	public UsersDao() {
		super();
		EntityManagerFactory entityManagerFactory 
			= Persistence.createEntityManagerFactory("ru.akanshin.jschool.data");
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
		
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}
	
	public void updateUser(User user) {
		if (user == null)
			return;
		
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}
	
	public void deleteUserById(long id) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("delete User where id = :ID");
		query.setParameter("ID", id);
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}
	
	public void deleteUserByLogin(String login) {
		if (login == null)
			return;
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("delete User where upper(login) = upper(:LOGIN)");
		query.setParameter("LOGIN", login);
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}
	
	public void deleteAllUsers() {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("delete User");
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}
	
	public boolean isUserExist(User user) {
		return getUserByLogin(user.getLogin()) != null;
	}
}
