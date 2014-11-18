package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Location;


public interface LocationService{
	public Location create(Location location);

	public List<Location> getAll();

	public Location find(Long id);

	public Location update(Location location);

	public long count();

	public void delete(Object id);
	
	public Location first();
    
    public Location last();

}
