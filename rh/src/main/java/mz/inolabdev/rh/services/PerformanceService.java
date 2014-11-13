package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Performance;

public interface PerformanceService {

	public Performance create(Performance performance);

	public List<Performance> getAll();

	public Performance find(Long id);

	public long count();

	public Performance first();

	public Performance last();
}
