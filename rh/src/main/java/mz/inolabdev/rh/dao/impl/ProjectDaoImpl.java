package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.ProjectDao;
import mz.inolabdev.rh.entity.Project;

import org.springframework.stereotype.Repository;

@Repository("projectDao")
public class ProjectDaoImpl extends GenericDaoImpl<Project> implements
		ProjectDao {

}
