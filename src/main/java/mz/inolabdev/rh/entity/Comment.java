package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment extends IdEntity{

	private static final long serialVersionUID = 6058550218632091953L;
	
	@Column(columnDefinition = "TEXT")
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
