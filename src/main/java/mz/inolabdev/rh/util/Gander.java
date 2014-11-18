package mz.inolabdev.rh.util;

public enum Gander {

	Masculino("Masculino"),
	Femennino("Femenino"),
	Outro("Outro");

	final String value;

	Gander(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
};