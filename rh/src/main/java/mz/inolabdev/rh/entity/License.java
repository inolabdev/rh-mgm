package mz.inolabdev.rh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "licences")
public class License extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1492451474522584141L;

	private Integer year;

	@OneToOne
	@JoinColumn(name = "license_type_id")
	private LicenseType license_type;

	private Date begin;

	private Date end;

	@OneToOne
	@JoinColumn(name = "reason_id")
	private Reason reason;
	
	private String description;
	
	private String status;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public LicenseType getLicense_type() {
		return license_type;
	}

	public void setLicense_type(LicenseType license_type) {
		this.license_type = license_type;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
