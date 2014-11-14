package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Location;
import mz.inolabdev.rh.services.LocationService;

import org.springframework.stereotype.Service;

@Service("locationService")
public class LocationServiceImpl extends GenericServiceImpl<Location> implements
		LocationService {

}
