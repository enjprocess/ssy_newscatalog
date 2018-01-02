package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.NewsCatalog;

public interface NewsCatalogDao {

    public void addNewsCatalog(NewsCatalog bean) throws DaoException;
    
    public List<NewsCatalog> listNewsCatalog() throws DaoException;

    public List<NewsCatalog> listNewsCatalog(long parentId, long start, long range) throws DaoException;
    
    public long getNewCatalogCountByParentIdAndName(long parentId, String name) throws DaoException;
    
    public NewsCatalog getNewsCatalogById(long id) throws DaoException;
    
    public List<NewsCatalog> listNewsCatalogByParentId(long parentId) throws DaoException;
    
    public void updateNewsCatalog(NewsCatalog bean) throws DaoException;
    
    public long getNewsCatalogByParentId(long parentId) throws DaoException;
    
    public void deleteNewsCatalogById(long id) throws DaoException; 
}
