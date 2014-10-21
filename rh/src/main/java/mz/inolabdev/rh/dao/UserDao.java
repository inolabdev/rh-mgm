package mz.inolabdev.rh.dao;

import mz.inolabdev.rh.entity.User;

public interface UserDao extends GenericDao<User> {

	public User find(String userName);
}
