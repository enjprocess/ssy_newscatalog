package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.service.impl.NewsCatalogServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class ListNewsCatalog extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        
        long parentId = ParamUtils.getLongParameter(req, "parentId", -1);
        long start = ParamUtils.getLongParameter(req, "start", 0);
        long range = ParamUtils.getLongParameter(req, "range", 20);
        //上一层的ID
        long parentOfParentId = ParamUtils.getLongParameter(req, "parentOfParentId", -1);

        NewsCatalogService service = new NewsCatalogServiceImpl();
        
        try {
            List<NewsCatalog> list = service.listNewsCatalog(parentId, start, range);
            req.setAttribute("list", list);
            req.setAttribute("parentId", String.valueOf(parentId));
            req.setAttribute("start", String.valueOf(start));
            req.setAttribute("range", String.valueOf(range));
            req.setAttribute("parentOfParentId", String.valueOf(parentOfParentId));
            
            req.getRequestDispatcher("listNewsCatalog.jsp").forward(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        
        out.close();
    }

}
