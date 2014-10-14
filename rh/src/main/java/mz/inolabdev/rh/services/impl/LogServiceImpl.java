package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Log;
import mz.inolabdev.rh.services.LogService;

import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl extends GenericServiceImpl<Log>
		implements LogService {

	//We can also override functions to specify some logics.
}
