package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Candidate;
import mz.inolabdev.rh.services.CandidateService;

import org.springframework.stereotype.Service;

@Service("candidateService")
public class CandidateServiceImpl extends GenericServiceImpl<Candidate>
		implements CandidateService {

}
