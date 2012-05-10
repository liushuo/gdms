<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:include value="header.jsp"></s:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
    <div id="searchPane"></div>
    <div id="mainPane">
    <s:form>
    <s:hidden name="departmentId"></s:hidden>
    <table border="1">
    <tr><th>All</th><th>name</th><th>birthday</th><th>income</th><th>gender</th><th>操作</th></tr>
    <s:iterator value="userList" status="user">
    <tr>
    <td><input type="checkbox" name="checkedIds" value="${id}"></input></td>
    <td><s:property value="name"/></td>
    <td><s:date name="birthday" format="yyyy-MM-dd"></s:date></td>
    <td><s:property value="income"/></td>
    <td>${gender==true?'男':'女'}</td>
    <td>
    <% if(request.isUserInRole("uadmin")){ %>
    <a href="user!input.action?id=${id}">编辑</a>
    <a href="user!delete.action?id=${id}&departmentId=${departmentId}">删除</a>
    <%}else{ %>
    <a href="user!input.action?id=${id}">查看</a>
    <%} %>
    </td>
    </tr>
    </s:iterator>
    </table>
    <% if(request.isUserInRole("uadmin")){ %>
    <s:submit method="batch" action="user" align="left" value="删除" />
    <%} %>
    </s:form>
    </div>
  </body>
</html>
