package mz.inolabdev.rh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mz.inolabdev.rh.dao.GenericDao;

public abstract class GenericDaoImpl<T extends Serializable> implements
		GenericDao<T> {

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@PersistenceContext
	protected EntityManager em;

	@Override
	public long count() {
		String entity = type.getSimpleName();
		final StringBuffer queryString = new StringBuffer(
				"select count(ent) from " + entity + " ent");
		final Query query = this.em.createQuery(queryString.toString());

		return (Long) query.getSingleResult();
	}

	@Override
	public T create(final T t) {
		em.persist(t);
		return t;
	}

	@Override
	public void delete(final Object id) {
		em.remove(em.getReference(type, id));
	}

	@Override
	public T find(final Object id) {
		return em.find(type, id);
	}

	@Override
	public T update(final T t) {
		return em.merge(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		Query query = em.createQuery("from " + type.getName());
		return query.getResultList();
	}

}
