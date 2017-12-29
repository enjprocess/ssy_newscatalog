<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.shengsiyuan.imis.model.*" %>

<%@include file="../tabsidebar.jsp" %>

<%
List<NewsCatalog> list = (List) request.getAttribute("list");

String parentId = (String) request.getAttribute("parentId");
String start = (String) request.getAttribute("start");
String range = (String) request.getAttribute("range");
String parentOfParentId = (String) request.getAttribute("parentOfParentId");


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
                <a href="addNewsCatalog.jsp?parentId=<%=parentId %>"><img src="<%=request.getContextPath()%>/Images/btn_add.gif" border="0"/></a>
            
            &nbsp;
            <%if (!"-1".equals(parentId)) { %>
                <a href="<%=request.getContextPath() %>/NewsCatalog/ListNewsCatalog?parentId=<%=parentOfParentId%>">
                    <img src="<%=request.getContextPath() %>/Images/btn_back.gif" border="0" />
                </a>
            <%} %>
            </td>
        </tr>
        
    </table>
    
    <table width="90%" align="center" cellpadding="3" cellspacing="1" border="0" class="table" >
        <tr class="tr">
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">名称</td>
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">子分类</td>
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">新闻条目</td>
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">修改</td>
            <td align="center" bgcolor="eoeoeo" nowrap="nowrap">删除</td>
        </tr>
        <%          
        for (int i = 0; list != null && i < list.size(); i++) {
            NewsCatalog bean = list.get(i);
        %>
        <tr  class="tr">
            <td align="center"><%=bean.getName() %></td>
            <td align="center"><a href="ListNewsCatalog?parentId=<%=bean.getId() %>&parentOfParentId=<%=parentId%>">子分类</a></td>
            <td align="center">新闻条目</td>
            <td align="center"><a href="UpdatePNewsCatalog?parentId=<%=parentId%>&id=<%=bean.getId()%>">更新</a></td>
            <td align="center">删除</td>
        </tr>
        <%} %>
    </table>
    
    <%@include file="../footer.jsp" %>
</body>
</html>