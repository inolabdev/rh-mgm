package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.EventDao;
import mz.inolabdev.rh.entity.Event;

import org.springframework.stereotype.Repository;

@Repository("eventDao")
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao {

}
