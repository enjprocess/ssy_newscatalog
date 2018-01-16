package com.shengsiyuan.imis.servlet.documentcatalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.Attributes;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.model.DocumentCatalogTreeModel;
import com.shengsiyuan.imis.service.DocumentCatalogService;
import com.shengsiyuan.imis.service.impl.DocumentCatalogServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class DocumentCatalogTree extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        DocumentCatalogService service = new DocumentCatalogServiceImpl();
        long parentId = ParamUtils.getLongParameter(request, "parentId", -1);

        try {
            List<DocumentCatalog> list = service
                    .listDocumentCatalogByParentId(parentId);

            Iterator<DocumentCatalog> iterator = list.iterator();
            List<DocumentCatalogTreeModel> treeList = new ArrayList<DocumentCatalogTreeModel>();
            
            List<DocumentCatalogTreeModel> createTreeList = createTreeList(list, treeList);
            
            if (-1 == parentId) {
                //补充children
                List<DocumentCatalog> subList = service.listDocumentCatalogByParentId(list.get(0).getId());
                List<DocumentCatalogTreeModel> childrenList = new ArrayList<DocumentCatalogTreeModel>();
                List<DocumentCatalogTreeModel> children = createTreeList(subList, childrenList);
                createTreeList.get(0).setChildren(children);
            }
            

            Gson gson = new Gson();
            String json = gson.toJson(treeList);
            out.write(json);
            out.close();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    public List<DocumentCatalogTreeModel> createTreeList(List<DocumentCatalog> sourceList, List<DocumentCatalogTreeModel> treeList) {
        Iterator<DocumentCatalog> iterator = sourceList.iterator();
        while (iterator.hasNext()) {
            DocumentCatalogTreeModel bean = new DocumentCatalogTreeModel();
            DocumentCatalog documentCatalog = iterator.next();
            bean.setData(documentCatalog.getName());
            String state = documentCatalog.getState();

            if (-1 == documentCatalog.getParentId()) {
                bean.setState("open");
            }else if ("closed".equals(state)) {
                bean.setState(documentCatalog.getState());
            }
            
            Attributes attr = new Attributes();
            attr.setId(documentCatalog.getId());
            bean.setAttributes(attr);
            treeList.add(bean);
        }
        return treeList;
    }

}
