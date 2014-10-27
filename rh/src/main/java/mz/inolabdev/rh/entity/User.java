package mz.inolabdev.rh.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Objects;

@Entity
@Table(name = "users")
public class User extends IdEntity implements UserDetails {

	private static final long serialVersionUID = 6311364761937265306L;

	@NotNull
	@NotEmpty
	@Column(name = "username", unique = true)
	private String username;

	@NotNull
	@NotEmpty
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profile_id")
	@NotNull
	private Employee userProfile;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roles;

	public User() {

		this.roles = new HashSet<Role>();
	}

	@Transient
	public void addRole(Role role) {
		this.roles.add(role);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return String.format("%s(id=%d, username=%s, password=%s, enabled=%b)",
				this.getClass().getSimpleName(), this.getId(),
				this.getUsername(), this.getPassword(), this.getEnabled());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;

		if (o instanceof User) {
			final User other = (User) o;
			return Objects.equal(getId(), other.getId())
					&& Objects.equal(getUsername(), other.getUsername())
					&& Objects.equal(getPassword(), other.getPassword())
					&& Objects.equal(getEnabled(), other.getEnabled());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId(), getUsername(), getPassword(),
				getEnabled());
	}

	@Transient
	public Set<Permission> getPermissions() {
		Set<Permission> perms = new HashSet<Permission>();
		for (Role role : roles) {
			perms.addAll(role.getPermissions());
		}
		return perms;
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.addAll(getRoles());
		authorities.addAll(getPermissions());
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// return true = account is valid / not expired
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// return true = account is not locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// return true = password is valid / not expired
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

	public Employee getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Employee userProfile) {
		this.userProfile = userProfile;
	}

}