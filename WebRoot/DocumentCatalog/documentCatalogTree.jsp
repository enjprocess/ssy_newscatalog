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
<script type="text/javascript" src="../lib/jquery.blockUI.js"></script>

<script type="text/javascript">

var src = "DocumentCatalogTree?type=${param.type}";
var firstLoading = true;
var selectNodeId = -1;

$(document).ready(function(){
	$('#documentCatalog').tree({
		data : {
			type: 'json',
			async: 'true',
			opts: {
				method: 'POST',
				url:src
			}
		},
		lang : {
			loading: "目录加载中..."
		},
		ui: {
			theme_name: 'apple'
		}, 
		callback: {
			beforedata: function(NODE, TREE_OBJ) {
				
				if (firstLoading) {
					firstLoading = false;
				    return {parentId: ${param.parentId}};
				} else {
					return {parentId:$(NODE).children("a").attr("id").substring(4)};
				}
				
			},
			onselect : function(NODE, TREE_OBJ) {
				selectNodeId = $(NODE).children("a").attr("id").substring(4);
			}
		}
		
		
	});
})


function createDocumentCatalog() {
	$.blockUI({message:$('#addDocumentCatalogArea')})
}

function closeWindow() {
	$(":text").val("");
	$.unblockUI();
}

function addDocumentCatalog() {
	
	
	$.post("AddDocumentCatalog", {
		title: $("#title").val(),
		parentId: selectNodeId,
		type: ${param.type}
	}, function(returnedData, status) {
		if (-1 == selectNodeId) {
			$.tree.focused().create({"data":{"title":$("#title").val(),"attributes":{"id":"node" + returnedData,"href":""}},"state":"leaf"}, $('li:first'), 0);
		} else {
			$.tree.focused().create({"data":{"title":$("#title").val(),"attributes":{"id":"node" + returnedData,"href":""}},"state":"leaf"}, "#node" + selectNodeId, 0);
		}
		closeWindow();
	});
	
}

function updatePDocumentCatalog() {
	//改变"增加"按钮的行为
	document.getElementById("sample").onclick = updateDocumentCatalog;
	$.blockUI({message:$('#addDocumentCatalogArea')})
}

function updateDocumentCatalog() {
	//还原"增加"按钮的行为
	document.getElementById("sample").onclick = addDocumentCatalog;
	$.ajax({
		type: "POST",
		url: "UpdateDocumentCatalog",
		data: {
			type: ${param.type},
			id: selectNodeId,
			title:$("#title").val()
		},
		success:function(returnedData, status) {
			$.unblockUI();
			$.tree.focused().rename("#node" + selectNodeId, $("#title").val());
			$("#title").val("");
		},
		error: function(XHR, textStatus, errorThro) {
			//alert($(XHR.responseText).length); 
/* 			for(var i = 0; i < $(XHR.responseText).length; i++) {
				console.log($(XHR.responseText).get(i).innerHTML);
			} */
			var source = "<h1>";
			var dest = "</h1>";
			var errMsg = XHR.responseText;
			var result = errMsg.substring(errMsg.indexOf(source) + source.length, errMsg.indexOf(dest));
			alert(result)
			$.unblockUI();
		}
	});
	
}


</script>
</head>
<body>



    <table width="400" align="left" border="1" >
    
        <tr>
            <td align="left">
                <input type="button" value="增加文档" onclick="createDocumentCatalog();" />
            </td>
            <td align="left">
                <input type="button" value="更新文档" onclick="updatePDocumentCatalog();" />
            </td>
        </tr>
        
    </table>
    
    
    <div id="documentCatalog">
    </div>
    
    <div id="addDocumentCatalogArea" style="display: none">
        <table width="400" align="center" border="1" >
	        <tr>
	             <td align="left" colspan="2">
                    <input type="text" id="title" style="width: 100%" />
                </td>
	        </tr>
	        <tr>
	           <td align="right">
                    <input id="sample" type="button" value="增加" onclick="addDocumentCatalog();" />
                </td>
	            <td align="left">
                    <input type="button" value="关闭" onclick="closeWindow();" />
                </td>
            </tr>
        </table>
    </div>
    
   

    
    <%@include file="../footer.jsp" %>
    
            
       
</body>
</html>