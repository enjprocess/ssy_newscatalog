package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;

public interface DocumentCatalogService {

    public List<DocumentCatalog> listDocumentCatalogByParentId(long parentId) throws ServiceException;
}
