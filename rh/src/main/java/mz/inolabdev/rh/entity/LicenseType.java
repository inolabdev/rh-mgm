package mz.inolabdev.rh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "license_types")
public class LicenseType extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2498207089970990920L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
