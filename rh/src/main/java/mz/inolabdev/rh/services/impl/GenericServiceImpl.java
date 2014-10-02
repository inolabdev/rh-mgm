package mz.inolabdev.rh.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import mz.inolabdev.rh.dao.GenericDao;
import mz.inolabdev.rh.entity.IdEntity;
import mz.inolabdev.rh.services.GenericService;

import org.springframework.beans.factory.annotation.Autowired;

@Transactional
abstract class GenericServiceImpl<T extends IdEntity> implements
		GenericService<T> {

	@Autowired
	private GenericDao<T> specificDao;
	
	@Override
	public T create(T t) {
		return specificDao.create(t);
	}

	@Override
	public List<T> getAll() {
		return specificDao.getAll();
	}

	@Override
	public T find(Long id) {
		return specificDao.find(id);
	}

	@Override
	public T update(T t) {
		return specificDao.update(t);
	}

	@Override
	public long count() {
		return specificDao.count();
	}

	@Override
	public void delete(Object id) {
		specificDao.delete(id);
	}
}