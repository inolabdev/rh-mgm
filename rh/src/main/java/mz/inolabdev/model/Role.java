package mz.inolabdev.model;

import javax.persistence.Table;

@Table(name = "role")
public class Role extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String role_name;
	private Profile profile;
	private String description;

	public String getRole() {
		return role_name;
	}

	public void setRole(String role) {
		this.role_name = role;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
