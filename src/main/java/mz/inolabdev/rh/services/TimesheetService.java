package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.TimeSheet;

public interface TimesheetService {
	
	public TimeSheet create(TimeSheet timeSheet);

	public List<TimeSheet> getAll();

	public TimeSheet find(Long id);

	public TimeSheet update(TimeSheet timeSheet);

	public long count();

	public void delete(Object id);
	
	public TimeSheet first();
    
    public TimeSheet last();
    
    public TimeSheet findByWeekAndEmployee(int w, Employee e);
}
