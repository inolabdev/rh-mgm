package mz.inolabdev.rh.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.inolabdev.rh.dao.EmployeeDao;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.services.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements
		EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> allWhereUserIdIsNull() {

		return employeeDao.allWhereUserIdIsNull();
	}

}
