package mz.inolabdev.rh.services.impl;

import org.springframework.stereotype.Service;

import mz.inolabdev.rh.entity.DocumentType;
import mz.inolabdev.rh.services.DocumentTypeService;

@Service("documentTypeService")
public class DocumentTypeServiceImpl extends GenericServiceImpl<DocumentType>
		implements DocumentTypeService {

}
