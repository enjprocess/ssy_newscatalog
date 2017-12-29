<%@page language="java" contentType="text/html; charset=GBK"%>
<%@ page import ="java.util.*, com.shengsiyuan.imis.util.*"%>

<link rel="stylesheet" href="style.css">


<body style="margin:0px" bgcolor="#D5E3E0">

<br>
<br>
<br>
<table width="100%" height="384" cellpadding="0" cellspacing="0" align="center">
<tr>
<td>
<img src="<%=request.getContextPath()%>/Images/loginbg_left.gif" width="100%" height="384"><br>
</td>
</tr>
</table>

<script type="text/javascript">

alert("<%= request.getAttribute(Common.SESSION_ERROR)%>");
history.back();

</script>