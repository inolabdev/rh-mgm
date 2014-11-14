package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Tenant;
import mz.inolabdev.rh.services.TenantService;

import org.springframework.stereotype.Service;

@Service("tenantService")
public class TenantServiceImpl extends GenericServiceImpl<Tenant> implements
		TenantService {
}
