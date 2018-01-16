<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.shengsiyuan.imis.model.*" %>

<%@include file="../tabsidebar.jsp" %>

<%
List<NewsAttachment> list = (List) request.getAttribute("list");
  
String parentId = (String) request.getAttribute("parentId");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String title = "新闻附件列表"; %>
<%@ include file="../title.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="../lib/jquery.js"></script>
<script type="text/javascript" src="../uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="../uploadify/uploadify.css" />

<script type="text/javascript">

$(document).ready(function() {
	$('#file_upload').uploadify({
        'swf'      : '../uploadify/uploadify.swf',
        'uploader' : 'AddNewsAttachment',
        'auto'     : false,
        'formData' : {'parentId' : '<%= parentId%>'},
        'onQueueComplete' : function(queueData) {
            //alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');
            location.href = location.href;
        }
        // Your options here
    });
})

</script>

</head>
<body>


    <table width="400" align="center"  >
    
        <tr>
            <td align="left">
            </td>
        </tr>
        
    </table>
    
    <table width="60%" align="center" cellpadding="3" cellspacing="1" border="0" class="table" >
        <tr class="tr">
            <td align="left" bgcolor="eoeoeo" nowrap="nowrap">名称</td>
        </tr>
        <%          
        for (int i = 0; list != null && i < list.size(); i++) {
            NewsAttachment bean = list.get(i);
        %>
        <tr  class="tr">
            <td align="left"><%=bean.getName() %></td>
       </tr>
        <%} %>
    </table>
    <br/>
    <table width="30%" align="center" cellpadding="3" cellspacing="1" border="0" class="table" >
        <tr class="tr">
            <td align="left" bgcolor="eoeoeo" nowrap="nowrap">
                <input id="file_upload" type="file" />
            </td>
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap" onclick="javascript:$('#file_upload').uploadify('upload', '*');">提交</td>
        </tr>
    </table>
    
    <%@include file="../footer.jsp" %>
    

</body>
</html>