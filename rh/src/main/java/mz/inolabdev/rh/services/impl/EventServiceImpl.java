package mz.inolabdev.rh.services.impl;

import java.util.List;

import mz.inolabdev.rh.dao.EventDao;
import mz.inolabdev.rh.entity.Event;
import mz.inolabdev.rh.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;

	@Override
	public Event create(Event event) {
		return eventDao.create(event);
	}

	@Override
	public Event find(Long id) {
		return eventDao.find(id);
	}

	@Override
	public List<Event> getAll() {
		return eventDao.getAll();
	}

	@Override
	public Long count() {
		return eventDao.count();
	}

}
