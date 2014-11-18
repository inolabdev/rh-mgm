package mz.inolabdev.rh.dao.impl;

import javax.persistence.Query;

import mz.inolabdev.rh.dao.RoleDao;
import mz.inolabdev.rh.entity.Role;

import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	@Override
	public Role find(String rollName) {

		Query query = em.createQuery("select r from Role r where r.rolename = ?");
		query.setParameter(1, rollName);
		
		Role role = (Role) query.getResultList().get(0);
		
		return role;
	}

}