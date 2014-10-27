package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.ContactPoint;

public interface ContactPointService {
	
	public ContactPoint create(ContactPoint contactPoint);

	public List<ContactPoint> getAll();

	public ContactPoint find(Long id);
	
	public ContactPoint findByValue(String value);

	public ContactPoint update(ContactPoint contactPoint);

	public long count();

	public void delete(Object id);
}
