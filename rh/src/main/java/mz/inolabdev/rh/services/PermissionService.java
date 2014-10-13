package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.exception.DuplicatePermissionException;
import mz.inolabdev.rh.exception.PermissionNotFoundException;

public interface PermissionService {

    public void addPermission(Permission permission) throws DuplicatePermissionException;

    public Permission getPermission(Long id) throws PermissionNotFoundException;
    
    public Permission getPermission(String permissionname) throws PermissionNotFoundException;

    public void updatePermission(Permission permission) throws PermissionNotFoundException;

    public void deletePermission(Long id) throws PermissionNotFoundException;

    public List<Permission> getPermissions();

}