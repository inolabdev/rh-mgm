package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.ActivityDao;
import mz.inolabdev.rh.entity.Activity;

import org.springframework.stereotype.Repository;

@Repository("activityDao")
public class ActivityDaoImpl extends GenericDaoImpl<Activity> implements
		ActivityDao {

}
