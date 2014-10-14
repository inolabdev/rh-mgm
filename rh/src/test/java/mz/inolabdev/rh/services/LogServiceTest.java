package mz.inolabdev.rh.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Log;

public class LogServiceTest extends GenericTestUnit {

	@Autowired
	private LogService logService;

	private Log newLog() {

		Log l = new Log();
		l.setMessage("Started test");

		return l;
	}

	@Test
	public void shouldSaveTest() {

		Log log = newLog();
		
		logService.create(log);
		
		assertNotNull(log.getId());
	}
}
