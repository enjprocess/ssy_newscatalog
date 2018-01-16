package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsAttachment;

public interface NewsAttachmentService {

    public List<NewsAttachment> listNewsAttachmentByParentId(long parentId) throws ServiceException;
    
    public void addNewsAttachment(NewsAttachment bean) throws ServiceException;
    
}
