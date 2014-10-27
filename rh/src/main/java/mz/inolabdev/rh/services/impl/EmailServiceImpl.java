package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.dao.EmailDao;
import mz.inolabdev.rh.entity.Email;
import mz.inolabdev.rh.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("emailService")
public class EmailServiceImpl extends GenericServiceImpl<Email> implements EmailService{

	@Autowired
	private EmailDao emailDao; 
	
	@Override
	public Email findByValue(String value) {
		return emailDao.findByValue(value);
	}

}
