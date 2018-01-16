package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.model.NewsCatalog;

public interface NewsAttachmentDao {

    
    public List<NewsAttachment> listNewsAttachmentByParentId(long parentId) throws DaoException;

    public void addNewsAttachment(NewsAttachment bean) throws DaoException;
}
