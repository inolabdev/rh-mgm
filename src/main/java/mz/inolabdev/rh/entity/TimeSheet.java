package mz.inolabdev.rh.entity;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "timesheets")
public class TimeSheet extends IdEntity {

	private static final long serialVersionUID = 6425076522619832700L;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "timesheet_projects", joinColumns = { @JoinColumn(name = "timesheet_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "project_id", nullable = false, updatable = false) })
	private List<Project> projects = new LinkedList<Project>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "timesheet_activities", joinColumns = { @JoinColumn(name = "timesheet_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "activity_id", nullable = false, updatable = false) })
	private List<Activity> activities = new LinkedList<Activity>();

	private Integer week;

	@Lob
	@Column(name = "hours_per_date", columnDefinition = "mediumblob")
	private Hashtable<String, DateHours> dateHours = new Hashtable<String, DateHours>();

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

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Hashtable<String, DateHours> getDateHours() {
		return dateHours;
	}

	public void setDateHours(Hashtable<String, DateHours> dateHours) {
		this.dateHours = dateHours;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(LinkedList<Project> projects) {
		this.projects = projects;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(LinkedList<Activity> activities) {
		this.activities = activities;
	}

}
