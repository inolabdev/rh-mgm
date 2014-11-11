package mz.inolabdev.rh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DateHours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1928469566308813172L;

	@Temporal(TemporalType.DATE)
	private Date day;

	private Integer hours;

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}
}
