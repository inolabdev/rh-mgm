package mz.inolabdev.rh.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "cp_email")
public class Email extends ContactPoint {

	private static final long serialVersionUID = 3599817801260457093L;

}
