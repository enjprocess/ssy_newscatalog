package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsCatalogDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class NewsCatalogDaoImpl extends AbstractBaseDao implements
        NewsCatalogDao {

    public NewsCatalogDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void addNewsCatalog(NewsCatalog bean) throws DaoException {

        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append("(name, parentId) values(?, ?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setString(1, bean.getName());
            ps.setLong(2, bean.getParentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
    }

    @Override
    public List<NewsCatalog> listNewsCatalog() throws DaoException {

        List<NewsCatalog> list = new ArrayList<NewsCatalog>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id, name, parentId from ").append(
                DaoConstants.NEWSCATALOG_TABLE_NAME);
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ResultSet rs = ps.executeQuery(sb.toString());
            while (rs.next()) {
                NewsCatalog bean = new NewsCatalog();
                bean.setId(rs.getLong("id"));
                bean.setName(rs.getString("name"));
                bean.setParentId(rs.getLong("parentId"));
                list.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
        return list;

    }

    @Override
    public List<NewsCatalog> listNewsCatalog(long parentId, long start,
            long range) throws DaoException {

        List<NewsCatalog> list = new ArrayList<NewsCatalog>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id, name, parentId from ")
                .append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where parentId = ? order by id desc limit ?, ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            ps.setLong(2, start);
            ps.setLong(3, range);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsCatalog bean = new NewsCatalog();
                bean.setId(rs.getLong("id"));
                bean.setName(rs.getString("name"));
                bean.setParentId(rs.getLong("parentId"));
                list.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
        return list;
    }

    @Override
    public long getNewCatalogCountByParentIdAndName(long parentId, String name)
            throws DaoException {

        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) COUNT_ from ")
                .append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where parentId = ? and name = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            ps.setString(2, name);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong("COUNT_");
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
        return 0;
    }

    @Override
    public NewsCatalog getNewsCatalogById(long id) throws DaoException {

        StringBuilder sb = new StringBuilder();
        sb.append("select id, name, parentId from ")
                .append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where id = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsCatalog newsCatalog = new NewsCatalog();
                newsCatalog.setId(rs.getLong("id"));
                newsCatalog.setName(rs.getString("name"));
                newsCatalog.setParentId(rs.getLong("parentId"));
                return newsCatalog;
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
        return null;
    }

    @Override
    public List<NewsCatalog> listNewsCatalogByParentId(long parentId)
            throws DaoException {
        List<NewsCatalog> list = new ArrayList<NewsCatalog>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id, name, parentId from ")
                .append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where parentId = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsCatalog bean = new NewsCatalog();
                bean.setId(rs.getLong("id"));
                bean.setName(rs.getString("name"));
                bean.setParentId(rs.getLong("parentId"));
                list.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
        return list;
    }

    @Override
    public void updateNewsCatalog(NewsCatalog bean) throws DaoException {

        StringBuilder sb = new StringBuilder();
        sb.append("update ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" set parentId = ?, name = ? where id = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, bean.getParentId());
            ps.setString(2, bean.getName());
            ps.setLong(3, bean.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
    }

    @Override
    public long getNewsCatalogByParentId(long parentId) throws DaoException {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) count_ from ")
                .append(DaoConstants.NEWSCATALOG_TABLE_NAME)
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
    public void deleteNewsCatalogById(long id) throws DaoException {
        
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ")
                .append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where id = ?");
        try {
            
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_ERROR, e);
        }
    }
}
