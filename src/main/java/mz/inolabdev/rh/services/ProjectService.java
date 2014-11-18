package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Project;

public interface ProjectService extends GenericService<Project> {

	public List<Project> projectsByEmployee(Employee e);
}
