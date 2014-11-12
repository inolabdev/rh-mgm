package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.inolabdev.rh.dao.ProjectDao;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Project;

import org.springframework.stereotype.Repository;

@Repository("projectDao")
public class ProjectDaoImpl extends GenericDaoImpl<Project> implements
		ProjectDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> projectsByEmployee(Employee e) {
		
		Query query = em
				.createQuery("select p from Project p where ? in elements(p.customers)");
		query.setParameter(1, e);

		return query.getResultList();
	}

}
