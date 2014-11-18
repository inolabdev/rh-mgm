package mz.inolabdev.rh.entity;

import java.util.Date;
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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "time")
	private Date time;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public Date getTime() {
		return time;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}