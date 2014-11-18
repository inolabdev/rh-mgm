package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.EventTypeDao;
import mz.inolabdev.rh.entity.EventType;

@Repository("eventTypeDao")
public class EventTypeDaoImpl extends GenericDaoImpl<EventType> implements
		EventTypeDao {

}
