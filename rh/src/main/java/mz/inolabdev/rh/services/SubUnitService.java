package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.SubUnit;

public interface SubUnitService {
	
	public SubUnit create(SubUnit subUnit);

	public List<SubUnit> getAll();

	public SubUnit find(Long id);

	public SubUnit update(SubUnit subUnit);

	public long count();

	public void delete(Object id);

}
