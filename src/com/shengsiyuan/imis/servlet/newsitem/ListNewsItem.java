package com.shengsiyuan.imis.servlet.newsitem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.shengsiyuan.imis.util.Page;
import com.shengsiyuan.imis.util.ParamUtils;

public class ListNewsItem extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long parentId = ParamUtils.getLongParameter(req, "parentId", -1);
        long start = ParamUtils.getLongParameter(req, "start", 0);
        long range = ParamUtils.getLongParameter(req, "range", 20);
        
        NewsItemService service = new NewsItemServiceImpl();
        
        try {
            //这里是一个service，这个事务处理完，有可能其它用户增加了数据，那么这个count并不实时的，也正常
            long count = service.getNewsItemByParentId(parentId);
            
            String pageLink = Page.getPage(req, "parentId=" + parentId, start, range, count);
            System.out.println(pageLink);
            
            List<NewsItem> list = service.listNewsItem(start, range, parentId);
            
            req.setAttribute("list", list);
            req.setAttribute("parentId", String.valueOf(parentId));
            req.setAttribute("start", String.valueOf(start));
            req.setAttribute("range", String.valueOf(range));
            
            req.setAttribute("pageLink", pageLink);
            req.getRequestDispatcher("listNewsItem.jsp").forward(req, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        
        out.close();
    }

}
