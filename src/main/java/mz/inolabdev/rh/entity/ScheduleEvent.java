package mz.inolabdev.rh.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "schedule_events")
public class ScheduleEvent extends IdEntity{

	private static final long serialVersionUID = -675259690074658945L;

	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Calendar startDate;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Calendar endDate;

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*TO_DO
	 * Set Repeat function;
	 * Set Loaction;
	 * Set Description;
	 * Set reminders
	 *   */
}
