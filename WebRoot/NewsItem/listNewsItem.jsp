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
<script type="text/javascript" src="../lib/jquery.js"></script>
<script type="text/javascript" src="../lib/jquery.blockUI.js"></script>
<script type="text/javascript">

function showNewsItem(id) {
	
	$.blockUI({message: $('#newsItemPane'), css: {cursor:''}});
	$.post("ViewNewsItem", 
			{
	         id : id
			}, 
			function(returnedData, status) {
				$("#title").html(returnedData.newsName);
				$("#content").html(returnedData.newsContent);
				
				var attList = returnedData.list;
				var str = "";
				for (var i = 0; i < attList.length; i++) {
					str += "<a href='../NewsAttachment/DownloadNewsAttachment?parentId=" + attList[i].parentId + "&name=" 
							+ attList[i].name + "&randomName=" + attList[i].randomName + "'>" + attList[i].name + "</a><br/>"
				}
				$("#attachment").html(str);
			})
}

function closeNewsItem() {
	$.unblockUI();
}


</script>
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
            <td align="center"><a href="#" onclick="showNewsItem('<%= bean.getId()%>')"><%=bean.getName() %></a></td>
            <td align="center"><a href="../NewsAttachment/ListNewsAttachment?parentId=<%= bean.getId()%>">管理附件</a></td>
            <td align="center"><a href="UpdatePNewsCatalog?start=<%= start %>&range=<%= range %>&parentId=<%=parentId%>&id=<%=bean.getId()%>">更新</a></td>
            <td align="center"><a href="DeleteNewsCatalog?start=<%= start %>&range=<%= range %>&parentId=<%= parentId %>&id=<%= bean.getId() %>" onclick="return del();">删除</a></td>
        </tr>
        <%} %>
    </table>
    

    
    <%@include file="../footer.jsp" %>
    
            
       <table id="newsItemPane" style="display: none" width="90%" align="center" cellpadding="3" cellspacing="1" border="0"  >
           <tr class="tr">
               <td align="center" bgcolor="eoeoeo" nowrap="nowrap" id="title">标题</td>
           </tr>
           <tr>
               <td align="center" nowrap="nowrap" id="content">内容</td>
           </tr>
           <tr>
               <td align="center" nowrap="nowrap" id="attachment"></td>
           </tr>
           <tr>
               <td align="center" nowrap="nowrap"><input type="button" value="关闭窗口" onclick="closeNewsItem();" /></td>
           </tr>
       </table>
</body>
</html>