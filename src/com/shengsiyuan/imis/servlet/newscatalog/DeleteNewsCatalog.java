package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.service.impl.NewsCatalogServiceImpl;
import com.shengsiyuan.imis.util.Common;
import com.shengsiyuan.imis.util.ParamUtils;

public class DeleteNewsCatalog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //如果没有子节点那么是允许删除的，否则不允许，涉及到一个查询和一个删除，前后强相关的，所以应该放到一个事务中
        
        NewsCatalogService service = new NewsCatalogServiceImpl();
        
        long id = ParamUtils.getLongParameter(request, "id", -1);
        long parentId = ParamUtils.getLongParameter(request, "parentId", -1);

        long start = ParamUtils.getLongParameter(request, "start", 0);
        long range = ParamUtils.getLongParameter(request, "range", 20);
        
        try {
            
            boolean delSuccess = service.deleteNewsCatalog(id);
            
            if (!delSuccess) {
                request.setAttribute(Common.SESSION_ERROR, "有子栏目不能删除!");
                
                request.getRequestDispatcher("../error.jsp").forward(request, response);
                
                return ;
            }
            
            response.sendRedirect("ListNewsCatalog?start=" + start + "&range=" + range + "&parentId=" + parentId);
            
            
        } catch (ServiceException se) {
            se.printStackTrace();
        }
    }

}
