package com.shengsiyuan.imis.servlet.documentcatalog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.service.DocumentCatalogService;
import com.shengsiyuan.imis.service.impl.DocumentCatalogServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class AddDocumentCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        DocumentCatalogService service = new DocumentCatalogServiceImpl();
        
        String name = request.getParameter("title");
        long parentId = ParamUtils.getLongParameter(request, "parentId");
        long type = ParamUtils.getLongParameter(request, "type");
        
        DocumentCatalog documentCatalog = new DocumentCatalog();
        documentCatalog.setType(type);
        documentCatalog.setParentId(parentId);
        documentCatalog.setName(name);
        documentCatalog.setLeaf(true);
        
        try {
            
            //需要更改的父节点的范围
            if (-1 != documentCatalog.getParentId()) {
                DocumentCatalog bean = service.getDocumentCatalogById(documentCatalog.getParentId());
                bean.setLeaf(false);
                service.updateDocumenCatalog(bean);
            }
             
            long id = service.addDocumentCatalog(documentCatalog);
            response.setContentType("application/json;charset=utf-8");
            out.print(id);
            out.close();
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
