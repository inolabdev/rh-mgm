package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.DocumentTypeDao;
import mz.inolabdev.rh.entity.DocumentType;
@Repository("documentTypeDao")
public class DocumentTypeDaoImpl extends GenericDaoImpl<DocumentType> implements
		DocumentTypeDao {

}
