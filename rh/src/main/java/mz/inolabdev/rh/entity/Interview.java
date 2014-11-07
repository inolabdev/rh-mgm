package mz.inolabdev.rh.entity;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="interviews")
public class Interview extends IdEntity {

	private static final long serialVersionUID = -8237840719768818488L;
	

	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time")
	private Calendar dateAndTime;
	
	private String status; //,failed, passed
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "interview_id", insertable = false, updatable = false)
	private Set<Employee> interviewers;
	
	public String getTitle() {
		return title;
	}
	
	public Set<Employee> getInterviewers() {
		return interviewers;
	}

	public void setInterviewers(Set<Employee> interviewers) {
		this.interviewers = interviewers;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Calendar dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}