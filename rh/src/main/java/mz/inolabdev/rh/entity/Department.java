package mz.inolabdev.rh.entity;

import javax.persistence.Table;

@Table(name = "departaments")
public class Department extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private Employee responsible;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getResponsible() {
		return responsible;
	}

	public void setResponsible(Employee responsible) {
		this.responsible = responsible;
	}

}
