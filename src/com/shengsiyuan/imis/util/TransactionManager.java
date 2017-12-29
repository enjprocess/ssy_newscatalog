package com.shengsiyuan.imis.util;

/**
 * 完全调用TransactionManager中的相应方法，并且它真正的接受数据库的连接,就是说从数据源中获取到连接之后给它,
 * TransactionManager是service层来用的,由它来开启事务，
 * 
 * TransactionManager中的方法设置为静态方法好像也可以，不设置的话，就对service进一步包装
 * <p>Title: TransactionManager</p>
 * <p>Description: </p>
 * <p>Company: 盈丰软件</p> 
 * @author lsw
 * @date 2017年12月18日
 */
public class TransactionManager {

    
    public TransactionContext beginTransaction() {
        return new TransactionContext(DBConnectionFactory.getInstance().getConnection());
    }
    
    public void commitTransaction(TransactionContext tc) {
        tc.commit();
    }
    
    public void rollbackTransaction(TransactionContext tc) {
        tc.rollback();
    }
    
}
