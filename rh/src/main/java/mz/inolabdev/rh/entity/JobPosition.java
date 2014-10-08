package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_positions")
public class JobPosition extends IdEntity {

	private static final long serialVersionUID = -2994535517681373646L;

	@Column(name = "type")
	private String type;

	@Column(name = "description")
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

	@Override
	public String toString() {
		return "JobPosition [type=" + type + ", description=" + description
				+ "]";
	}

}
