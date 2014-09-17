package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Log;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LogDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Log> queryAll() {
		Query query = em.createQuery("SELECT l FROM Log l");
		List<Log> result = query.getResultList();
		return result;
	}

	@Transactional(readOnly = true)
	public Log get(Integer id) {
		return em.find(Log.class, id);
	}

	@Transactional
	public Log save(Log log) {
		em.persist(log);
		return log;
	}

	@Transactional
	public void delete(Log log) {
		Log r = get(log.getId());
		if(r != null) {
			em.remove(r);
		}
	}

}
