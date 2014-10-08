package mz.inolabdev.rh.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee extends Individual {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "jobPosition_id")
	private JobPosition jobPosition;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

	public JobPosition getJobPosition() {
		return jobPosition;
	}

	public void setJob_position(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

}
