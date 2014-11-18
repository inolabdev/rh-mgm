package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.CandidateDao;
import mz.inolabdev.rh.entity.Candidate;

import org.springframework.stereotype.Repository;

@Repository("candidateDao")
public class CandidateDaoImpl extends GenericDaoImpl<Candidate> implements
		CandidateDao {

}
