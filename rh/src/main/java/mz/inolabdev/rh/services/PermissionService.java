package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Permission;

public interface PermissionService {

	public Permission create(Permission permission);

	public List<Permission> getAll();

	public Permission find(Long id);

	public Permission update(Permission permission);

	public long count();

	public void delete(Object id);
	
	public Permission first();
    
    public Permission last();

}