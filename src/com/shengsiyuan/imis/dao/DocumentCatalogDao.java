package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.DocumentCatalog;

public interface DocumentCatalogDao  {

    public List<DocumentCatalog> listDocumentCatalogByTypeAndParentId(long type, long parentId) throws DaoException;

    public DocumentCatalog getDocumentCatalogById(long parentId) throws DaoException;
    
    public long addDocumentCatalog(DocumentCatalog bean) throws DaoException;
    
    public void updateDocumenCatalog(DocumentCatalog bean) throws DaoException;
    
}
