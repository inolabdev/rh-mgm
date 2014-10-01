package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Event;

public interface EventService {
	
	public Event create(Event e);

	public List<Event> getAll();

	public Event find(Long id);

	public long count();
}
