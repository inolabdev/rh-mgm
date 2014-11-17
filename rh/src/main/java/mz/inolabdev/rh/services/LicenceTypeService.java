package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.LicenseType;

public interface LicenceTypeService {

	public LicenseType create(LicenseType licenceType);

	public List<LicenseType> getAll();

	public LicenseType find(Long id);

	public LicenseType update(LicenseType licenceType);

	public long count();

	public void delete(Object id);
	
	public LicenseType first();
    
    public LicenseType last();
}
