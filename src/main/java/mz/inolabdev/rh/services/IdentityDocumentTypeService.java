package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.IdentityDocumentType;

public interface IdentityDocumentTypeService {
	
	public IdentityDocumentType create(IdentityDocumentType identityDocumentType);

	public List<IdentityDocumentType> getAll();

	public IdentityDocumentType find(Long id);

	public IdentityDocumentType update(IdentityDocumentType identityDocumentType);

	public long count();

	public void delete(Object id);
}
