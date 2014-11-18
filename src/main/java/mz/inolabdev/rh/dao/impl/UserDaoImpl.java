package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.inolabdev.rh.dao.UserDao;
import mz.inolabdev.rh.entity.User;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@Override
	public User find(String userName) {

		Query query = em
				.createQuery("select u from User u where u.username = ?");
		query.setParameter(1, userName);

		@SuppressWarnings("unchecked")
		List<User> local = query.getResultList();
		
		if(local.size() == 0)
			return null;
		
		User user = (User) local.get(0);
		System.out.println(user);

		return user;
	}

}