package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Reason;

public interface ReasonService {

	public Reason create(Reason reason);

	public List<Reason> getAll();

	public Reason find(Long id);

	public Reason update(Reason reason);

	public long count();

	public void delete(Object id);
	
	public Reason first();
    
    public Reason last();
}
