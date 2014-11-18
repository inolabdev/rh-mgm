package mz.inolabdev.rh.entity;

	public enum MaritalStatus {
	    Casado("Casado"), 
	    Solteiro("Solteiro"), 
	    Divorciado("Divorciado"), 
	    UniaoDeFacto("Uniao De Facto"),
	    Viuvo("Viuvo");
	    
	    final String value;
	    
	    MaritalStatus(String value) {
	        this.value = value;
	    }

		public String getValue() {
			return this.value;
		}
	};

