package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.ScheduleEvent;

public interface ScheduleEventService {

	public ScheduleEvent create(ScheduleEvent scheduleEvent);

	public List<ScheduleEvent> getAll();

	public ScheduleEvent find(Long id);

	public ScheduleEvent update(ScheduleEvent scheduleEvent);

	public long count();

	public void delete(Object id);

	public ScheduleEvent first();

	public ScheduleEvent last();

}
