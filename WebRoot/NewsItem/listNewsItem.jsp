<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.shengsiyuan.imis.model.*" %>

<%@include file="../tabsidebar.jsp" %>

<%
List<NewsItem> list = (List) request.getAttribute("list");
  
String parentId = (String) request.getAttribute("parentId");
String start = (String) request.getAttribute("start");
String range = (String) request.getAttribute("range");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String title = "新闻分类列表"; %>
<%@ include file="../title.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


    <table width="400" align="center"  >
    
        <tr>
            <td align="left">
            </td>
        </tr>
        
    </table>
    
    
    <table width="90%" align="center" >
    
        <tr>
            <td align="left">
                <a href="addNewsItem.jsp?start=<%=start %>&range=<%= range %>&parentId=<%=parentId %>"><img src="<%=request.getContextPath()%>/Images/btn_add.gif" border="0"/></a>
            
            &nbsp;
            </td>
            
            <td align="right">
                <%= request.getAttribute("pageLink") %>
            </td>
        </tr>
        
    </table>
    
    <table width="90%" align="center" cellpadding="3" cellspacing="1" border="0" class="table" >
        <tr class="tr">
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">名称</td>
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">管理附件</td>
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">更新</td>
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">删除</td>
        </tr>
        <%          
        for (int i = 0; list != null && i < list.size(); i++) {
            NewsItem bean = list.get(i);
        %>
        <tr  class="tr">
            <td align="center"><%=bean.getName() %></td>
            <td align="center">管理附件</td>
            <td align="center"><a href="UpdatePNewsCatalog?start=<%= start %>&range=<%= range %>&parentId=<%=parentId%>&id=<%=bean.getId()%>">更新</a></td>
            <td align="center"><a href="DeleteNewsCatalog?start=<%= start %>&range=<%= range %>&parentId=<%= parentId %>&id=<%= bean.getId() %>" onclick="return del();">删除</a></td>
        </tr>
        <%} %>
    </table>
    
    <%@include file="../footer.jsp" %>
</body>
</html>