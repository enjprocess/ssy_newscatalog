package com.shengsiyuan.imis.servlet.newsattachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.util.ParamUtils;

public class DownloadNewsAttachment extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //PrintWriter out = response.getWriter();

        long parentId = ParamUtils.getLongParameter(request, "parentId");
        String name = request.getParameter("name");
        String randomName = request.getParameter("randomName");
        
        response.setHeader("Content-Disposition", "attachment;filename=\"" + name + "\"");
        String path = request.getServletContext().getRealPath("/filestorage/" + parentId + "/" + randomName);
        
        InputStream is = new FileInputStream(path);
        
        byte[] buff = new byte[1024];
        
        int len = 0;
        
        OutputStream os = response.getOutputStream();
        
        while (-1 != (len = is.read(buff))) {
            os.write(buff, 0, len);
        }
        
        is.close();
        os.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
