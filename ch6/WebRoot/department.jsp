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
    
    <title>My JSP 'department.jsp' starting page</title>
    
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
    <table border="1">
    <tr><th>NO</th><th>name</th><th>telephone</th><th>操作</th></tr>
    <s:iterator value="departmentList" status="st">
    <tr>
    <td>${st.index+1}</td>
    <td><s:property value="name"/></td>
    <td><s:property value="telephone"/></td>
    <td>
    <% if(request.isUserInRole("dadmin")){ %>
	    <a href="department-input.action?id=${id}">编辑</a>
	    <a href="department-delete.action?id=${id}">删除</a>
    <%}else{ %>
	    <a href="department-input.action?id=${id}">查看</a>
	<%} %>
    </td>
    </tr>    
    </s:iterator>
    </table>
    <% if(request.isUserInRole("dadmin")){ %>
    <a href="department-input.action">新建部门</a>
    <%} %>
    </div>
  </body>
</html>
