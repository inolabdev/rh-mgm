package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.LicenseType;
import mz.inolabdev.rh.services.LicenceTypeService;

import org.springframework.stereotype.Service;

@Service("licenceTypeService")
public class LicenceTypeServiceImpl extends GenericServiceImpl<LicenseType>
		implements LicenceTypeService {

	//We can also override functions to specify some logics.
}
