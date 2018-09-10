<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑旅客信息</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/css/datepicker.css" />
</head>
<body>
	<div class="main_box">
		<div class="cont_box editpro_box">
			<form action="${pageContext.request.contextPath }/updatepsgbyuser" method="post" id="user_form">
				<ul class="addpro_box adduser_box">
				<input type="hidden" name="passenId" value="${psg.passenId}">
					<li><label>乘客类型：</label>
						<div class="select_group" id="carmodel">
							<select name="passenType">
								<option value="${psg.passenType}">请选择</option>
								<option value="成年">成年</option>
								<option value="儿童">儿童</option>
							</select>
						</div></li>
					<li><label>证件类型：</label>
						<div class="select_group" id="carmodel">
							<select name="passenLtype">
								<option value="${psg.passenLtype}">请选择</option>
								<option value="二代居民身份证">二代居民身份证</option>
								<option value="护照">护照</option>
								<option value="港澳台通行证">港澳台通行证</option>
							</select>
						</div></li>
					<li><label>姓名：</label> <input type="text" name="passenName" value="${psg.passenName}"
						required data-rule-fullname="true" data-msg-required="会员姓名不能为空" />
					</li>
					<li><label>证件号码：</label> <input type="text" name="passenLinsen" value="${psg.passenLinsen}"
						required data-rule-paper="true" data-msg-required="证件号码不能为空" /></li>
				</ul>
				<div class="probtn_box clearfix">
					<input type="submit" value="保存修改" class="btn blue_btn submit" />
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
		$(function() {
			$("body").other({
				formId : "#user_form",
				formUrl : "${pageContext.request.contextPath }/updatepsgbyuser"
			});//formUrl 表单请求地址,linkHref 请求成功后跳转地址，此页面不需要，已删除
			$("#carmodel").cxSelect(
					{
						url : "js/carModel.json",
						selects : [ "carbrand", "carmodela", "carmodelb",
								"carmodelc" ],
						emptyStyle : "none"
					});
		});
	</script>
</body>
</html>