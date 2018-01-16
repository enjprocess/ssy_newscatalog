package com.shengsiyuan.imis.servlet.newsitem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.model.NewsItemAndAttachment;
import com.shengsiyuan.imis.service.NewsAttachmentService;
import com.shengsiyuan.imis.service.NewsItemService;
import com.shengsiyuan.imis.service.impl.NewsAttachmentServiceImpl;
import com.shengsiyuan.imis.service.impl.NewsItemServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class ViewNewsItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long id = ParamUtils.getLongParameter(request, "id");
        
        NewsItemService newsItemService = new NewsItemServiceImpl();
        
        NewsAttachmentService newsAttachmentService = new NewsAttachmentServiceImpl();
        
        try {
            NewsItem newsItem = newsItemService.getNewsItemById(id);
            
            List<NewsAttachment> list = newsAttachmentService.listNewsAttachmentByParentId(id);
            
            NewsItemAndAttachment bean = new NewsItemAndAttachment();
            bean.setNewsId(id);
            bean.setNewsName(newsItem.getName());
            bean.setNewsContent(newsItem.getContent());
            bean.setList(list);
            
            Gson gson = new Gson();
            String json = gson.toJson(bean);
            response.setContentType("application/json; charset=utf-8");
            out.write(json);
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
