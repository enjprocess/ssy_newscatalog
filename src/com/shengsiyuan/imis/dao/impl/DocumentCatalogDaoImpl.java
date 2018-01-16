package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<DocumentCatalog> listDocumentCatalogByParentId(long parentId)
            throws DaoException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ")
                .append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append(" where parentId = ?");
        
        PreparedStatement ps;
        try {
            List<DocumentCatalog> list = new ArrayList<DocumentCatalog>();
            ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                DocumentCatalog bean = new DocumentCatalog();
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String state = rs.getString("state");
                long parentIdDB = rs.getLong("parentId");
                
                bean.setId(id);
                bean.setName(name);
                bean.setState(state);
                bean.setParentId(parentIdDB);
                
                list.add(bean);
            }
            
            return list;
            
            
        } catch (SQLException e) {
            throw new DaoException(ErrCode.LIST_DOCUMENTCATALOG_ERROR, e);
        }
        
    }

}
