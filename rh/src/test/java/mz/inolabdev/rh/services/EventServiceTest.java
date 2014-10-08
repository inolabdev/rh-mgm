package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Event;
import mz.inolabdev.rh.entity.EventType;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EventServiceTest extends GenericTestUnit {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventTypeService eventTypeService;

	private EventType CreateNewEventType() {
		EventType eventType = new EventType();
		eventType.setName("Registar colaboradores");
		eventType.setDescription("Processo de Registo de Colaboradores");
		return eventType;
	}

	private EventType createEventTypeTest() {
		EventType eventType = CreateNewEventType();
		eventTypeService.create(eventType);

		return eventType;
	}

	private Event createNewEvent() {

		Event event = new Event();
		event.setEventType(createEventTypeTest());

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
