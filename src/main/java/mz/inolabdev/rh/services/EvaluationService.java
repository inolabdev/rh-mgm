package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Evaluation;


public interface EvaluationService {
	public Evaluation create(Evaluation evaluation);

	public List<Evaluation> getAll();

	public Evaluation find(Long id);

	public long count();

	public Evaluation first();

	public Evaluation last();
}
