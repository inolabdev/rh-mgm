package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.inolabdev.rh.dao.LicenceDao;
import mz.inolabdev.rh.entity.License;

import org.springframework.stereotype.Repository;

@Repository("licenceDao")
public class LicenceDaoImpl extends GenericDaoImpl<License> implements
		LicenceDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<License> findByStatus(String status) {

		Query query = em
				.createQuery("select l from License l where l.status like '"
						+ status + "'");

		return query.getResultList();
	}

}
