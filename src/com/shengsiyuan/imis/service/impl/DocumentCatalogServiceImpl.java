package com.shengsiyuan.imis.service.impl;

import java.util.List;

import com.shengsiyuan.imis.dao.DocumentCatalogDao;
import com.shengsiyuan.imis.dao.impl.DocumentCatalogDaoImpl;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.service.DocumentCatalogService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.TransactionContext;
import com.shengsiyuan.imis.util.TransactionManager;

public class DocumentCatalogServiceImpl extends AbstractBaseService implements
        DocumentCatalogService {

    @Override
    public List<DocumentCatalog> listDocumentCatalogByTypeAndParentId(
            long type, long parentId) throws ServiceException {

        TransactionContext context = transManager.beginTransaction();

        DocumentCatalogDao dao = new DocumentCatalogDaoImpl(context.getConn());

        try {
            List<DocumentCatalog> list = dao
                    .listDocumentCatalogByTypeAndParentId(type, parentId);
            transManager.commitTransaction(context);
            return list;
        } catch (DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_DOCUMENTCATALOG_ERROR, e);
        }

    }

    @Override
    public DocumentCatalog getDocumentCatalogById(long parentId)
            throws ServiceException {
        TransactionContext context = transManager.beginTransaction();

        DocumentCatalogDao dao = new DocumentCatalogDaoImpl(context.getConn());

        try {
            DocumentCatalog bean = dao.getDocumentCatalogById(parentId);
           
            transManager.commitTransaction(context);
            return bean;
        } catch (DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_DOCUMENTCATALOG_ERROR, e);
        }
    }

    @Override
    public long addDocumentCatalog(DocumentCatalog bean)
            throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        DocumentCatalogDao dao = new DocumentCatalogDaoImpl(context.getConn());

        try {
            long id = dao.addDocumentCatalog(bean);
           
            transManager.commitTransaction(context);
            return id;
        } catch (DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.ADD_DOCUMENTCATALOG_ERROR, e);
        }
    }

    @Override
    public void updateDocumenCatalog(DocumentCatalog bean)
            throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        DocumentCatalogDao dao = new DocumentCatalogDaoImpl(context.getConn());

        try {
            dao.updateDocumenCatalog(bean);
           
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.UPDATE_DOCUMENTCATALOG_ERROR, e);
        }
        
    }

}
