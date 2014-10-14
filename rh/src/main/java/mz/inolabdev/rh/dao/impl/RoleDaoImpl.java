package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mz.inolabdev.rh.dao.RoleDao;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.exception.DuplicateRoleException;
import mz.inolabdev.rh.exception.RoleNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
	static Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void addRole(Role role) throws DuplicateRoleException {
		logger.debug("RoleDAOImpl.addRole() - [" + role.getRolename() + "]");
		try {
			Role roleCheck = getRole(role.getRolename());
			String message = "The role [" + roleCheck.getRolename()
					+ "] already exists";
			throw new DuplicateRoleException(message);
		} catch (RoleNotFoundException e) {
			em.persist(role);
		}
	}

	@Override
	public Role getRole(Long role_id) throws RoleNotFoundException {
		Role roleObject = (Role) em.find(Role.class, role_id);
		if (roleObject == null) {
			throw new RoleNotFoundException("Role id [" + role_id
					+ "] not found");
		} else {
			return roleObject;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Role getRole(String usersRole) throws RoleNotFoundException {
		logger.debug("RoleDAOImpl.getRole() - [" + usersRole + "]");
		javax.persistence.Query query = em
				.createQuery("from Role where rolename = :usersRole ");
		query.setParameter("usersRole", usersRole);

		logger.debug(query.toString());
		if (query.getResultList().size() == 0) {
			throw new RoleNotFoundException("Role [" + usersRole
					+ "] not found");
		} else {
			logger.debug("Role List Size: " + query.getResultList().size());
			List<Role> list = (List<Role>) query.getResultList();
			Role roleObject = (Role) list.get(0);

			return roleObject;
		}
	}

	@Override
	public void updateRole(Role role) throws RoleNotFoundException {
		Role roleToUpdate = getRole(role.getId());
		roleToUpdate.setRolename(role.getRolename());
		em.merge(roleToUpdate);
	}

	@Override
	public void deleteRole(Long role_id) throws RoleNotFoundException {
		Role role = getRole(role_id);
		if (role != null) {
			em.remove(role);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		return em.createQuery("from Role").getResultList();
	}
}
