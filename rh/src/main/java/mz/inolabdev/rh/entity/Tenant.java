package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tenants")
public class Tenant extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	@Column(name = "url_identifier")
	private Character urlIdentifier;

	@Column(name = "time_zone")
	private Character timeZone;

	public Character getUrlIdentifier() {
		return urlIdentifier;
	}

	public void setUrlIdentifier(Character urlIdentifier) {
		this.urlIdentifier = urlIdentifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Character timeZone) {
		this.timeZone = timeZone;
	}

}
