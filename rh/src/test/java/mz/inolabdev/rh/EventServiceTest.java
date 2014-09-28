package mz.inolabdev.rh;

import java.util.Calendar;
import java.util.List;

import mz.inolabdev.rh.entity.Event;
import mz.inolabdev.rh.services.EventService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EventServiceTest extends GenericTestUnit {

	@Autowired
	private EventService eventService;

	private Event createNewEvent() {
		Event event = new Event();
		event.setType("First Type Event");
		event.setCreated_at(Calendar.getInstance().getTime());
		return event;
	}

	@Test
	public void createTest() {

		Event event = createNewEvent();

		Event eventSaved = eventService.create(event);

		Assert.assertNotNull(eventSaved);
		Assert.assertNotNull(eventSaved.getId());
	}

	@Test
	public void findTest() {
		Event event = createNewEvent();

		Event eventSaved = eventService.create(event);
		Event eventDeleted = eventService.find(eventSaved.getId());

		Assert.assertNotNull(eventDeleted);
	}

	@Test
	public void countTest() {
		Event event = createNewEvent();

		long countBefore = eventService.count();
		eventService.create(event);
		long countAfter = eventService.count();

		Assert.assertEquals(countBefore, countAfter - 1);
	}

	@Test
	public void getAllTest() {
		this.createTest();
		List<Event> all = eventService.getAll();

		Assert.assertNotNull(all);
		Assert.assertTrue(all.size() > 0);
	}

}
