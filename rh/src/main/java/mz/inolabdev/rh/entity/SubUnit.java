package mz.inolabdev.rh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sub_unities")
public class SubUnit extends IdEntity {

	private static final long serialVersionUID = 5862508092750681509L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
