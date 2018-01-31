<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.shengsiyuan.imis.model.*" %>

<%@include file="../tabsidebar.jsp" %>
<%
    String parentId = request.getParameter("parentId");
    //String start = (String) request.getParameter("start"); 添加的内容总要让用户知道的么，所以总是跳转到第一页
    String range = (String) request.getParameter("range");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% String title = "新闻条目列表"; %>
<%@ include file="../title.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../lib/jquery.js"></script>
<link type="text/css"  rel="stylesheet" href="../lib/jwysiwyg/jquery.wysiwyg.css" />
<script type="text/javascript" src="../lib/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript">

$(function() {
	$("#content").wysiwyg();
})

</script>

</head>
<body>

    <form action="AddNewsItem" method="post">
        
        <input type="hidden" name="parentId" value="<%=parentId %>" />
        <input type="hidden" name="range" value="<%=range %>" />
        
        <table border="0" width="400" cellpadding="3" cellspacing="1" align="center" class="table">
	        <tr bgcolor="#f0f0f0">
	            <td nowrap="nowrap">名称：</td>
	            <td><input type="text" name="name" style="width: 400px"></td>
	        </tr>
	        
	        <tr bgcolor="#f0f0f0">
                <td nowrap="nowrap">内容：</td>
                <td>
                    <textarea id="content" name="content" style="width:600px; height:300px;"></textarea>
                  </td>
            </tr>
	        
	        <tr class="tr">
	            <td align="center" colspan="2">
		            <input type="submit" value="提交">&nbsp;&nbsp;
	                <input type="button" value="返回"  onclick="javascript:history.back();">
	            </td>
	        </tr>
        </table>
    </form>    

<%@include file="../footer.jsp" %>
</body>
</html>