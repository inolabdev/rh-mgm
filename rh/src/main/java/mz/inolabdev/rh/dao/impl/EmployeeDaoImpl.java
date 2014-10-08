package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.EmployeeDao;
import mz.inolabdev.rh.entity.Employee;
@Repository("employeeDao")
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements
		EmployeeDao {

}
