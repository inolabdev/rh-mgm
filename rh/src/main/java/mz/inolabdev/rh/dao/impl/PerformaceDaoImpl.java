package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.PerformaceDao;
import mz.inolabdev.rh.entity.Performace;

import org.springframework.stereotype.Repository;

@Repository("performaceDao")
public class PerformaceDaoImpl extends GenericDaoImpl<Performace> implements
		PerformaceDao {

}
