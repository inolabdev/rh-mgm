package mz.inolabdev.rh.services.impl;

import java.util.List;

import mz.inolabdev.rh.dao.ProjectDao;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Project;
import mz.inolabdev.rh.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectServiceImpl extends GenericServiceImpl<Project> implements
		ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Override
	public List<Project> projectsByEmployee(Employee e) {

		return projectDao.projectsByEmployee(e);
	}

}
