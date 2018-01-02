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

public class UpdateNewsCatalog extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NewsCatalogService service = new NewsCatalogServiceImpl();
        
        long id = ParamUtils.getLongParameter(request, "id", -1);
        long oldParentId = ParamUtils.getLongParameter(request, "parentId", -1);
        long newParentId = ParamUtils.getLongParameter(request, "newParentId", -1);
        String name = request.getParameter("name");
        
        long start = ParamUtils.getLongParameter(request, "start", 0);
        long range = ParamUtils.getLongParameter(request, "range", 20);
        
        try {

            NewsCatalog bean = service.getNewsCatalogById(id);
            
            //如果要调入到其上层节点的兄弟节点中，那么要进行判断是否兄弟节点中存在于该节点名字相同的节点
            if (-1 != oldParentId && newParentId != oldParentId) {
                boolean exists = service.isNewsCatalogExists(newParentId, name);
                if (exists) {
                    request.setAttribute(Common.SESSION_ERROR, name + "重名,请重新更换名称!");
                    request.getRequestDispatcher("../error.jsp").forward(request, response);
                    return;
                }
            }
            
            bean.setId(id);
            bean.setName(name);
            bean.setParentId(oldParentId == -1 ? -1 : newParentId);
            service.updateNewsCatalog(bean);
            response.sendRedirect("ListNewsCatalog?start=" + start + "&range=" + range + "&parentId=" + oldParentId);
            
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        
    }

}
