package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Event;
import mz.inolabdev.rh.services.EventService;

import org.springframework.stereotype.Service;

@Service("eventService")
public class EventServiceImpl extends GenericServiceImpl<Event> implements
		EventService {
	
	//We can also override functions to specify some logics.

	// @Override
	// public Event create(Event e){
	// //validations
	// return new Event();
	// }
	

}
