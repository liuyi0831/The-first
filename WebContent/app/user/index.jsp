<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>个人中心</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/app/user/css/style.css" />
	</head>

	<body>
		<div class="header" style="background-color: #cdf0a9;">
			<div class="logo" style="text-align: center;">
				<a href="${pageContext.request.contextPath }/app/main/index.jsp"><img style="width: 139px;height: 49px;" src="images/logo.png" /></a>
			</div>
			<div class="nav"style="width: 1395px;">
				<ul class="clearfix">
					
					<li style="background-color: #30adef54">
						<i class="fa fa-user-circle-o font_lager"></i>
						<p data-id="user">个人信息</p>
					</li>
				</ul>
			</div>
			<ul class="login_msg" style="background-color: #cdf0a9;    float: right;">
				<li title="欢迎，您好！" style="color: #ffffe2;"><%out.print(session.getAttribute("uname")); %></li>
				<li>
					<a href="${pageContext.request.contextPath }/logout">退出</a>
				</li>
			</ul>
		</div>
		<!--top end-->
		<div class="main_left">
			<br>
			<h2><i class="menu_icon fa fa-reorder"></i></h2>
			<ul class="menu">
				<!--会员管理-->
				<li>
					<i class="menu_icon fa fa-user"></i>
					<a href="javascript:void(0);" data-id="user" data-href="${pageContext.request.contextPath }/userlist">用户信息</a>
				</li>
				<li>
					<i class="menu_icon fa fa-user-plus"></i>
					<a href="javascript:void(0);" data-id="user" data-fast="add_user" data-href="${pageContext.request.contextPath }/useredit">用户信息修改</a>
				</li>
				<li>
					<i class="menu_icon fa fa-vcard"></i>
					<a href="javascript:void(0);" data-id="user" data-href="${pageContext.request.contextPath }/userinfo">常用旅客信息</a>
				</li>
				<!--订单管理-->
				<li>
					<i class="menu_icon fa fa-file-text-o"></i>
					<a href="javascript:void(0);" data-id="user" data-href="${pageContext.request.contextPath }/orderlist">订单列表</a>
				</li>
			</ul>
		</div>
		<!--left end-->
		 <div class="main_right">
			<iframe src="${pageContext.request.contextPath }/app/user/Hello.jsp" name="cont_box" frameborder="0" width="100%" height="100%" seamless></iframe>
		</div> 
		<!--desktop end-->
		<!--javascript include-->
		<script src="${pageContext.request.contextPath }/app/user/js/jquery-2.2.1.min.js"></script>
		<script src="${pageContext.request.contextPath }/app/user/js/tipSuppliers.js"></script>
		<script>
			$("iframe[name='cont_box']").on("load", function() {
				$(".loading").hide();
				setTimeout(function() {
					$("iframe[name='cont_box']").css("opacity", "1");
				}, 500);
			});
			$(function() {
				$(".loading").hide();
				setTimeout(function() {
					$("iframe[name='cont_box']").css("opacity", "1");
				}, 500);
				$(".nav li:first").trigger("click");
			});
			jQuery("body").jrdek({
				Mtop: ".header",
				Mleft: ".main_left",
				Mright: ".main_right",
				foldCell: ".main_left h2"
			});
			$(".logo").click(function() {
				location.href="${pageContext.request.contextPath }/app/main/index.jsp"
			});
		</script>
	</body>

</html>