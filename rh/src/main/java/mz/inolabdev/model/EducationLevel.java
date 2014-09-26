package mz.inolabdev.model;

import java.util.Date;

import javax.persistence.Table;
@Table(name="education_levels")
public class EducationLevel extends IdEntity {

	private static final long serialVersionUID = -2376892484957010589L;
	private String description;
	private Date created_at;	
	private Date updated_at;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}
