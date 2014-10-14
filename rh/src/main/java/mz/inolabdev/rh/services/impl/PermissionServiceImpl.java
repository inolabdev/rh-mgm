package mz.inolabdev.rh.services.impl;

import java.util.List;

import mz.inolabdev.rh.dao.PermissionDao;
import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.exception.DuplicatePermissionException;
import mz.inolabdev.rh.exception.PermissionNotFoundException;
import mz.inolabdev.rh.services.PermissionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    static Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    
    @Autowired
    private PermissionDao permissionDAO;

    @Override
    public void addPermission(Permission permission) throws DuplicatePermissionException {
        permissionDAO.addPermission(permission);
    }

    @Override
    public Permission getPermission(Long id) throws PermissionNotFoundException {
        return permissionDAO.getPermission(id);
    }

    @Override
    public Permission getPermission(String permissionname) throws PermissionNotFoundException {
        return permissionDAO.getPermission(permissionname);
    }

    @Override
    public void updatePermission(Permission permission) throws PermissionNotFoundException {
        permissionDAO.updatePermission(permission);
    }

    @Override
    public void deletePermission(Long id) throws PermissionNotFoundException {
        permissionDAO.deletePermission(id);
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionDAO.getPermissions();
    }
}
