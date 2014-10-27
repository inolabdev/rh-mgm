package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact_points")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="FROM_CLASS", discriminatorType=DiscriminatorType.STRING)
public class ContactPoint extends IdEntity {

	private static final long serialVersionUID = 1L;
	@Column(name="name")
	private String name;
	
	private String type;
	
	@Column(name="value")
	private String value;
	
	@ManyToOne
	@JoinColumn(name = "holder_id")
	private Individual holder;
	
	@Override
	public String toString() {
		return "ContactPoint [type=" + type + ", value=" + value + "]";
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Individual getHolder() {
		return holder;
	}

	public void setHolder(Individual holder) {
		this.holder = holder;
	}


	
}
