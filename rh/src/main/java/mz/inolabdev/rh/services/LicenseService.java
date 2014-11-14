package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.License;

public interface LicenseService extends GenericService<License> {

	public License create(License license);

	public List<License> getAll();

	public License find(Long id);

	public long count();
	
	public License first();
    
    public License last();
}
