package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Role;

public interface RoleService {

	public Role create(Role role);

	public List<Role> getAll();

	public Role find(Long id);
	
	public Role find(String rollName);

	public Role update(Role role);

	public long count();

	public void delete(Object id);
}
