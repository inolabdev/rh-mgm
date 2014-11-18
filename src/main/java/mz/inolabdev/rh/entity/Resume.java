package mz.inolabdev.rh.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Resume extends IdEntity {

	private static final long serialVersionUID = -6038927717089845138L;
	
	@OneToOne
    @PrimaryKeyJoinColumn
    private  Candidate candidate;
}
