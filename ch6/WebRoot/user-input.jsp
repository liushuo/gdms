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
    <s:form action="user!save" namespace="/" method="post" enctype="multipart/form-data">
    	<s:hidden name="user.id" ></s:hidden>
	    <s:select list="departmentList" listKey="id" listValue="name" name="user.departmentId" label="department"></s:select>
	    <s:textfield name="user.name" label="name"></s:textfield>
	    <s:password name="user.password" label="password"></s:password>
	    <sx:datetimepicker name="user.birthday" toggleType="explode" value="today" displayFormat="yyyy-MM-dd" label="brithday"/>
	    <s:textfield name="user.income" label="income"></s:textfield>
		<s:radio list="#{true:'男',false:'女'}" name="user.gender" label="gender"></s:radio>
		<s:if test="user.photo!=null">
			<tr>
			<td></td><td><img src="user!showPhoto.action?id=${user.id}" width="200" height="100" alt="照片"/><s:checkbox name="deletePhoto" label="删除图片"></s:checkbox></td>
			</tr>
		</s:if>
		<s:else>
			<s:file name="photoFile"></s:file>
		</s:else>
		<% if(request.isUserInRole("uadmin")){ %>
		<s:submit value="提交"></s:submit>
		<s:reset value="重置"></s:reset>
		<%} %>
    </s:form>
    </div>
  </body>
</html>
