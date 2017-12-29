package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;

public interface NewsCatalogService {

    public void addNewsCatalog(NewsCatalog bean) throws ServiceException;
    
    public List<NewsCatalog> listNewsCatalog() throws ServiceException;
    
    public List<NewsCatalog> listNewsCatalog(long parentId, long start, long range) throws ServiceException;
    
    public boolean isNewsCatalogExists(long parentId, String name) throws ServiceException;
    
    public NewsCatalog getNewsCatalogById(long id) throws ServiceException;
    
    public List<NewsCatalog> listAllParentLevelNewsCatalog(long parentId) throws ServiceException;
}
