package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact_points")
public class ContactPoint extends IdEntity {

	private static final long serialVersionUID = 1L;
	@Column(name="name")
	private String name;
	
	@Column(name="value")
	private int value;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
