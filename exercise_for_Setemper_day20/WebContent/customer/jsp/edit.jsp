<%@page import="com.xjx.pojo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRE-edit</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/default.css">
<link style="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css" media="all">
<% 
	String id = "" ;
	String name = "" ;
	String gender = "" ;
	String birthday = "" ;
	String email = "" ;
	String phone = "" ;
	Customer customer = (Customer)request.getAttribute("customer") ;
	if(customer != null){
		id = customer.getId() + "" ;
		name = customer.getName() ;
		gender = customer.getGender() ;
		birthday = customer.getBirthday() + "" ;
		email = customer.getEmail() ;
		phone = customer.getPhone() ;
	}
%>
</head>
<body>
	<div style="margin-top: 2%">
		<h3 align="center">添加客户</h3>
		<form id="editForm" action="<%=request.getContextPath()%>/customerServlet?method=<%=customer==null ? "insert" : "update" %>" method="post" style="margin: auto;">
			<table class="tab-base" style="margin: auto;">
				<tr>
					<td colspan="8" style="border: 0px;text-align: right;">
						<a href="<%=request.getContextPath()%>">返回首页</a>
					</td>
				</tr>
				<tr hidden="hidden">
					<th>ID</th>
					<td><input type="text" name="id" class="text-input" value="<%=id%>"></td>
				</tr>
				<tr>
					<th>姓名</th>
					<td><input type="text" name="name" class="text-input" value="<%=name%>"></td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						<input required="required" type="radio" name="gender" value="M" <%=gender.equals("M") ? "checked" : "" %>>男
						<input type="radio" name="gender" value="F" <%=gender.equals("F") ? "checked" : "" %>>女
					</td>
				</tr>
				<tr>
					<th>生日</th>
					<td><input type="text" name="birthday" class="text-input" value="<%=birthday%>" id="calendar" readonly="readonly"></td>
				</tr>
				<tr>
					<th>邮箱</th>
					<td><input type="text" name="email" class="text-input" value="<%=email%>"></td>
				</tr>
				<tr>
					<th>电话</th>
					<td><input type="text" name="phone" class="text-input" value="<%=phone%>"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" class="btn-ok"></td>
				</tr>
			</table>
		</form>
	</div>
<script src="<%=request.getContextPath()%>/layui/layui.all.js"  ></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"  ></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.js"  ></script>
<script src="<%=request.getContextPath()%>/js/formValidatorClass.js"  ></script>
<!--  <script src="<%=request.getContextPath()%>/js/messages_zh.js"  ></script>-->
 <script>
	layui.use('laydate', function(){
	  var laydate = layui.laydate;	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#calendar' //指定元素
	  });
	});
	$("#editForm").validate({
		rules:{
			name: {
				required: true,
				minlength: 2 
			},
			gender:{
				required: true,
			},
			email:{
				required: true,
				email: true
			},
			birthday:{
				required: true
			},
			phone:{
				required: true,
				isPhone: true
			}
		},
		messages:{
			name: {
				required: "用户名不能为空！",
				minlength: "用户名至少填两个字符！" 
			},
			gender:{
				required: "请选择性别！"
			},
			email:{
				required: "请输入邮箱信息！",
				email: "请输入正确的邮箱信息！"
			},
			birthday: {
				required: "请选择生日日期！"
			},
			phone:{
				required: "请输入电话信息！",
				isPhone: "请输入正确的电话信息"
			}
		},
		errorElement: "em"
	})
</script>
</body>
</html>