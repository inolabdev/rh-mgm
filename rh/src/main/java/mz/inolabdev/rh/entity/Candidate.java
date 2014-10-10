package mz.inolabdev.rh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "candidates")
public class Candidate extends Individual {

	private static final long serialVersionUID = 2338487021821515023L;

	@OneToMany(mappedBy = "candidate")
	private Set<Document> documents;

	@Temporal(value = TemporalType.DATE)
	private Date dateOfApplication;

	@OneToMany(mappedBy = "candidate")
	private Set<ContactPoint> emails;

	@OneToMany(mappedBy = "candidate")
	private Set<ContactPoint> cellphones;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "candidates")
	private Set<Vacancy> vacancies = new HashSet<Vacancy>(0);

	private String status;

	@Override
	public int hashCode() {
		int hash = 1;
		// hash = hash * 31 + status.hashCode();
		hash = hash * 31 + (getId() == null ? 0 : getId().hashCode());
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Candidate))
			return false;

		Candidate othercandidate = (Candidate) other;

		// Two candidates with the same IdNumber are the same candidate;
		return getName().equals(othercandidate.getName()) ;  // EqualsUtil.areEqual(status, othercandidate.status) &&
		//EqualsUtil.areEqual(getName(), othercandidate.getName());
	}

	// getters and setters
	public Date getDateOfApplication() {
		return dateOfApplication;
	}

	public void setDateOfApplication(Date dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}

	public Set<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(Set<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}

	public Set<ContactPoint> getEmails() {
		return emails;
	}

	public void setEmails(Set<ContactPoint> emails) {
		this.emails = emails;
	}

	public Set<ContactPoint> getCellphones() {
		return cellphones;
	}

	public void setCellphones(Set<ContactPoint> cellphones) {
		this.cellphones = cellphones;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
}
