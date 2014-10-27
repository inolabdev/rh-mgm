package mz.inolabdev.rh.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.inolabdev.rh.dao.CellPhoneDao;
import mz.inolabdev.rh.dao.ContactPointDao;
import mz.inolabdev.rh.entity.Cellphone;
import mz.inolabdev.rh.entity.ContactPoint;
import mz.inolabdev.rh.services.CellPhoneService;

@Service("cellPhoneService")
public class CellPhoneServiceImpl extends GenericServiceImpl<Cellphone>
		implements CellPhoneService {

	@Autowired
	private CellPhoneDao cellPhoneDao; 
	
	@Override
	public Cellphone findByValue(String value) {
		return cellPhoneDao.findByValue(value);
	}

}
