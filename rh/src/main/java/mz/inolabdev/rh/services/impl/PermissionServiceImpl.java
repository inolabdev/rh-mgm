package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.services.PermissionService;

import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl extends GenericServiceImpl<Permission>
		implements PermissionService {

}
