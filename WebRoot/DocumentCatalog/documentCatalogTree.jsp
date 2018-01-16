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
<% String title = "公司文档"; %>
<%@ include file="../title.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../lib/jquery.js"></script>
<script type="text/javascript" src="../jstree/jquery.tree.js"></script>
<script type="text/javascript" src="../lib/jquery.metadata.js"></script>

<link type="text/css" rel="stylesheet" href="../jstree/syntax/shCore.css"/>
<link type="text/css" rel="stylesheet" href="../jstree/syntax/shThemeDefault.css"/>
<script type="text/javascript">

var url = "DocumentCatalogTree";

$(function () { 
	$("#tree").tree({
        data : { 
            type : "json",
            async : true,
            opts : {
                method : "POST",
                url : url
            }
        },
        callback:{
            beforedata: function(NODE, TREE_OBJ) { 
                return { parentId : $(NODE).attr("id") || -1 } 
            }
        }
    });
});

</script>
</head>
<body>


    <table width="400" align="center"  >
    
        <tr>
            <td align="left">
            </td>
        </tr>
        
    </table>
    
    
    <div id="tree">
    </div>
    
   

    
    <%@include file="../footer.jsp" %>
    
            
       
</body>
</html>