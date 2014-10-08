package mz.inolabdev.rh.services;

import java.util.List;

import com.lowagie.text.Document;

public interface DocumentService {
	public Document create(Document document);

	public List<Document> getAll();

	public Document find(Long id);

	public Document update(Document document);

	public long count();

	public void delete(Object id);
}
