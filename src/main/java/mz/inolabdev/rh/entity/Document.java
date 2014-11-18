package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="file_document")
public class Document extends File {

	
	private static final long serialVersionUID = -4654818069177689714L;
	
	@Column(name = "category")
	private String category;

	@Column(name = "status")
	private String status;
	
	//private String published_to; we'll set this in future!
	
	//getters and Setters
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
