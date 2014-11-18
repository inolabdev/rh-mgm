package mz.inolabdev.rh.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.EmailDao;
import mz.inolabdev.rh.entity.Email;

@Repository("emailDao")
public class EmailDaoImpl extends GenericDaoImpl<Email> implements EmailDao {

	@Override
	public Email findByValue(String value) {
		Email email = null;
		String queryString = "Select email from Email email where email.value = :value ";

		TypedQuery<Email> query = em.createQuery(queryString, Email.class);
		query.setParameter("value", value);
		//query.setParameter("type", "email");
		List<Email> results = query.getResultList();

		if (!results.isEmpty()) {
			email = results.get(0);
		}

		return email;
	}

}
