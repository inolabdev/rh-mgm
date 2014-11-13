package mz.inolabdev.rh.dao.impl;

import mz.inolabdev.rh.dao.TenantDao;
import mz.inolabdev.rh.entity.Tenant;

import org.springframework.stereotype.Repository;

@Repository("tenantDao")
public class TenantDaoImpl extends GenericDaoImpl<Tenant> implements TenantDao {

}
