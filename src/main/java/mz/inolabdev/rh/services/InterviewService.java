package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Interview;

public interface InterviewService {

	public Interview create(Interview interview);

	public List<Interview> getAll();

	public Interview find(Long id);

	public Interview update(Interview interview);

	public long count();

	public void delete(Object id);

	public Interview first();

	public Interview last();

}
