package mz.inolabdev.rh.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 28723899533971644L;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customers_projects", joinColumns = { @JoinColumn(name = "project_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) })
	private List<Employee> customers;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "admin_projects", joinColumns = { @JoinColumn(name = "project_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "admin_id", nullable = false, updatable = false) })
	private List<Employee> admins;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private List<Activity> activities;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Employee> customers) {
		this.customers = customers;
	}

	public List<Employee> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Employee> admins) {
		this.admins = admins;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

}
