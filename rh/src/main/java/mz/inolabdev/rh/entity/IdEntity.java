package mz.inolabdev.rh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.Objects;

@MappedSuperclass
public class IdEntity implements Serializable {

	private static final long serialVersionUID = -6468535868748071777L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	private Date updated;

	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Long getId() {
		return id;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	@Override
    public String toString() {
        return String.format("%s(id=%d)", this.getClass().getSimpleName(), this.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof IdEntity) {
            final IdEntity other = (IdEntity) o;
            return Objects.equal(getId(), other.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
