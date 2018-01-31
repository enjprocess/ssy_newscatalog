package com.shengsiyuan.imis.servlet.documentcatalog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ErrCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.service.DocumentCatalogService;
import com.shengsiyuan.imis.service.impl.DocumentCatalogServiceImpl;
import com.shengsiyuan.imis.util.MessageHelper;
import com.shengsiyuan.imis.util.ParamUtils;

public class UpdateDocumentCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        DocumentCatalogService service = new DocumentCatalogServiceImpl();

        String name = request.getParameter("title");
        long id = ParamUtils.getLongParameter(request, "id");
        long type = ParamUtils.getLongParameter(request, "type");

        try {
            DocumentCatalog bean = service.getDocumentCatalogById(id);
            bean.setName(name);
            service.updateDocumenCatalog(bean);
        } catch (Exception e) {
            throw new ServletException(
                    MessageHelper
                            .getExceptionMessagebyErrCode(ErrCode.UPDATE_DOCUMENTCATALOG_ERROR));
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
