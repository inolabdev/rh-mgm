package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mz.inolabdev.rh.dao.UserDao;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.exception.DuplicateUserException;
import mz.inolabdev.rh.exception.UserNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void addUser(User user) throws DuplicateUserException {
		logger.debug("UserDaoImpl.addUser() - [" + user.getUsername() + "]");
		try {
			User userCheck = getUser(user.getUsername());
			String message = "The user [" + userCheck.getUsername()
					+ "] already exists";
			throw new DuplicateUserException(message);
		} catch (UserNotFoundException e) {
			em.persist(user);
		}
	}

	@Override
	public User getUser(Long userId) throws UserNotFoundException {
		logger.debug("UserDaoImpl.getUser() - [" + userId + "]");
		User userObject = (User) em.find(User.class, userId);

		if (userObject == null) {
			throw new UserNotFoundException("User id [" + userId
					+ "] not found");
		} else {
			return userObject;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String usersName) throws UserNotFoundException {
		logger.debug("UserDaoImpl.getUser() - [" + usersName + "]");
		javax.persistence.Query query = em
				.createQuery("from User where username = :usersName ");
		query.setParameter("usersName", usersName);

		logger.debug(query.toString());
		if (query.getResultList().size() == 0) {
			throw new UserNotFoundException("User [" + usersName
					+ "] not found");
		} else {
			logger.debug("User List Size: " + query.getResultList().size());
			List<User> list = (List<User>) query.getResultList();
			User userObject = (User) list.get(0);

			return userObject;
		}
	}

	@Override
	public void updateUser(User user) throws UserNotFoundException {
		User userToUpdate = getUser(user.getId());
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setEnabled(user.getEnabled());
		userToUpdate.setRoles(user.getRoles());
		em.merge(userToUpdate);
	}

	@Override
	public void deleteUser(Long userId) throws UserNotFoundException {
		User user = getUser(userId);
		if (user != null) {
			em.remove(user);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return em.createQuery("from User").getResultList();
	}

}