package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.EventType;
import mz.inolabdev.rh.services.EventTypeService;

import org.springframework.stereotype.Service;

@Service("eventTypeService")
public class EventTypeServiceImpl extends GenericServiceImpl<EventType>
		implements EventTypeService {

}
