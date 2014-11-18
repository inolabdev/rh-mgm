package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.VacancyDao;
import mz.inolabdev.rh.entity.Vacancy;

import org.springframework.stereotype.Repository;

@Repository("vacancyDao")
public class VacancyDaoImpl extends GenericDaoImpl<Vacancy> implements VacancyDao {

}
