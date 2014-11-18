package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.LicenceTypeDao;
import mz.inolabdev.rh.entity.LicenseType;

import org.springframework.stereotype.Repository;

@Repository("licenseTypeDao")
public class LicenceTypeDaoImpl extends GenericDaoImpl<LicenseType> implements LicenceTypeDao {

}
