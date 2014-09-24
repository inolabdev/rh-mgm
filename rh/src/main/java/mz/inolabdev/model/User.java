package mz.inolabdev.model;

import java.util.Date;

import javax.persistence.Table;

@Table(name = "user")
public class User extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private String last_name;
	private String email;
	private String password;
	private String password_confirmation;
	private Profile profile;
	private String description;
	private Date created_at;
	private Date updated_at;
	private int sign_in_count;
	private Date current_sign_in_at;
	private Date last_sign_in_at;

	public int getSign_in_count() {
		return sign_in_count;
	}

	public void setSign_in_count(int sign_in_count) {
		this.sign_in_count = sign_in_count;
	}

	public Date getCurrent_sign_in_at() {
		return current_sign_in_at;
	}

	public void setCurrent_sign_in_at(Date current_sign_in_at) {
		this.current_sign_in_at = current_sign_in_at;
	}

	public Date getLast_sign_in_at() {
		return last_sign_in_at;
	}

	public void setLast_sign_in_at(Date last_sign_in_at) {
		this.last_sign_in_at = last_sign_in_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPassword_confirmation() {
		return password_confirmation;
	}

	public void setPassword_confirmation(String password_confirmation) {
		this.password_confirmation = password_confirmation;
	}

}
