package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.ContactPointDao;
import mz.inolabdev.rh.entity.ContactPoint;

import org.springframework.stereotype.Repository;

@Repository("contactPointDao")
public class ContactPointDaoImpl extends GenericDaoImpl<ContactPoint> implements
		ContactPointDao {

}
