package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.License;

public interface LicenceService {

	public License create(License license);

	public List<License> getAll();

	public License find(Long id);

	public License update(License license);

	public long count();

	public void delete(Object id);
	
	public License first();
    
    public License last();
    
    public List<License> findByStatus(String status);
}
