package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.EducationLevel;

public interface EducationLavelService {

	public EducationLevel create(EducationLevel educationLevel);

	public List<EducationLevel> getAll();

	public EducationLevel find(Long id);

	public EducationLevel update(EducationLevel educationLevel);

	public long count();

	public void delete(Object id);
	
	public EducationLevel first();
    
    public EducationLevel last();
}
