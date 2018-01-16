package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.DocumentCatalog;

public interface DocumentCatalogDao  {

    public List<DocumentCatalog> listDocumentCatalogByParentId(long parentId) throws DaoException;
}
