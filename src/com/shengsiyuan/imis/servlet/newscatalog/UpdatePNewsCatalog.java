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

public class UpdatePNewsCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NewsCatalogService service = new NewsCatalogServiceImpl();
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long parentId = ParamUtils.getLongParameter(request, "parentId");
        long id = ParamUtils.getLongParameter(request, "id");
        
        try {
            
            //有可能是null,比如说有人把该记录删除了
            NewsCatalog bean = service.getNewsCatalogById(id);
            
            request.setAttribute("bean", bean);

            //是根级别的就不需要栏目下拉
            if (-1 != parentId) {
                List<NewsCatalog> list = service.listAllParentLevelNewsCatalog(parentId);
                request.setAttribute("list", list);
            }
            
            request.getRequestDispatcher("updateNewsCatalog.jsp").forward(request, response);
            
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
