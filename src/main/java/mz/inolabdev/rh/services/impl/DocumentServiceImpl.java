package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Document;
import mz.inolabdev.rh.services.DocumentService;

import org.springframework.stereotype.Service;

@Service("documentService")
public class DocumentServiceImpl extends GenericServiceImpl<Document> implements DocumentService{

}
