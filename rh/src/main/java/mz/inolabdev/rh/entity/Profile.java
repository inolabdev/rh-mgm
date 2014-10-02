package mz.inolabdev.rh.entity;

import java.util.List;

import javax.persistence.Table;
@Table(name="profiles")
public class Profile extends IdEntity{

	private static final long serialVersionUID = 1L;
	private String type;
	private List<Role> roles;
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
