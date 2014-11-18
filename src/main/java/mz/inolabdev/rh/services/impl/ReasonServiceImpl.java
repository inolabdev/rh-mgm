package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Reason;
import mz.inolabdev.rh.services.ReasonService;

import org.springframework.stereotype.Service;

@Service("reasonService")
public class ReasonServiceImpl extends GenericServiceImpl<Reason>
		implements ReasonService {

	//We can also override functions to specify some logics.
}
