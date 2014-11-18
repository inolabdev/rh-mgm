package mz.inolabdev.rh.dao;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.TimeSheet;

public interface TimesheetDao extends GenericDao<TimeSheet> {

	public TimeSheet findByWeekAndEmployee(int w, Employee e);
}
