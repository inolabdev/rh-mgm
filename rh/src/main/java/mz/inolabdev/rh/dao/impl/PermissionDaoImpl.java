package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.PermissionDao;
import mz.inolabdev.rh.entity.Permission;

import org.springframework.stereotype.Repository;

@Repository("permissionDao")
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements
		PermissionDao {

}
