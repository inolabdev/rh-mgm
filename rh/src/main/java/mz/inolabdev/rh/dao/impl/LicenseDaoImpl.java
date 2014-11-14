package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.LicenseDao;
import mz.inolabdev.rh.entity.License;

import org.springframework.stereotype.Repository;

@Repository("licenseDao")
public class LicenseDaoImpl extends GenericDaoImpl<License> implements
		LicenseDao {

}
