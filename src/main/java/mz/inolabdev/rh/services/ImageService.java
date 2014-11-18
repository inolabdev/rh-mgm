package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Image;

public interface ImageService {

	public Image create(Image image);

	public List<Image> getAll();

	public Image find(Long id);

	public Image update(Image image);

	public long count();

	public void delete(Object id);
	
	public Image first();
    
    public Image last();
}
