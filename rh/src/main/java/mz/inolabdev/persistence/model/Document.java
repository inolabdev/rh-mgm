package mz.inolabdev.persistence.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "documents")
public class Document extends IdEntity {

	private static final long serialVersionUID = -4654818069177689714L;
	private String name;
	@ManyToOne
	@JoinColumn(name = "documentType")
	private DocumentType documentType;
	private String category;
	private String status;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String published_to;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublished_to() {
		return published_to;
	}

	public void setPublished_to(String published_to) {
		this.published_to = published_to;
	}

	private Employee employee;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

}
