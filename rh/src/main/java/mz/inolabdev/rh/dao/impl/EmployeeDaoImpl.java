package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.EmployeeDao;
import mz.inolabdev.rh.entity.Employee;
@Repository("employeeDao")
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements
		EmployeeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> allWhereUserIdIsNull() {
		
		Query query = em
				.createQuery("select e from Employee e where e.userLogin is null");
		
		return query.getResultList();
	}

}
