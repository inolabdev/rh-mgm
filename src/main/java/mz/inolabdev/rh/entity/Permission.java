package mz.inolabdev.rh.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Objects;

@Entity
@Table(name = "permissions")
public class Permission extends IdEntity implements GrantedAuthority {

    private static final long serialVersionUID = -5404269148967698143L;
    
    @NotNull
    @NotEmpty
    @Column(name = "permissionname")
    private String permissionname;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissions")
    private Set<Role> roles;

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    @Override
    public String getAuthority() {
        return permissionname;
    }

    @Override
    public String toString() {
        return this.permissionname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Role) {
            final Permission other = (Permission) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getPermissionname(), other.getPermissionname());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getPermissionname());
    }

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}