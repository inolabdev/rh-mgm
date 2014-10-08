package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Task;

public interface TaskService {
	public Task create(Task task);

	public List<Task> getAll();

	public Task find(Long id);

	public Task update(Task task);

	public long count();

	public void delete(Object id);
}
