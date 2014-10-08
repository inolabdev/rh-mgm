package mz.inolabdev.rh.services.impl;

import org.springframework.stereotype.Service;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.services.EmployeeService;
@Service("employeeService")
public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements
		EmployeeService {

}
