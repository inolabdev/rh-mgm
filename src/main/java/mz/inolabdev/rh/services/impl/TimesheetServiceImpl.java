package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.dao.TimesheetDao;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.TimeSheet;
import mz.inolabdev.rh.services.TimesheetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("timesheetService")
public class TimesheetServiceImpl extends GenericServiceImpl<TimeSheet>
		implements TimesheetService {

	@Autowired
	private TimesheetDao timesheetDao;

	@Override
	public TimeSheet findByWeekAndEmployee(int w, Employee e) {
		return timesheetDao.findByWeekAndEmployee(w, e);
	}
}
