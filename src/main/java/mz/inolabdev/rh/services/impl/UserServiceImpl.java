package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.dao.UserDao;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.EmployeeService;
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
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public User find(String userName) {
		return userDAO.find(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return find(username);
	}

	@Override
	public User create(User u) {
		
		Employee e = employeeService.find(u.getUserProfile().getId());
		e.setUserLogin(u);
		employeeService.update(e);
		
		return userDAO.create(u);
	}
}
