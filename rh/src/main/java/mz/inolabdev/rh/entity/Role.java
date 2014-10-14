package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "profile")
	private Profile profile;

	@Column(name = "description")
	private String description;

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
