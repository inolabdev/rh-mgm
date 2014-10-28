package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "timesheets")
public class TimeSheet extends IdEntity{

	private static final long serialVersionUID = 6425076522619832700L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Column(name = "project_name")
	private String projectName;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "timeSheet")
//	private List<Activity> activities = new ArrayList<Activity>();
	
	private String status;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
