package mz.inolabdev.rh.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "performaces")
public class Performace extends IdEntity {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "performace_id", insertable = false, updatable = false)
	private Set<Evaluation> evaluations;

	@Column(name = "status")
	private String status;

	@Column(name = "objectives")
	private String objectives;

	@Column(name = "feed_back")
	private String feedBack;

	@OneToOne
	@JoinColumn(name = "appraiser")
	private Employee appraiser;

	public String getObjectives() {
		return this.objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getFeedBack() {
		return this.feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public Employee getAppraiser() {
		return this.appraiser;
	}

	public void setAppraiser(Employee appraiser) {
		this.appraiser = appraiser;
	}

	public String getEmployeeStatus() {
		return this.employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	private String employeeStatus;

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
