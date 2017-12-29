package com.shengsiyuan.imis.util;

/**
 * 虽然有一个成员变量，但是因为它的成员变量所引用的对象只有方法，所以这个类还是无状态类，在servlet单实例下不会有影响
 * 这样所有的service模块中的方法不用每次都去new一个TransactionManager对象了，而是直接使用
 * <p>Title: AbstractBaseService</p>
 * <p>Description: </p>
 * <p>Company: 盈丰软件</p> 
 * @author lsw
 * @date 2017年12月19日
 */
public class AbstractBaseService {

    protected TransactionManager transManager = new TransactionManager();
}
