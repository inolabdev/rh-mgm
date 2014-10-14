package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.RoleDao;
import mz.inolabdev.rh.entity.Role;
@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

}
