package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.ScheduleEventDao;
import mz.inolabdev.rh.entity.ScheduleEvent;

import org.springframework.stereotype.Repository;

@Repository("scheduleEventDao")
public class ScheduleEventDaoImpl extends GenericDaoImpl<ScheduleEvent>
		implements ScheduleEventDao {

}
