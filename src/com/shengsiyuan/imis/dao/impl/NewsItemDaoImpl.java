package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsItemDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class NewsItemDaoImpl extends AbstractBaseDao implements NewsItemDao {

    public NewsItemDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<NewsItem> listNewsItem(long start, long range, long parentId)
            throws DaoException {

        List<NewsItem> list = new ArrayList<NewsItem>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id, name, content, parentId from ")
                .append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append(" where parentId = ? order by id desc limit ?, ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            ps.setLong(2, start);
            ps.setLong(3, range);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsItem bean = new NewsItem();
                bean.setId(rs.getLong("id"));
                bean.setName(rs.getString("name"));
                bean.setContent(rs.getString("content"));
                bean.setParentId(rs.getLong("parentId"));
                list.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
        return list;
    }

    @Override
    public long getNewsItemByParentId(long parentId) throws DaoException {
        
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) count_ from ")
                .append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append(" where parentId = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               long count = rs.getLong("count_");
               return count;
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
        return 0; //不可到达,为了方法通过
    }

    @Override
    public void addNewsItem(NewsItem bean) throws DaoException {
    
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append("(name, content, parentId) values(?, ?, ?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getContent());
            ps.setLong(3, bean.getParentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
    
    }

}
