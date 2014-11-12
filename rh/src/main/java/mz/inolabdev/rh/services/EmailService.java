package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Email;

public interface EmailService {
	
	public Email create(Email email);

	public List<Email> getAll();

	public Email find(Long id);

	public Email update(Email email);

	public long count();

	public void delete(Object id);

	public Email findByValue(String value);
}
