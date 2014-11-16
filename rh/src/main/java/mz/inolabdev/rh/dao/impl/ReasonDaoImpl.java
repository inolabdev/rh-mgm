package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.ReasonDao;
import mz.inolabdev.rh.entity.Reason;

import org.springframework.stereotype.Repository;

@Repository("reasonDao")
public class ReasonDaoImpl extends GenericDaoImpl<Reason> implements ReasonDao {

}
