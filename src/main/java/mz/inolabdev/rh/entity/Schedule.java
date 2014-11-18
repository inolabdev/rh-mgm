package mz.inolabdev.rh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "schedules")
public class Schedule extends IdEntity {

	private static final long serialVersionUID = -894769526798826300L;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "schedule_event_id", insertable = false, updatable = false)
	private Set<ScheduleEvent> events = new HashSet<ScheduleEvent>(0);
	
}
