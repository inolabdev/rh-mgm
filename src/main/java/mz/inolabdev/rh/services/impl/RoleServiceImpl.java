package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.dao.RoleDao;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role> implements
		RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role find(String rollName) {

		Role role = roleDao.find(rollName);

		return role;
	}
}