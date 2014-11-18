package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import mz.inolabdev.rh.dao.ContactPointDao;
import mz.inolabdev.rh.entity.ContactPoint;

import org.springframework.stereotype.Repository;

@Repository("contactPointDao")
public class ContactPointDaoImpl extends GenericDaoImpl<ContactPoint> implements
		ContactPointDao {

	@Override
	public ContactPoint findByValue(String value) {
		ContactPoint cp = null;
		String queryString = "Select cp from ContactPoint cp where cp.value = :value ";

		TypedQuery<ContactPoint> query = em.createQuery(queryString,
				ContactPoint.class);
		query.setParameter("value", value);
		List<ContactPoint> results = query.getResultList();

		if (!results.isEmpty()) {
			cp = results.get(0);
		}

		return cp;
	}
}