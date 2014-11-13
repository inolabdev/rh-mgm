package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.PerformanceDao;
import mz.inolabdev.rh.entity.Performance;

import org.springframework.stereotype.Repository;

@Repository("performanceDao")
public class PerformanceDaoImpl extends GenericDaoImpl<Performance> implements
		PerformanceDao {

}
