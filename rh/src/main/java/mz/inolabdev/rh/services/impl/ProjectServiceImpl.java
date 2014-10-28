package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Project;
import mz.inolabdev.rh.services.ProjectService;

import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectServiceImpl extends GenericServiceImpl<Project> implements
		ProjectService {

}
