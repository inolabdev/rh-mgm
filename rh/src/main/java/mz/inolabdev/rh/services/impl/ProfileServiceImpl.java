package mz.inolabdev.rh.services.impl;

import org.springframework.stereotype.Service;

import mz.inolabdev.rh.entity.Profile;
import mz.inolabdev.rh.services.ProfileService;
@Service("ProfileService")
public class ProfileServiceImpl extends GenericServiceImpl<Profile> implements
		ProfileService {

}
