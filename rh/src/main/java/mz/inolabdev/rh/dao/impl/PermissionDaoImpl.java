package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.PermissionDao;
import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.exception.DuplicatePermissionException;
import mz.inolabdev.rh.exception.PermissionNotFoundException;

@Repository
public class PermissionDaoImpl implements PermissionDao {

	static Logger logger = LoggerFactory.getLogger(PermissionDaoImpl.class);

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void addPermission(Permission permission)
			throws DuplicatePermissionException {
		logger.debug("PermissionDAOImpl.addPermission() - ["
				+ permission.getPermissionname() + "]");
		try {
			Permission permCheck = getPermission(permission.getPermissionname());
			String message = "The permission [" + permCheck.getPermissionname()
					+ "] already exists";
			throw new DuplicatePermissionException(message);
		} catch (PermissionNotFoundException e) {
			em.persist(permission);
		}
	}

	@Override
	public Permission getPermission(Long long1)
			throws PermissionNotFoundException {
		Permission permObject = (Permission) em.find(Permission.class, long1);
		if (permObject == null) {
			throw new PermissionNotFoundException("Permission id [" + long1
					+ "] not found");
		} else {
			return permObject;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Permission getPermission(String usersPermission)
			throws PermissionNotFoundException {
		logger.debug("PermissionDAOImpl.getPermission() - [" + usersPermission
				+ "]");
		javax.persistence.Query query = em
				.createQuery("from Permission where permissionname = :usersPermission ");
		query.setParameter("usersPermission", usersPermission);

		logger.debug(query.toString());
		if (query.getResultList().size() == 0) {
			throw new PermissionNotFoundException("Permission ["
					+ usersPermission + "] not found");
		} else {
			logger.debug("Permission List Size: "
					+ query.getResultList().size());
			List<Permission> list = (List<Permission>) query.getResultList();
			Permission permObject = (Permission) list.get(0);

			return permObject;
		}
	}

	@Override
	public void updatePermission(Permission permission)
			throws PermissionNotFoundException {
		Permission permissionToUpdate = getPermission(permission.getId());
		permissionToUpdate.setPermissionname(permission.getPermissionname());
		em.merge(permissionToUpdate);
	}

	@Override
	public void deletePermission(Long permission_id)
			throws PermissionNotFoundException {
		Permission permission = getPermission(permission_id);
		if (permission != null) {
			em.remove(permission);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Permission> getPermissions() {
		return em.createQuery("from Permission").getResultList();
	}
}
