package com.shengsiyuan.imis.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBConnectionFactory {

    //数据库连接池 数据源，连接是从它里获得的
    private DataSource dataSource;
    
    //这个类仅仅只有这么一个对象，该对象中的 dataSource已经被初始化了
    private static DBConnectionFactory instance = new DBConnectionFactory(); 
    
    /**
     * 构建连接对象
     */
    private DBConnectionFactory() {
        //初始化DBConnectionFactory对象中所维护的成员变量dataSource
        setupDataSource();
    }
    
    private void setupDataSource() {
        
        if (dataSource == null) {
            //这里为什么不用dataSource呢，是因为dataSource的话就设置不了url,username,password等等了
            BasicDataSource bds = new BasicDataSource();
            try {
                Properties prop = ConfigHelper.getConfig(DBString.DB_CONFIG_FILE);
                bds.setUrl(prop.getProperty(DBString.DB_URL_PEROPERTY));
                bds.setUsername(prop.getProperty(DBString.DB_USERNAME));
                bds.setPassword(prop.getProperty(DBString.DB_PASSWORD));
                bds.setDriverClassName(prop.getProperty(DBString.DB_DRIVER_PROPERTY));
                
                int initConn = Integer.parseInt(prop.getProperty(DBString.DB_INIT_CONNECTIONS));
                
                int maxConn = Integer.parseInt(prop.getProperty(DBString.DB_MAX_CONNECTIONS));
                
                bds.setInitialSize(initConn);
                
                bds.setMaxActive(maxConn);
                
                dataSource = bds;//多态
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    //这个其实就是单例模式中的一环而已,通过返回的单例对象，来调用对应的方法,这个对象已经被封装好了
    public static DBConnectionFactory getInstance() {
        return instance;
    } 
    
    public Connection getConnection() {
        Connection conn  = null;
        try {
            conn = dataSource.getConnection();
            //设置事务隔离级别,允许重复读，不设置就会用数据库默认的隔离级别
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
        
    }
    
}

