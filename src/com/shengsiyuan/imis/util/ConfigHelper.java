package com.shengsiyuan.imis.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigHelper {

    //维护着一个map用来作为缓存，这样的好处就是同一个properties文件不会被加载多次
    public static Map<String, Properties> configCache = new HashMap<String, Properties>();
    
    /**
     * 如果不存在自定义加载器且自定义加载器没有重写findResource方法的话,那ClassLoader中的path不能以/开头，否则会返回null,
     * 该方法其实本质就是通过各种ClassLoader去加载资源，这里感觉都是系统类加载器啊。
     */
    public static URL findAsResource(String path) {
        
        URL resourceURL = null;
        
        resourceURL = Thread.currentThread().getContextClassLoader().getResource(path);
        
        if (resourceURL != null) {
            return resourceURL;
        }
        
        resourceURL = ConfigHelper.class.getClassLoader().getResource(path);
        
        if (resourceURL != null) {
            return resourceURL;
        }
        
        resourceURL = ClassLoader.getSystemClassLoader().getResource(path);
        
        return resourceURL;
        
    }
    
    public static Properties getConfig(String confName) throws IOException {
        
        //如果发现维护的map中没有这个资源的Properties对象的话，那么就先创建Properties对象，并且去加载资源，加载完后，把该对象设置进configCache数据结构中，
        //并且返回它，如果已经存在那么直接就返回
        if (configCache.get(confName) == null) {
            Properties prop = new Properties();
            InputStream is = findAsResource(confName).openStream();
            prop.load(is);
            is.close();
            configCache.put(confName, prop);
        }
        
        //执行到这一步说明prop一定存在于map结构中了
        return configCache.get(confName);
    }
}
