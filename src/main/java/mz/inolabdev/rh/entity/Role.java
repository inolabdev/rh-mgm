package mz.inolabdev.rh.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Objects;

@Entity
@Table(name = "roles")
public class Role extends IdEntity implements Serializable, GrantedAuthority  {

    private static final long serialVersionUID = 6874667425302308430L;

    @NotNull
    @NotEmpty
    @Column(name = "rolename")
    private String rolename;
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permissions", joinColumns = { 
			@JoinColumn(name = "role_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "permission_id", 
					nullable = false, updatable = false) })
    private Set<Permission> permissions;
    
    public Role() {
		
    	permissions = new HashSet<Permission>();
	}

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<Permission> getPermissions() { 
        return permissions; 
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    
    @Transient
	public void addPermition(Permission per){
    	this.permissions.add(per);
    }
    
    @Override
    public String toString() {
        return rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Role) {
            final Role other = (Role) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getRolename(), other.getRolename());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getRolename());
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }
}
