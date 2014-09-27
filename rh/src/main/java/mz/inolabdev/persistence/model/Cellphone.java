package mz.inolabdev.persistence.model;

import javax.persistence.Table;

@Table(name = "cell_phones")
public class Cellphone extends ContactPoint {

	private static final long serialVersionUID = 1L;
	private String name;
	private String prefix;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
