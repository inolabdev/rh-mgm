package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.Tenant;

public interface TenantService {
	public Tenant create(Tenant tenant);

	public List<Tenant> getAll();

	public Tenant find(Long id);

	public long count();

	public Tenant first();

	public Tenant last();
}
