<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎页面</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/css/style.css" />
</head>
<body>
	<!--技术支持-->
	<div class="hello_box hello_opera" style="margin: 20px 0 20px 33%;">
		<h2>
			<span></span>欢迎登陆Govihar
		</h2>
		<div class="hello_info">
			<ul>
				<li style="text-align: center;"><span>${user.userName },您好！</span>
				</li>
				<li>
						<label>您的手机号：</label>
						<span>${user.userPhone}</span>
					</li> 
					<li>
						<label>您的邮箱地址：</label>
						<span>${user.userEmail}</span>
					</li> 
					<li>
						<label>您的账户余额：</label>
						<span>${user.userAccount}元</span>
					</li> 
					
			</ul>
		</div>
	</div>
	<!--javascript include-->
	<script
		src="${pageContext.request.contextPath }/app/user/js/jquery-2.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/app/user/js/tipSuppliers.js"></script>
	<script>
			$(function(){
				function box(){
					var bodyH = $("body").height(),
						bodyW = $("body").width(),
						boxL = parseInt($(".hello_box").css("margin-left"));
					$(".hello_order").css({"width":(parseInt(bodyW)-(boxL*3)-505),"height":parseInt(bodyH)-70});
					$(".hello_opera").css({"width":"445px","height":(parseInt(bodyH)-210)/3});
				};
				box();
				$(window).resize(function(){
					box();
				});
			});
		</script>
</body>
</html>
