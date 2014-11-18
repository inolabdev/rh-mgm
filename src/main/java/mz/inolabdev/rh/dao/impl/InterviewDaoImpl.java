package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.InterviewDao;
import mz.inolabdev.rh.entity.Interview;

import org.springframework.stereotype.Repository;

@Repository("interviewDaoImpl")
public class InterviewDaoImpl extends GenericDaoImpl<Interview> implements
		InterviewDao {

}
