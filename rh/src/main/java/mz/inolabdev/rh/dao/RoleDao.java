package mz.inolabdev.rh.dao;

import mz.inolabdev.rh.entity.Role;

public interface RoleDao extends GenericDao<Role>{
	
	public Role find(String rollName);
}
