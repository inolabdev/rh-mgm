package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.LogDao;
import mz.inolabdev.rh.entity.Log;

import org.springframework.stereotype.Repository;

@Repository("logDao")
public class LogDaoImpl extends GenericDaoImpl<Log> implements LogDao {

}
