package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;

public interface DocumentCatalogService {

    public List<DocumentCatalog> listDocumentCatalogByTypeAndParentId(long type, long parentId) throws ServiceException;
    
    public DocumentCatalog getDocumentCatalogById(long parentId) throws ServiceException;
    
    public long addDocumentCatalog(DocumentCatalog bean) throws ServiceException;
    
    public void updateDocumenCatalog(DocumentCatalog bean) throws ServiceException;
    
    
}
