package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Employee;

public interface EmployeeService {
	
	public Employee create(Employee employee);

	public List<Employee> getAll();

	public Employee find(Long id);

	public Employee update(Employee employee);

	public long count();

	public void delete(Object id);
	
	public List<Employee> allWhereUserIdIsNull();
}
