package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.entity.DocumentType;

public interface DocumentTypeService {
	
	public DocumentType create(DocumentType documentType);

	public List<DocumentType> getAll();

	public DocumentType find(Long id);

	public DocumentType update(DocumentType documentType);

	public long count();

	public void delete(Object id);
}
