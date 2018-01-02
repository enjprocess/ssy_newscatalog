package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.model.NewsItem;

public interface NewsItemDao {

    public List<NewsItem> listNewsItem(long start, long range, long parentId) throws DaoException;
    
    public long getNewsItemByParentId(long parentId) throws DaoException;
    
    public void addNewsItem(NewsItem bean) throws DaoException;
    
}
