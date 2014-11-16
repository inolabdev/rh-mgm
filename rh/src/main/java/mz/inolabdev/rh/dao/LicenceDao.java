package mz.inolabdev.rh.dao;

import java.util.List;

import mz.inolabdev.rh.entity.License;

public interface LicenceDao extends GenericDao<License> {

	public List<License> findByStatus(String status);
}
