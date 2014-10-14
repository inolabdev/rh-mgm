package mz.inolabdev.rh.dao;

import java.util.List;

import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.exception.DuplicateRoleException;
import mz.inolabdev.rh.exception.RoleNotFoundException;

public interface RoleDao {

	public void addRole(Role role) throws DuplicateRoleException;

	public Role getRole(Long id) throws RoleNotFoundException;

	public Role getRole(String roleName) throws RoleNotFoundException;

	public void updateRole(Role role) throws RoleNotFoundException;

	public void deleteRole(Long id) throws RoleNotFoundException;

	public List<Role> getRoles();
}
