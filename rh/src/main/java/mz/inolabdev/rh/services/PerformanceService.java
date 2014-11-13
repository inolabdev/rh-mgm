package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Performace;

public interface PerformaceService {

	public Performace create(Performace performace);

	public List<Performace> getAll();

	public Performace find(Long id);

	public long count();

	public Performace first();

	public Performace last();
}
