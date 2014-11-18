package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Location extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	@Column(name = "ancestry")
	private Character ancestry;

	@Column(name = "prefix")
	private Character prefix;

	@Column(name = "type")
	private String type;
	
	@OneToOne
	@JoinColumn(name="parent_id")
	private Location parent;
	
	@ManyToOne
	@JoinColumn(name = "tenant_id", insertable=false, updatable=false)
	private Tenant tenant;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getAncestry() {
		return ancestry;
	}

	public void setAncestry(Character ancestry) {
		this.ancestry = ancestry;
	}

	public Character getPrefix() {
		return prefix;
	}

	public void setPrefix(Character prefix) {
		this.prefix = prefix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}



}
