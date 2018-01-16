package com.shengsiyuan.imis.servlet.newsattachment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.service.NewsAttachmentService;
import com.shengsiyuan.imis.service.impl.NewsAttachmentServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class ListNewsAttachment extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long parentId = ParamUtils.getLongParameter(request, "parentId", -1);
        
        NewsAttachmentService service = new NewsAttachmentServiceImpl();
        try {
            List<NewsAttachment> list = service.listNewsAttachmentByParentId(parentId);
            
            request.setAttribute("parentId", String.valueOf(parentId));
            request.setAttribute("list", list);
            
            request.getRequestDispatcher("listNewsAttachment.jsp").forward(request, response);
            
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
