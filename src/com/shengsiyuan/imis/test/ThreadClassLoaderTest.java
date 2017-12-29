package com.shengsiyuan.imis.test;

import java.net.URL;

/**
 * 主要来熟悉下Thread.currentThread().getContextClassLoader().getResource(String
 * path)的使用
 * <p>
 * Title: ThreadClassLoaderTest
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: 盈丰软件
 * </p>
 * 
 * @author lsw
 * @date 2017年12月13日
 */
public class ThreadClassLoaderTest {

    public static void main(String[] args) {

//        ClassLoder是java.lang包下的，说明是根类加载器来加载的,那么根类加载器所加载的类及其子类加载器所加载的类中是可见的
//        ClassLoader cls = ClassLoader.class.getClassLoader();
//        System.out.println(cls);
//        
//         ClassLoader cl = Thread.currentThread().getContextClassLoader();
//         ClassLoader cl2 = ThreadClassLoaderTest.class.getClassLoader();
//         System.out.println(cl);
//         System.out.println(cl2);
//         URL resource = cl2.getResource("/");
//         System.out.println(resource);
//        
//         System.out.println(Thread.currentThread());
//
//        URL resource = ThreadClassLoaderTest.class.getResource("");
//        URL resource2 = ThreadClassLoaderTest.class.getResource("/");
//
//        System.out.println(resource);
//        System.out.println(resource2);
        
        URL r1 = ThreadClassLoaderTest.class.getResource("/");
//        URL r2 = ThreadClassLoaderTest.class.getResource("/");
        System.out.println(r1);
//        System.out.println(r2);
        
//        System.out.println(r1);
        URL path = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println(path);
    }

}
