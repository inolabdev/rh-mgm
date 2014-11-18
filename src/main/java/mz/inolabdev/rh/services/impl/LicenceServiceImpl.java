package mz.inolabdev.rh.services.impl;

import java.util.List;

import mz.inolabdev.rh.dao.LicenceDao;
import mz.inolabdev.rh.entity.License;
import mz.inolabdev.rh.services.LicenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("licenceService")
public class LicenceServiceImpl extends GenericServiceImpl<License>
		implements LicenceService {
	
	@Autowired
	private LicenceDao licenceDao;

	@Override
	public List<License> findByStatus(String status) {
		// TODO Auto-generated method stub
		return licenceDao.findByStatus(status);
	}

	//We can also override functions to specify some logics.
}
