package com.shengsiyuan.imis.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用的分页方法
 * <p>Title: Page</p>
 * <p>Description: </p>
 * <p>Company: 盈丰软件</p> 
 * @author lsw
 * @date 2017年12月29日
 */
public class Page {

    public static String getPage(HttpServletRequest request, String appendString, long start, long range, long count) {
        
        String requestURI = request.getRequestURI();
        
        //会返回形如ListNewsCatalog这样的字符串
        String index = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        //总页数
        long numPages = count / range + (0 == count % range ? 0 : 1);
        
        if (numPages <= 1) {
            return "";
        } 
        
        //拼接字符串
        StringBuffer sb = new StringBuffer();
        
        sb.append("[");
        
        //当不是第一页的时候 需要显示"<" 即上一页
        if (start > 0) {
            sb.append("<a href=\"").append(index).append("?");
            if ("".equals(appendString)) {
                sb.append("start=");
            } else {
                sb.append(appendString);
                sb.append("&start=");
            }
            
            sb.append(start - range);
            sb.append("&range=");
            sb.append(range);
            sb.append("\">");
            
            sb.append("<img src=\"../Images/prev.gif\" width=\"10\" height=\"10\" border=\"0\" />");
            
            sb.append("</a>");
        }

        //判断当前是哪一页,先确定当前页是为了确定是否需要输出...
        
        long currentPage = start / range + 1;

        long low = currentPage - 5;
        
        long high = currentPage + 5;
        
        //如果减了之后发现没有空间，那么从1开始循环
        if (low <= 0) {
            low = 1;
        }
        
        //如果减5之后还大于2那么需要打印1和...
        if (low >= 2) {
            sb.append("<a href=\"").append(index).append("?");
            if ("".equals(appendString)) {
                sb.append("start=");
            } else {
                sb.append(appendString);
                sb.append("&start=");
            }
            
            sb.append(0);
            sb.append("&range=");
            sb.append(range);
            sb.append("\">");
            
            sb.append("1");
            
            sb.append("</a>&nbsp;");
            if (low > 2) {
                sb.append("...");
            }
        }
        
        while (low < currentPage) {
            
            sb.append("<a href=\"").append(index).append("?");
            if ("".equals(appendString)) {
                sb.append("start=");
            } else {
                sb.append(appendString);
                sb.append("&start=");
            }
            
            sb.append((low - 1) * range);
            sb.append("&range=");
            sb.append(range);
            sb.append("\">");
            
            sb.append(low);
            
            sb.append("</a>");
            sb.append("&nbsp;");
            
            low++;
        }
        
        //处理当前页 加粗就行
        
        //low已经成为当前页了,不管是否进入上述while循环，进入是不进入说明本来就是
        sb.append("<B>");
        sb.append(low);
        sb.append("</B>");
        
        currentPage++;
        
        //打印后面的
        //在high与numPages取最小值
        while (currentPage <= high && currentPage <= numPages) {
            
            sb.append("&nbsp;<a href=\"").append(index).append("?");
            if ("".equals(appendString)) {
                sb.append("start=");
            } else {
                sb.append(appendString);
                sb.append("&start=");
            }
            
            sb.append((currentPage - 1) * range);
            sb.append("&range=");
            sb.append(range);
            sb.append("\">");
            
            sb.append(currentPage);
            
            sb.append("</a>");
            
            currentPage++;
        }
        
        //当前页 + 5 小于 numPages的情况
        //什么时候需要打印...呢
        //一种是刚好多一个，还有一种是多很多个
        if (high + 1 < numPages) {
            sb.append("...");
        }
        
        if (high + 1 <= numPages) {
            sb.append("&nbsp;<a href=\"").append(index).append("?");
            if ("".equals(appendString)) {
                sb.append("start=");
            } else {
                sb.append(appendString);
                sb.append("&start=");
            }
            
            sb.append((numPages - 1) * range);
            sb.append("&range=");
            sb.append(range);
            sb.append("\">");
            
            sb.append(numPages);
            
            sb.append("</a>");
            
        }
        
        //如果不是最后一页显示">" 即下一页
        
        if (count > (start + range)) {
            
            sb.append("&nbsp;<a href=\"").append(index).append("?");
            if ("".equals(appendString)) {
                sb.append("start=");
            } else {
                sb.append(appendString);
                sb.append("&start=");
            }
            
            sb.append(start + range);
            sb.append("&range=");
            sb.append(range);
            sb.append("\">");
            
            sb.append("<img src=\"../Images/next.gif\" width=\"10\" height=\"10\" />");
            
            sb.append("</a>");
            
            
        }
        
        sb.append("]");
        
        return sb.toString();
    }
}
