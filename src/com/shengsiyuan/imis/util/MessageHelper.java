package com.shengsiyuan.imis.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 消息辅助类，连接properties文件，通过key返回对应的value值
 * <p>Title: MessageHelper</p>
 * <p>Description: </p>
 * <p>Company: 盈丰软件</p> 
 * @author lsw
 * @date 2017年12月13日
 */
public class MessageHelper {

    public static String getExceptionMessagebyErrCode(int errCode) {
        ResourceBundle rb = ResourceBundle.getBundle(
                "exceptionMessages", Locale.getDefault());
        return rb.getString(Integer.toString(errCode));
    }
}
