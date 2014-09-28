package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Event;

public interface EventService {
	
	public Event create(Event event);
	
	public void delete(Long id);
	
	public Event update(Event event);
	
	public Event find(Long id);
	
	public List<Event> getAll();
	
	public Long count();

}
