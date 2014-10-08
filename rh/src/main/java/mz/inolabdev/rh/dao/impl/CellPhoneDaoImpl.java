package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.CellPhoneDao;
import mz.inolabdev.rh.entity.Cellphone;

@Repository("cellPhoneDao")
public class CellPhoneDaoImpl extends GenericDaoImpl<Cellphone> implements
		CellPhoneDao {

}
