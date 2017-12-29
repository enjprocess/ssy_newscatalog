package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.service.impl.NewsCatalogServiceImpl;
import com.shengsiyuan.imis.util.Common;
import com.shengsiyuan.imis.util.ParamUtils;

public class AddNewsCatalog extends HttpServlet {
 
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        NewsCatalogService service = new NewsCatalogServiceImpl();
        
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        
        String name = req.getParameter("name");
        
        long parentId = ParamUtils.getLongParameter(req, "parentId");
        
        try {
            
            boolean newsCatalogExists = service.isNewsCatalogExists(parentId, name);
            
            if (newsCatalogExists) {
                req.setAttribute(Common.SESSION_ERROR, name + "已经存在!");
                req.getRequestDispatcher("../error.jsp").forward(req, resp);
                return;
            }
            
            NewsCatalog bean = new NewsCatalog();
            
            bean.setName(name);
            bean.setParentId(parentId);

            service.addNewsCatalog(bean);
            resp.sendRedirect("ListNewsCatalog?parentId=" + parentId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        
    }

}
