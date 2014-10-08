package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.TaskDao;
import mz.inolabdev.rh.entity.Task;
@Repository("taskDao")
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {

}
