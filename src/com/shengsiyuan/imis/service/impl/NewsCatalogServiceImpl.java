package com.shengsiyuan.imis.service.impl;

import java.util.List;

import com.shengsiyuan.imis.dao.NewsCatalogDao;
import com.shengsiyuan.imis.dao.impl.NewsCatalogDaoImpl;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.TransactionContext;

public class NewsCatalogServiceImpl extends AbstractBaseService implements
        NewsCatalogService {

    @Override
    public void addNewsCatalog(NewsCatalog bean) throws ServiceException {

        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        try {
            dao.addNewsCatalog(bean);
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.ADD_NEWS_CATALOG_ERROR, e);
        }
    }

    @Override
    public List<NewsCatalog> listNewsCatalog() throws ServiceException {

        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        List<NewsCatalog> list = null;
        try {
            list = dao.listNewsCatalog();
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_NEWS_CATALOG_ERROR, e);
        }

        return list;
    }

    @Override
    public List<NewsCatalog> listNewsCatalog(long parentId, long start,
            long range) throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        List<NewsCatalog> list = null;
        try {
            list = dao.listNewsCatalog(parentId, start, range);
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_NEWS_CATALOG_ERROR, e);
        }

        return list;
    }

    @Override
    public boolean isNewsCatalogExists(long parentId, String name)
            throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        List<NewsCatalog> list = null;
        try {
            long count = dao.getNewCatalogCountByParentIdAndName(parentId, name);
            if (count > 0) {
                return true;
            }
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_NEWS_CATALOG_ERROR, e);
        }

        return false;
    }

    @Override
    public NewsCatalog getNewsCatalogById(long id) throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        try {
            NewsCatalog newsCatalog = dao.getNewsCatalogById(id);
            transManager.commitTransaction(context);
            return newsCatalog;
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_NEWS_CATALOG_ERROR, e);
        }

    }

    @Override
    public List<NewsCatalog> listAllParentLevelNewsCatalog(long parentId)
            throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        List<NewsCatalog> list = null;
        try {
            NewsCatalog bean = dao.getNewsCatalogById(parentId);
            list = dao.listNewsCatalogByParentId(bean.getParentId());
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_NEWS_CATALOG_ERROR, e);
        }

        return list;
    }

    @Override
    public void updateNewsCatalog(NewsCatalog bean) throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        try {
            dao.updateNewsCatalog(bean);
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.UPDATE_NEWS_CATALOG_ERROR, e);
        }
    }

    @Override
    public boolean deleteNewsCatalog(long id) throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        try {
            
            long count = dao.getNewsCatalogByParentId(id);
            if (0 == count) {
                dao.deleteNewsCatalogById(id);
                transManager.commitTransaction(context);
                return true;
            }
            
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.UPDATE_NEWS_CATALOG_ERROR, e);
        }
        
        return false;
    }

    @Override
    public long getNewsCatalogByParentId(long id) throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsCatalogDao dao = new NewsCatalogDaoImpl(context.getConn());

        try {
            
            long count = dao.getNewsCatalogByParentId(id);
            transManager.commitTransaction(context);
            return count;
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.UPDATE_NEWS_CATALOG_ERROR, e);
        }
    }
    

}
