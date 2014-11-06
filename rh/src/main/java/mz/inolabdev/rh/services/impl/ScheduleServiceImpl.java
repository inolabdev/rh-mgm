package mz.inolabdev.rh.services.impl;

import org.springframework.stereotype.Service;

import mz.inolabdev.rh.entity.Schedule;
import mz.inolabdev.rh.services.ScheduleService;

@Service("scheduleService")
public class ScheduleServiceImpl extends GenericServiceImpl<Schedule> implements
		ScheduleService {

}
