package mz.inolabdev.persistence.service;

import java.util.List;

import mz.inolabdev.persistence.model.Event;

public interface EventService {
	
	public Event create(Event event);
	
	public void delete(Long id);
	
	public Event update(Event event);
	
	public Event find(Long id);
	
	public List<Event> getAll();
	
	public Long count();

}
