package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.LocationDao;
import mz.inolabdev.rh.entity.Location;

import org.springframework.stereotype.Repository;

@Repository("locationDao")
public class LocationDaoImp extends GenericDaoImpl<Location> implements
		LocationDao {

}
