package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.User;

public interface UserService {
	public User create(User user);

	public List<User> getAll();

	public User find(Long id);

	public User update(User user);

	public long count();

	public void delete(Object id);
}
