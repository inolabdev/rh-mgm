package mz.inolabdev.model;

import java.util.Date;

public class Event {
	
	private String type;
	private String hapenned;
	private Date created_at;
	private Date updated_at;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHapenned() {
		return hapenned;
	}

	public void setHapenned(String hapenned) {
		this.hapenned = hapenned;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}
