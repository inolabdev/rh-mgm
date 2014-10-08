package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Profile;

public interface ProfileService {
    public Profile create(Profile profile);  

	public List<Profile> getAll();

	public Profile find(Long id);

	public Profile update(Profile profile);

	public long count();

	public void delete(Object id);
}
