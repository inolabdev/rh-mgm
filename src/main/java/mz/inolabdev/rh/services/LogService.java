package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Log;

public interface LogService {

	public Log create(Log log);

	public List<Log> getAll();

	public Log find(Long id);

	public Log update(Log log);

	public long count();

	public void delete(Object id);
	
	public Log first();
    
    public Log last();
}
