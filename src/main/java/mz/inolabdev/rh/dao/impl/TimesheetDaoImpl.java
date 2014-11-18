package mz.inolabdev.rh.dao.impl;

import javax.persistence.Query;

import mz.inolabdev.rh.dao.TimesheetDao;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.TimeSheet;

import org.springframework.stereotype.Repository;

@Repository("timesheetDao")
public class TimesheetDaoImpl extends GenericDaoImpl<TimeSheet> implements
		TimesheetDao {

	@Override
	public TimeSheet findByWeekAndEmployee(int w, Employee e) {

		Query query = em
				.createQuery("select t from TimeSheet t where t.week = ? and t.employee = ?");
		query.setParameter(1, w);
		query.setParameter(2, e);

		return (TimeSheet) (!query.getResultList().isEmpty() ? query
				.getResultList().get(0) : null);
	}

}
