package mz.inolabdev.rh.dao;

import mz.inolabdev.rh.entity.Email;

public interface EmailDao extends GenericDao<Email>{

	Email findByValue(String value);

}
