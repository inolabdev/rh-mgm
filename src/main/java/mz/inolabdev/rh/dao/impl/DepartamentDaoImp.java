package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.DepartamentDao;
import mz.inolabdev.rh.entity.Department;
@Repository("departamentDao")
public class DepartamentDaoImp extends GenericDaoImpl<Department> implements
		DepartamentDao {

}
