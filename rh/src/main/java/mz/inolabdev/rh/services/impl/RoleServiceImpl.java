package mz.inolabdev.rh.services.impl;

import java.util.List;

import mz.inolabdev.rh.dao.RoleDao;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.exception.DuplicateRoleException;
import mz.inolabdev.rh.exception.RoleNotFoundException;
import mz.inolabdev.rh.services.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDAO;

	@Override
	public void addRole(Role role) throws DuplicateRoleException {
		roleDAO.addRole(role);
	}

	@Override
	public Role getRole(Long id) throws RoleNotFoundException {
		return roleDAO.getRole(id);
	}

	@Override
	public Role getRole(String rolename) throws RoleNotFoundException {
		return roleDAO.getRole(rolename);
	}

	@Override
	public void updateRole(Role role) throws RoleNotFoundException {
		roleDAO.updateRole(role);
	}

	@Override
	public void deleteRole(Long id) throws RoleNotFoundException {
		roleDAO.deleteRole(id);
	}

	@Override
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}
}