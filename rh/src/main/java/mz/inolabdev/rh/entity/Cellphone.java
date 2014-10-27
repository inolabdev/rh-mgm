package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "cp_cell_phone")
public class Cellphone extends ContactPoint {

	private static final long serialVersionUID = 1L;
	@Column(name = "prefix")
	private String prefix;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
