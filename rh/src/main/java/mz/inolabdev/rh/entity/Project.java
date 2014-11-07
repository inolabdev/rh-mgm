package mz.inolabdev.rh.entity;

import java.util.HashSet;
import java.util.Set;

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
	private Set<Employee> customers = new HashSet<Employee>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "admin_projects", joinColumns = { @JoinColumn(name = "project_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "admin_id", nullable = false, updatable = false) })
	private Set<Employee> admins = new HashSet<Employee>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<Activity> activities = new HashSet<Activity>();

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

	public Set<Employee> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Employee> customers) {
		this.customers = customers;
	}

	public Set<Employee> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Employee> admins) {
		this.admins = admins;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
