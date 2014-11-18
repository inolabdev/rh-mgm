package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.JobPositionDao;
import mz.inolabdev.rh.entity.JobPosition;

@Repository("jobPositionDao")
public class JobPositionDaoImpl extends GenericDaoImpl<JobPosition> implements
		JobPositionDao {

}
