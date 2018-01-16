package com.shengsiyuan.imis.util;

/**
 * 传入一个文件名字,然后随机出一个长度为5的字符串，
 * 如果文件名字有扩展名那么就在随机出的名字后面加上扩展名，如果没有那么不加
 * <p>Title: FileUtility</p>
 * <p>Description: </p>
 * <p>Company: 盈丰软件</p> 
 * @author lsw
 * @date 2018年1月3日
 */
public class FileUtility {

    public static String randomNameFile(String fileName) {
        if (-1 == fileName.lastIndexOf(".")) {
            return CharacterUtil.randomString(5);
        }
        return CharacterUtil.randomString(5) + fileName.substring(fileName.lastIndexOf("."));
    }
}
