package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Vacancy;

public interface VacancyService {

	public Vacancy create(Vacancy vacancy);

	public List<Vacancy> getAll();

	public Vacancy find(Long id);

	public Vacancy update(Vacancy vacancy);

	public long count();

	public void delete(Object id);
}
