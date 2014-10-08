package mz.inolabdev.rh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task extends IdEntity {

	private static final long serialVersionUID = 1L;

}
