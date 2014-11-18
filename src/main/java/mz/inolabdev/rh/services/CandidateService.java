package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Candidate;

public interface CandidateService {

	public Candidate create(Candidate candidate);

	public List<Candidate> getAll();

	public Candidate find(Long id);

	public Candidate update(Candidate candidate);

	public long count();

	public void delete(Object id);
	
	public Candidate first();
    
    public Candidate last();
}
