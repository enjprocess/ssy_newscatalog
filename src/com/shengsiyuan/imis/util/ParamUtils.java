package com.shengsiyuan.imis.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理用户异常输入的
 * <p>Title: ParamUtils</p>
 * <p>Description: </p>
 * <p>Company: 盈丰软件</p> 
 * @author lsw
 * @date 2017年12月23日
 */
public class ParamUtils {

    /**
     * 取得前台参数字符串所对应的Long型
     * HttpServletRequest req 至于为什么这里的参数直接用HttpServletRequest呢，因为这样上层的代码会少点呀~
     * return：当是null值或是非法值那么返回默认值,否则返回正常的解析结果
     */
    public static long getLongParameter(HttpServletRequest req, String name, long defaultValue) {
        String value = req.getParameter(name);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * 方法从写，默认值为0，经常用到
     */
    public static long getLongParameter(HttpServletRequest req, String name) {
        return getLongParameter(req, name, Long.valueOf(0));
    }
    
}
