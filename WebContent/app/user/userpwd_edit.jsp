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
			<span></span>修改密码
		</h2>
		<div class="hello_info">
			<ul>
			<form action="${pageContext.request.contextPath }/pwdedit">
				<li><label style="width: 90px;">原密码： </label> <input type="password"
						placeholder="请输入密码以确认身份" required data-rule-password="true" data-msg-required="密码不能为空" name="pwd">
					</li><br>
					<li><label style="width: 90px;">新密码：</label> <input type="password"
						name="pwd1" id="userPassword" /></li><br>
					<li><label>确认密码：</label> <input type="password"
						name="pwd2" id="userPassword1" onblur="check()">
						<span id="message" style="color: #d21f1f; margin-left: 122px;"></span>
						</li>
				</ul>
				<div class="probtn_box clearfix">
					<input type="submit" value="确认修改" class="btn blue_btn" />
				</div>
				</form>
		</div>
	</div>
	<script type="text/javascript">
				function check() {
						var pwd1=document.getElementById("userPassword").value;
						var pwd2=document.getElementById("userPassword1").value;
						$.ajax({
							type : "post",
							url : "${pageContext.request.contextPath }/checkpwd",
							data : {
								"pwd1":pwd1,
								"pwd2":pwd2
							},
							success : function(data) {
								$("#message").text(data);
								if (data!="") {
									$("#userPassword1").val("")
								}
							},
							error : function(data) {
								
							}
						});
				}
				</script>
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
