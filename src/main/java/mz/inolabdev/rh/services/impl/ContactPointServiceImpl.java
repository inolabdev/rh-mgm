package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.dao.ContactPointDao;
import mz.inolabdev.rh.entity.ContactPoint;
import mz.inolabdev.rh.services.ContactPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contactPointService")
public class ContactPointServiceImpl extends GenericServiceImpl<ContactPoint>
		implements ContactPointService {

	@Autowired
	private ContactPointDao cpDao; 
	
	@Override
	public ContactPoint findByValue(String value) {
		return cpDao.findByValue(value);
	}
	
}
