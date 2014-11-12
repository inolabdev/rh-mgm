package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.ScheduleDao;
import mz.inolabdev.rh.entity.Schedule;

import org.springframework.stereotype.Repository;

@Repository("scheduleDao")
public class ScheduleDaoImpl extends GenericDaoImpl<Schedule> implements
		ScheduleDao {

}
