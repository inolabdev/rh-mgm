package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.LicenseType;

public interface LicenseTypeService extends GenericService<LicenseType> {

	public LicenseType create(LicenseType licenseType);

	public List<LicenseType> getAll();

	public LicenseType find(Long id);

	public long count();
	
	public LicenseType first();
    
    public LicenseType last();
}
