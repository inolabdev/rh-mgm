package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.EventType;

public interface EventTypeService {

	public EventType create(EventType eventType);

	public List<EventType> getAll();

	public EventType find(Long id);

	public EventType update(EventType eventType);

	public long count();

	public void delete(Object id);
	
	public EventType first();
    
    public EventType last();
}
