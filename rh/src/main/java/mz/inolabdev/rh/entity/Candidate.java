package mz.inolabdev.rh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mz.inolabdev.rh.util.Consts;

@Entity
@Table(name = "candidates")
public class Candidate extends Individual {

	private static final long serialVersionUID = 2338487021821515023L;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "date_of_application")
	private Date dateOfApplication;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "candidates")
	private Set<Vacancy> vacancies = new HashSet<Vacancy>(0);

	private String status; //rejected, hired

	public Candidate() {

		this.setEmails(new HashSet<Email>());
	}
	
	@OneToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;

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
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Candidate))
			return false;

		Candidate othercandidate = (Candidate) other;

		if (this.getName() == null && this.getBirthday() == null
				&& this.getLastName() == null && this.getCellPhones() == null)

			if (othercandidate.getName() != null
					&& othercandidate.getBirthday() != null
					&& othercandidate.getLastName() != null
					&& othercandidate.getCellPhones() != null)
				return false;
			else if (!EqualsUtil.areEqual(this.getName(),
					othercandidate.getName())
					&& !EqualsUtil.areEqual(this.getCellPhones(),
							othercandidate.getCellPhones())
					&& !EqualsUtil.areEqual(this.getBirthday(),
							othercandidate.getBirthday())
					&& !EqualsUtil.areEqual(this.getLastName(),
							othercandidate.getLastName())) {
				return false;
			}
		return true;
	}

	@Transient
	public void addEmail(Email email) {

		this.getEmails().add(email);
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
}
