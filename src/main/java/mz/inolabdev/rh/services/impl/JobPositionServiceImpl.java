package mz.inolabdev.rh.services.impl;

import org.springframework.stereotype.Service;

import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.services.JobPositionService;

@Service("jobPositionService")
public class JobPositionServiceImpl extends GenericServiceImpl<JobPosition>
		implements JobPositionService {

	//We can also override functions to specify some logics.
}
