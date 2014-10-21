package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.dao.UserDao;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements
		UserService, UserDetailsService {

	@Autowired
	private UserDao userDAO;

	@Override
	public User find(String userName) {
		return userDAO.find(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return find(username);
	}
}
