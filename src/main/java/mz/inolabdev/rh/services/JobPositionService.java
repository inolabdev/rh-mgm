package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.JobPosition;

public interface JobPositionService {

    public JobPosition create(JobPosition job);  
    
    public List<JobPosition> getAll();

    public JobPosition find(Long id);
    
    public JobPosition update(JobPosition job); 
 
    public long count();
    
    public void delete(Object id);
    
	public JobPosition first();
    
    public JobPosition last();
}
