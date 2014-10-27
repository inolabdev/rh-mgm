package mz.inolabdev.rh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vacancies")
public class Vacancy extends IdEntity {

	private static final long serialVersionUID = -8233857444433947377L;

	private String name;

	private String status;

	@OneToOne
	@JoinColumn(name = "job_title_id")
	private JobPosition jobTitle;
	
	@ManyToOne
	@JoinColumn(name = "sub_unit_id")
	private SubUnit subUnit;

	@ManyToMany
	@JoinTable(name = "job_application", joinColumns = { @JoinColumn(name = "vacancy_id") }, inverseJoinColumns = { @JoinColumn(name = "candidate_id") })
	private Set<Candidate> candidates = new HashSet<Candidate>();

	@ManyToMany
	@JoinTable(name = "job_application", joinColumns = { @JoinColumn(name = "vacancy_id") }, inverseJoinColumns = { @JoinColumn(name = "hiring_manager_id") })
	private Set<Employee> hiringManagers = new HashSet<Employee>();

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + status.hashCode();
		hash = hash * 31 + (status == null ? 0 : status.hashCode());
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

	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
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

}
