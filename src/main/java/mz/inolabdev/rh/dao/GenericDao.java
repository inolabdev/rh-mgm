package mz.inolabdev.rh.dao;

import java.util.List;

import mz.inolabdev.rh.entity.IdEntity;

public interface GenericDao<T extends IdEntity> {
	
	public long count();

    public T create(T t);

    public void delete(Object id);

    public T find(Object id);
    
    public List<T> getAll();

    public T update(T t); 

    public T first();
    
    public T last();
}
