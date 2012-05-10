<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:include value="header.jsp"></s:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user-input.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<sx:head/>
  </head>
  
  <body>
    <div id="mainPane">
    <s:actionerror/>
    <s:form action="department-save" namespace="/" method="post">
    	<s:hidden name="department.id" ></s:hidden>
	    <s:textfield name="department.name" label="name"></s:textfield>
	    <s:textfield name="department.telephone" label="telephone"></s:textfield>
	    <% if(request.isUserInRole("dadmin")){ %>
		<s:submit value="提交"></s:submit>
		<s:reset value="重置"></s:reset>
		<%} %>
    </s:form>
    <s:if test="department.id!=null">
	    <s:action name="user" namespace="/" executeResult="true">
	    	<s:param name="departmentId" value="department.id"></s:param>
	    </s:action>
	    <% if(request.isUserInRole("uadmin")){ %>
	    <a href="user!input.action?departmentId=${department.id}">新建员工</a>
	    <%} %>
    </s:if>
    </div>
  </body>
</html>
