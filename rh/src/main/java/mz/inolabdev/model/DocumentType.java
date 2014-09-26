package mz.inolabdev.model;

import javax.persistence.Table;

@Table(name = "document_types")
public class DocumentType extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
