package com.shengsiyuan.imis.service.impl;

import java.util.List;

import com.shengsiyuan.imis.dao.NewsAttachmentDao;
import com.shengsiyuan.imis.dao.NewsCatalogDao;
import com.shengsiyuan.imis.dao.impl.NewsAttachmentDaoImpl;
import com.shengsiyuan.imis.dao.impl.NewsCatalogDaoImpl;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.service.NewsAttachmentService;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.TransactionContext;

public class NewsAttachmentServiceImpl extends AbstractBaseService implements
        NewsAttachmentService {

    @Override
    public List<NewsAttachment> listNewsAttachmentByParentId(long parentId)
            throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsAttachmentDao dao = new NewsAttachmentDaoImpl(context.getConn());

        List<NewsAttachment> list = null;
        try {
            list = dao.listNewsAttachmentByParentId(parentId);
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.LIST_NEWS_CATALOG_ERROR, e);
        }

        return list;
    }

    @Override
    public void addNewsAttachment(NewsAttachment bean) throws ServiceException {
        
        TransactionContext context = transManager.beginTransaction();

        NewsAttachmentDao dao = new NewsAttachmentDaoImpl(context.getConn());

        try {
            
            dao.addNewsAttachment(bean);
            
            transManager.commitTransaction(context);
        } catch (DaoException e) {
            transManager.rollbackTransaction(context);
            throw new ServiceException(ErrCode.ADD_NEWS_ATTACHMENT_ERROR, e);
        }

    }

}
