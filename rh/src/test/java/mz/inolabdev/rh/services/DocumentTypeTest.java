package mz.inolabdev.rh.services;

import static org.junit.Assert.assertNotNull;
import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.DocumentType;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentTypeTest extends GenericTestUnit {

	@Autowired
	private DocumentTypeService documentTypeService;

	private DocumentType createNewDocumentType() {
		DocumentType documentType = new DocumentType();
		documentType.setType("BI");
		return documentType;
	}

	@Test
	public void createTest() {

		DocumentType documentType = createNewDocumentType();

		DocumentType documentTypeSaved = documentTypeService
				.create(documentType);

		assertNotNull(documentTypeSaved);
		assertNotNull(documentTypeSaved.getId());
	}

}
