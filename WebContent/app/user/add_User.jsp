<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员添加</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/css/datepicker.css" />
</head>
<body>
	<div class="main_box">
		<h2>
			<span></span>用户信息修改
		</h2>
		<div class="cont_box">
			<form action="${pageContext.request.contextPath }/adduser"
				method="post" id="user_form">
				<ul class="addpro_box adduser_box">
					<li><label>姓名：</label> <input type="text"
						value="${user.userName }" name="userName" required
						data-rule-fullname="true" data-msg-required="会员姓名不能为空" /></li>
					<li><label>手机号：</label> <input type="text"
						value="${user.userPhone }" placeholder="请输入手机号" name="userPhone"
						required data-rule-mobile="true" data-msg-required="会员手机号不能为空" />
					</li>
					<li><label>邮箱：</label> <input type="text"
						value="${user.userEmail }" placeholder="请输入邮箱" name="userEmail"
						required data-rule-emile="true" data-msg-required="会员邮箱不能为空" /></li>
					<li><label>密码：</label> <input type="password"
						placeholder="请输入密码以确认身份" required data-rule-password="true" data-msg-required="密码不能为空" name="pwd">
					
					</li>
				</ul>
				<div class="probtn_box clearfix">
					<input type="submit" value="确认修改" class="btn blue_btn" />
				</div>
				<div class="probtn_box clearfix">
				<a href="${pageContext.request.contextPath }/app/user/userpwd_edit.jsp">修改密码</a>
				</div>
			</form>
			
		</div>
	</div>
	<!--javascript include-->
	<script
		src="${pageContext.request.contextPath }/app/user/js/jquery-2.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/app/user/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/app/user/js/bootstrap-datepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/app/user/js/jquery.validate.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/app/user/js/jquery.form.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/app/user/js/jquery.cxselect.min.js"></script>
	<script src="${pageContext.request.contextPath }/app/user/js/other.js"></script>
	<script>
			$(function(){
				$("body").other({formId:"#user_form",formUrl:"#",linkHref:""});//formUrl 表单请求地址,linkHref 请求成功后跳转地址，可不填
				$("#carmodel").cxSelect({
					url: "js/carModel.json",
					selects: ["carbrand", "carmodela", "carmodelb", "carmodelc"],
					emptyStyle: "none"
				});
			});
		</script>
</body>
</html>