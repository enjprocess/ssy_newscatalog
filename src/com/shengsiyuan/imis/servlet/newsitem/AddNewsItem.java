package com.shengsiyuan.imis.servlet.newsitem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.service.NewsItemService;
import com.shengsiyuan.imis.service.impl.NewsCatalogServiceImpl;
import com.shengsiyuan.imis.service.impl.NewsItemServiceImpl;
import com.shengsiyuan.imis.util.Common;
import com.shengsiyuan.imis.util.ParamUtils;

public class AddNewsItem extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        NewsItemService service = new NewsItemServiceImpl();
        
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        
        String name = req.getParameter("name");
        String content = req.getParameter("content");
        
        long parentId = ParamUtils.getLongParameter(req, "parentId");
        
        long start = ParamUtils.getLongParameter(req, "start", 0);
        long range = ParamUtils.getLongParameter(req, "range", 20);
        
        try {

            NewsItem bean = new NewsItem();
            
            bean.setName(name);
            bean.setParentId(parentId);
            bean.setContent(content);
            service.addNewsItem(bean);
            
            resp.sendRedirect("ListNewsItem?start=" + start + "&range=" + range + "&parentId=" + parentId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        
        
    }

}
