package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.ContactPoint;
import mz.inolabdev.rh.services.ContactPointService;

import org.springframework.stereotype.Service;

@Service("contactPointService")
public class ContactPointServiceImpl extends GenericServiceImpl<ContactPoint>
		implements ContactPointService {
	
}
