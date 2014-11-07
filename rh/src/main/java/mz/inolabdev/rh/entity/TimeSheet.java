package mz.inolabdev.rh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "timesheets")
public class TimeSheet extends IdEntity {

	private static final long serialVersionUID = 6425076522619832700L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

//	@ManyToMany
//	@JoinTable(name = "timesheet_project", joinColumns = { @JoinColumn(name = "timesheet_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "project_id", nullable = false, updatable = false) })
//	private Set<Project> projects = new HashSet<Project>();

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

}
