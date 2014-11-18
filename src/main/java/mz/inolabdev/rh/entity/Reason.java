package mz.inolabdev.rh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reasons")
public class Reason extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5771830740530732109L;
	
	private String type;
	
	private String description;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
