package mz.inolabdev.rh.util;

public enum EducationLevels {
	
    NivelPrimario("Primario"), 
    NivelBasico("Basico"), 
    NivelMedio("Medio"), 
    NivelSuperior("Superior");
 
    
    final String value;
    
    EducationLevels(String value) {
        this.value = value;
    }

	public String getValue() {
		return this.value;
	}
};
