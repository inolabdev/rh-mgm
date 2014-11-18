package mz.inolabdev.rh.services.impl;

import org.springframework.stereotype.Service;

import mz.inolabdev.rh.entity.Task;
import mz.inolabdev.rh.services.TaskService;
@Service("taskService")
public class TaskServiceImpl extends GenericServiceImpl<Task> implements
		TaskService {

}
