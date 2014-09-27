package mz.inolabdev.persistence.daoImpl;

import mz.inolabdev.persistence.dao.EventDao;
import mz.inolabdev.persistence.model.Event;

import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao {
	
	 public EventDaoImpl() {
	        super();
	 }

}
