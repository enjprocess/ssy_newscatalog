package com.shengsiyuan.imis.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.shengsiyuan.imis.exception.ConnectionException;

/**
 * 事务环境上下文,因为事务是一定需要连接对象的，所以事务环境上下文中维护着连接对象
 * <p>Title: TransactionContext</p>
 * <p>Description: </p>
 * <p>Company: 盈丰软件</p> 
 * @author lsw
 * @date 2017年12月18日
 */
public class TransactionContext {

    private Connection conn;
    
    public TransactionContext(Connection conn) {
        this.conn = conn;
        
        if (null == this.conn) {
            throw new ConnectionException("无法获得数据库连接。原因：数据源返回null");
        }
        
        try {
            if (this.conn.getAutoCommit()) {
                this.conn.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ConnectionException("设置JDBC事务出错，原因：" + ex);
            
        }
        
    }

    public void commit() {
        try {
            //上述的conn出问题可以抛出连接异常，但是conn.commit不一定是连接问题,也可能是提交的数据不符合规则
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ConnectionException("关闭连接出错，原因：" + e);
            }
        }
        
    }
    
    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ConnectionException("关闭连接出错，原因：" + e);
            }
        }
    }
    
    public Connection getConn() {
        return conn;
    }
    
}
