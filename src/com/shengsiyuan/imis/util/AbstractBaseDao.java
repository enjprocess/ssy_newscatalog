package com.shengsiyuan.imis.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrCode;

/**
 * 被所有模块的Dao所继承，这样所有的Dao不需要去定义连接了，直接可以使用父类的连接
 * 抽取了各个模块的Dao都会用到的共有方法或属性
 * <p>Title: AbstractBaseDao</p>
 * <p>Description: </p>
 * <p>Company: 盈丰软件</p> 
 * @author lsw
 * @date 2017年12月13日
 */
public class AbstractBaseDao implements Dao {

    protected Connection conn;
    
    /**
     * 这个连接也不是该类自己创造出来的，而是有专门的类创造并传递给它的
     * @param conn
     */
    public AbstractBaseDao(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public Connection getConnection() {
        return conn;
    }
    
    protected void closeResources(Statement stmt) throws DaoException {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            throw new DaoException(ErrCode.SQL_STMT_CLOSE_ERROR);
        }
    }

}
