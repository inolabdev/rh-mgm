package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.EvaluationDao;
import mz.inolabdev.rh.entity.Evaluation;

import org.springframework.stereotype.Repository;

@Repository("evaluationDao")
public class EvaluationDaoImpl extends GenericDaoImpl<Evaluation> implements
		EvaluationDao {

}
