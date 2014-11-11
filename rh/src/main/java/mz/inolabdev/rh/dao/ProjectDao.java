package mz.inolabdev.rh.dao;

import java.util.List;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Project;

public interface ProjectDao extends GenericDao<Project> {

//	public List<Activity> activities(Project project);
//
//	public List<Employee> customers(Project project);
//
//	public List<Employee> admins(Project project);
	
	public List<Project> projectsByEmployee(Employee e);
}
