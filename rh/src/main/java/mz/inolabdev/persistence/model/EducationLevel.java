package mz.inolabdev.persistence.model;

import javax.persistence.Table;

@Table(name = "education_levels")
public class EducationLevel extends IdEntity {

	private static final long serialVersionUID = -2376892484957010589L;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
