package mz.inolabdev.rh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mz.inolabdev.rh.util.Consts;

@Entity
@Table(name = "vacancies")
public class Vacancy extends IdEntity {

	private static final long serialVersionUID = -8233857444433947377L;

	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "begin_date")
	private Date beginDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	private String status;

	@OneToOne
	@JoinColumn(name = "job_title_id")
	private JobPosition jobTitle;

	@ManyToOne
	@JoinColumn(name = "sub_unit_id")
	private SubUnit subUnit;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "job_interviewers", joinColumns = { @JoinColumn(name = "vacancy_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "hiring_manager_id", referencedColumnName = "id") })
	private Set<Employee> hiringManagers = new HashSet<Employee>();

	@Override
	public int hashCode() {
		int hash = Consts.ONE;
		// hash = hash * 31 + status.hashCode();
		hash = hash * Consts.THIRTY_ONE
				+ (getId() == null ? Consts.ONE : getId().hashCode());
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Candidate))
			return false;

		Vacancy otherVacancy = (Vacancy) other;

		return EqualsUtil.areEqual(name, otherVacancy.name)
				&& EqualsUtil.areEqual(jobTitle, otherVacancy.jobTitle)
				&& EqualsUtil.areEqual(hiringManagers,
						otherVacancy.hiringManagers)
				&& EqualsUtil.areEqual(status, otherVacancy.status);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	// getters and setters
	public JobPosition getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobPosition jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getHiringManagers() {
		return hiringManagers;
	}

	public void setHiringManagers(Set<Employee> hiringManagers) {
		this.hiringManagers = hiringManagers;
	}

	public SubUnit getSubUnit() {
		return subUnit;
	}

	public void setSubUnit(SubUnit subUnit) {
		this.subUnit = subUnit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
