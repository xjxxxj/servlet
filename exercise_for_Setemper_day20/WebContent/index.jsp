<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRM</title>
</head>
<body>
	<div>
		<dl>
			<dt>CRM客户管理系统</dt>
			<dd><a href="<%=request.getContextPath()%>/customer/jsp/edit.jsp">添加客户</a></dd>
			<dd><a href="<%=request.getContextPath()%>/customerServlet?method=query">客户列表</a></dd>
		</dl>
	</div>
</body>
</html>