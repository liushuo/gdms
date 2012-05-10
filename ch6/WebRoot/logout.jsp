<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="true"%>
 <%=request.getRemoteUser()%>已成功退出
<% session.invalidate(); %>
【<a href="index.jsp">返回首页</a>】