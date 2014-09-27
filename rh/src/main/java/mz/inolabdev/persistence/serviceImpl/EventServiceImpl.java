package mz.inolabdev.persistence.serviceImpl;

import java.util.List;

import mz.inolabdev.persistence.dao.EventDao;
import mz.inolabdev.persistence.model.Event;
import mz.inolabdev.persistence.service.EventService;

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
	public void delete(Long id) {
		eventDao.delete(id);
	}

	@Override
	public Event update(Event event) {
		return eventDao.update(event);
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
