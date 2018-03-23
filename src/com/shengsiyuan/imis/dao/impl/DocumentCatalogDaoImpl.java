package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.shengsiyuan.imis.dao.DocumentCatalogDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.model.Attributes;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class DocumentCatalogDaoImpl extends AbstractBaseDao implements
        DocumentCatalogDao {

    public DocumentCatalogDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<DocumentCatalog> listDocumentCatalogByTypeAndParentId(
            long type, long parentId) throws DaoException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ")
                .append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append(" where type = ? and parentId = ? order by id desc");

        PreparedStatement ps;
        try {
            List<DocumentCatalog> list = new ArrayList<DocumentCatalog>();
            ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, type);
            ps.setLong(2, parentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                DocumentCatalog bean = new DocumentCatalog();

                bean.setId(rs.getLong("id"));
                bean.setName(rs.getString("name"));
                bean.setParentId(rs.getLong("parentId"));
                bean.setType(rs.getLong("type"));
                bean.setLeaf(rs.getBoolean("leaf"));

                list.add(bean);
            }

            return list;

        } catch (SQLException e) {
            throw new DaoException(ErrCode.LIST_DOCUMENTCATALOG_ERROR, e);
        }

    }

    @Override
    public DocumentCatalog getDocumentCatalogById(long parentId)
            throws DaoException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ")
                .append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append(" where id = ?");

        PreparedStatement ps = null;
        try {
            
            ps = conn.prepareStatement(sb.toString());
            
            ps.setLong(1, parentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                DocumentCatalog bean = new DocumentCatalog();

                bean.setId(rs.getLong("id"));
                bean.setName(rs.getString("name"));
                bean.setParentId(rs.getLong("parentId"));
                bean.setType(rs.getLong("type"));
                bean.setLeaf(rs.getBoolean("leaf"));

                return bean;
            }


        } catch (SQLException e) {
            throw new DaoException(ErrCode.LIST_DOCUMENTCATALOG_ERROR, e);
        }
        return null;
    }

    @Override
    public long addDocumentCatalog(DocumentCatalog bean) throws DaoException {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into ")
                .append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append("(name, type, parentId, leaf) values (?, ?, ?, ?)");

        PreparedStatement ps = null;
        try {
            //mysql驱动包的问题，升级后，如果需要用到getGeneratedKeys方法则要在获得PreparedStatement对象的时候加上如下参数
            ps = conn.prepareStatement(sb.toString(),Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, bean.getName());
            ps.setLong(2, bean.getType());
            ps.setLong(3, bean.getParentId());
            ps.setBoolean(4, bean.isLeaf());
            
            ps.executeUpdate();
            
            
            ResultSet rs = ps.getGeneratedKeys();

            while (rs.next()) {
                return rs.getLong(1);
            }


        } catch (SQLException e) {
            throw new DaoException(ErrCode.LIST_DOCUMENTCATALOG_ERROR, e);
        }
        //无意义键值
        return -1;
    }

    @Override
    public void updateDocumenCatalog(DocumentCatalog bean) throws DaoException {
        
        StringBuffer sb = new StringBuffer();
        sb.append("update ")
                .append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append(" set name = ?, parentId = ?, leaf = ?,  type = ? where id = ?");

        PreparedStatement ps = null;
        try {
            
            ps = conn.prepareStatement(sb.toString());
            
            ps.setString(1, bean.getName());
            ps.setLong(2, bean.getParentId());
            ps.setBoolean(3, bean.isLeaf());
            ps.setLong(4, bean.getType());
            ps.setLong(5, bean.getId());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new DaoException(ErrCode.LIST_DOCUMENTCATALOG_ERROR, e);
        }
        
    }



}
