package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsAttachmentDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class NewsAttachmentDaoImpl extends AbstractBaseDao implements
        NewsAttachmentDao {

    public NewsAttachmentDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<NewsAttachment> listNewsAttachmentByParentId(long parentId)
            throws DaoException {
        
        List<NewsAttachment> list = new ArrayList<NewsAttachment>();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ")
                .append(DaoConstants.NEWSATTACHMENT_TABLE_NAME)
                .append(" where parentId = ? order by id desc");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsAttachment bean = new NewsAttachment();
                bean.setId(rs.getLong("id"));
                bean.setName(rs.getString("name"));
                bean.setRandomName(rs.getString("randomName"));
                bean.setParentId(rs.getLong("parentId"));
                list.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
        return list;
    }

    @Override
    public void addNewsAttachment(NewsAttachment bean) throws DaoException {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ")
                .append(DaoConstants.NEWSATTACHMENT_TABLE_NAME)
                .append("(name, randomName, parentId) values(?, ?, ?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getRandomName());
            ps.setLong(3, bean.getParentId());
           
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
    }
    
}
