package com.shengsiyuan.imis.servlet.newsattachment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.service.NewsAttachmentService;
import com.shengsiyuan.imis.service.impl.NewsAttachmentServiceImpl;
import com.shengsiyuan.imis.util.FileUtility;

public class AddNewsAttachment extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        NewsAttachmentService service = new NewsAttachmentServiceImpl();
        
        NewsAttachment bean = new NewsAttachment();
        
        FileItemFactory factory = new DiskFileItemFactory();
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        upload.setHeaderEncoding("utf-8");
        
        try {
            List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
            Iterator<FileItem> ite = list.iterator();
            String parentId = null;
            while (ite.hasNext()) {
                FileItem item = ite.next();
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    if ("parentId".equals(fieldName)) {
                        parentId = item.getString();
                        bean.setParentId(Long.parseLong(parentId));
                    }
                } else {
                    String name = item.getName(); //上传文件的原始名称
                    
                    long time = System.currentTimeMillis();
                    
                    String randomName = time + FileUtility.randomNameFile(name);
                    //补充完数据
                    String realPath = request.getServletContext().getRealPath("/filestorage");
                    
                    File file = new File(realPath, parentId);
                    
                    //如果不存在创建，如果存在那么忽略
                    file.mkdirs();
                    
                    InputStream is = item.getInputStream();
                    FileOutputStream os = new FileOutputStream(new File(file, randomName));
                    
                    byte[] buff = new byte[1024];
                    int len = 0;
                    while ( -1 != (len = is.read(buff))) {
                        os.write(buff, 0, len);
                    }
                    
                    is.close();
                    os.close();
                    
                    //写入到数据库
                    bean.setName(name);
                    bean.setRandomName(randomName);
                    
                    service.addNewsAttachment(bean);
                    
                    //执行反馈
                    out.write("success");
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        
        
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
