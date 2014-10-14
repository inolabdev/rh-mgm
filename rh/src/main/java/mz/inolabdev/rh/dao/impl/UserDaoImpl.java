package mz.inolabdev.rh.dao.impl;
import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.UserDao;
import mz.inolabdev.rh.entity.User;
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{

}
