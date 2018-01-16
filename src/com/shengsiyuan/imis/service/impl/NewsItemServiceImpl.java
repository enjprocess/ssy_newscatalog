package com.shengsiyuan.imis.service.impl;

import java.util.List;

import com.shengsiyuan.imis.dao.NewsCatalogDao;
import com.shengsiyuan.imis.dao.NewsItemDao;
import com.shengsiyuan.imis.dao.impl.NewsCatalogDaoImpl;
import com.shengsiyuan.imis.dao.impl.NewsItemDaoImpl;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.service.NewsItemService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.TransactionContext;

public class NewsItemServiceImpl extends AbstractBaseService implements
        NewsItemService {

    @Override
    public List<NewsItem> listNewsItem(long start, long range, long parentId)
            throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsItemDao dao = new NewsItemDaoImpl(context.getConn());

        List<NewsItem> list = null;
        try {
            list = dao.listNewsItem(start, range, parentId);
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_NEWS_CATALOG_ERROR, e);
        }

        return list;
    }

    @Override
    public long getNewsItemByParentId(long parentId) throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsItemDao dao = new NewsItemDaoImpl(context.getConn());

        try {
            
            long count = dao.getNewsItemByParentId(parentId);
            transManager.commitTransaction(context);
            return count;
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_NEWS_ITEM_ERROR, e);
        }
    }

    @Override
    public void addNewsItem(NewsItem bean) throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsItemDao dao = new NewsItemDaoImpl(context.getConn());

        try {
            dao.addNewsItem(bean);
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.ADD_NEWS_ITEM_ERROR, e);
        }
    }

    @Override
    public NewsItem getNewsItemById(long id) throws ServiceException {
        TransactionContext context = transManager.beginTransaction();

        NewsItemDao dao = new NewsItemDaoImpl(context.getConn());

        try {
            NewsItem bean = dao.getNewsItemById(id);
            transManager.commitTransaction(context);
            return bean;
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.ADD_NEWS_ITEM_ERROR, e);
        }
    }

}
