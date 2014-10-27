package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import mz.inolabdev.rh.dao.CellPhoneDao;
import mz.inolabdev.rh.entity.Cellphone;

import org.springframework.stereotype.Repository;

@Repository("cellPhoneDao")
public class CellPhoneDaoImpl extends GenericDaoImpl<Cellphone> implements
		CellPhoneDao {

	@Override
	public Cellphone findByValue(String value) {
		Cellphone cellphone = null;
		String queryString = "Select cp from Cellphone cp where cp.value = :value ";

		TypedQuery<Cellphone> query = em.createQuery(queryString,
				Cellphone.class);
		query.setParameter("value", value);
		//query.setParameter("type", "cellPhone");
		List<Cellphone> results = query.getResultList();

		if (!results.isEmpty()) {
			cellphone = results.get(0);
		}

		return cellphone;
	}

}
