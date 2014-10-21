package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Cellphone;

public interface CellPhoneService {
	public Cellphone create(Cellphone cellphone);

	public List<Cellphone> getAll();

	public Cellphone find(Long id);

	public Cellphone update(Cellphone cellphone);

	public long count();

	public void delete(Object id);
	
	public Cellphone first();
    
    public Cellphone last();
}
