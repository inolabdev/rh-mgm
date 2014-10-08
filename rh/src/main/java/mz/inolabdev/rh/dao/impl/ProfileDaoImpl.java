package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.ProfileDao;
import mz.inolabdev.rh.entity.Profile;
@Repository("profileDao")
public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements
		ProfileDao {

}
