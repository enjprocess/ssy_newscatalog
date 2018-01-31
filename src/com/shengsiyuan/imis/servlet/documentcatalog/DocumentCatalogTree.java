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
import com.shengsiyuan.imis.model.DataAttribute;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.model.TreeDocumentCatalog;
import com.shengsiyuan.imis.service.DocumentCatalogService;
import com.shengsiyuan.imis.service.impl.DocumentCatalogServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class DocumentCatalogTree extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            
            DocumentCatalogService service = new DocumentCatalogServiceImpl();
            
            long type = ParamUtils.getLongParameter(req, "type");
            long parentId = ParamUtils.getLongParameter(req, "parentId");
            
            List<DocumentCatalog> list = service.listDocumentCatalogByTypeAndParentId(type, parentId);
            
            TreeDocumentCatalog root = null;
            
            //首次加载
            if (-1 == parentId) {
                
                root = new TreeDocumentCatalog();
                String rootName = null;
                
                switch((int) type) {
                    case 1:
                        rootName = "公司文档";
                        break;
                    case 2:
                        rootName = "部门文档";
                        break;
                    case 3:
                        rootName = "协作文档";
                        break;
                    case 4:
                        rootName = "共享文档";
                        break;
                    case 5:
                        rootName = "培训文档";
                        break;
                }
                
                DataAttribute rootDataAttribute = new DataAttribute();
                rootDataAttribute.setTitle(rootName);
                
                Attributes rootAttributes = new Attributes();
                rootAttributes.setId("node" + -1);
                
                rootDataAttribute.setAttributes(rootAttributes);
                root.setData(rootDataAttribute);
                
            }
            
            
            //获取到子层次列表的信息
            List<TreeDocumentCatalog> resultList = new ArrayList<TreeDocumentCatalog>();
            
            TreeDocumentCatalog treeDocumentCatalog = null;
            
            for (DocumentCatalog documentCatalog : list) {
                
                treeDocumentCatalog = new TreeDocumentCatalog();
                
                long id = documentCatalog.getId();
                String name = documentCatalog.getName();
                boolean isLeaf = documentCatalog.isLeaf();
                
                Attributes attributes = new Attributes();
                attributes.setId("node" + id);
                
                DataAttribute dataAttribute = new DataAttribute();
                dataAttribute.setTitle(name);
                dataAttribute.setAttributes(attributes);
                
                treeDocumentCatalog.setData(dataAttribute);
                
                if (isLeaf) {
                    treeDocumentCatalog.setState("leaf");
                } else {
                    treeDocumentCatalog.setState("closed");
                }
                
                resultList.add(treeDocumentCatalog);
                
                
            }
            
            Gson gson = new Gson();
            String result = null;
            if (-1 == parentId) {
                root.setChildren(resultList);
                
                if (0 == resultList.size()) {
                    root.setState("leaf");
                } else {
                    root.setState("open");
                }
                 
                result = gson.toJson(root);
                
                
            } else {
                result = gson.toJson(resultList);
            }
            
            resp.setContentType("application/json;charset=utf-8");
            resp.setHeader("Cache-Control", "no-cache");
            
            PrintWriter out = resp.getWriter();
            out.println(result);
            out.close();
            
            
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

}
