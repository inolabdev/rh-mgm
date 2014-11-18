package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event extends IdEntity {

	private static final long serialVersionUID = -3956819043500146619L;
	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "eventType_id")
	private EventType eventType;

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
