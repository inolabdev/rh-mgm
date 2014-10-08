package mz.inolabdev.rh.services.impl;

import org.springframework.stereotype.Service;

import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.UserService;
@Service("UserService")
public class UserServiceImpl extends GenericServiceImpl<User> implements
		UserService {

}
