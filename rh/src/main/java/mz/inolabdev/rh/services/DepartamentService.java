package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Department;

public interface DepartamentService {
	public Department create(Department department);

	public List<Department> getAll();

	public Department find(Long id);

	public Department update(Department department);

	public long count();

	public void delete(Object id);
	
	public Department first();
    
    public Department last();
}
