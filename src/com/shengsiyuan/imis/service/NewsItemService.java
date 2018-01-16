package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.model.NewsItem;

public interface NewsItemService {
    
    public List<NewsItem> listNewsItem(long start, long range, long parentId) throws ServiceException;
    
    public long getNewsItemByParentId(long parentId) throws ServiceException;
    
    public void addNewsItem(NewsItem bean) throws ServiceException;
    
    public NewsItem getNewsItemById(long id) throws ServiceException;
}
