package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Schedule;

public interface ScheduleService {

	public Schedule create(Schedule schedule);

	public List<Schedule> getAll();

	public Schedule find(Long id);

	public Schedule update(Schedule schedule);

	public long count();

	public void delete(Object id);

	public Schedule first();

	public Schedule last();

}
