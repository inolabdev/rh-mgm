package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.LicenseTypeDao;
import mz.inolabdev.rh.entity.LicenseType;

import org.springframework.stereotype.Repository;

@Repository("licenseTypeDao")
public class LicenseTypeDaoImpl extends GenericDaoImpl<LicenseType> implements
		LicenseTypeDao {

}
