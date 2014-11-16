package mz.inolabdev.rh.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="file_image")
public class Image extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6530771329378127013L;

	
}
