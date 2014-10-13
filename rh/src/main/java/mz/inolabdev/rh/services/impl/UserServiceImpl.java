package mz.inolabdev.rh.services.impl;

import java.util.List;

import mz.inolabdev.rh.dao.UserDao;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.exception.DuplicateUserException;
import mz.inolabdev.rh.exception.UserNotFoundException;
import mz.inolabdev.rh.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDAO;

	@Override
	public void addUser(User user) throws DuplicateUserException {
		userDAO.addUser(user);
	}

	@Override
	public User getUser(Long userId) throws UserNotFoundException {
		return userDAO.getUser(userId);
	}

	@Override
	public User getUser(String username) throws UserNotFoundException {
		return userDAO.getUser(username);
	}

	@Override
	public void updateUser(User user) throws UserNotFoundException {
		userDAO.updateUser(user);
	}

	@Override
	public void deleteUser(Long userId) throws UserNotFoundException {
		userDAO.deleteUser(userId);
	}

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			System.out.println("Chegou a este pt:" + username);
			return getUser(username);
		} catch (UserNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}
}
