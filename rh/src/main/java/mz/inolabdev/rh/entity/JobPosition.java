package mz.inolabdev.rh.entity;

import javax.persistence.Table;

@Table(name = "job_positions")
public class JobPosition extends IdEntity {

	private static final long serialVersionUID = -2994535517681373646L;
	private String type;
	private String description;

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
