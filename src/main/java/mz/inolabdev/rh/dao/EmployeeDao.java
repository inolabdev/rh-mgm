package mz.inolabdev.rh.dao;

import java.util.List;

import mz.inolabdev.rh.entity.Employee;

public interface EmployeeDao extends GenericDao<Employee> {

	public List<Employee> allWhereUserIdIsNull();
}
