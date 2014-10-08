package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.ContactPointDao;
import mz.inolabdev.rh.entity.ContactPoint;
@Repository("contactPointDao")
public class ContactPointDaoImpl extends GenericDaoImpl<ContactPoint> implements
		ContactPointDao {

}
