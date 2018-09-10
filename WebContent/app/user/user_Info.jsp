<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>常用旅客信息</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/css/style.css" />
</head>
<body>
	<div class="main_box">
		<h2>
			<span></span>常用旅客信息
		</h2>
		<div class="search_box clearfix">
		<form action="${pageContext.request.contextPath }/userinfosearch" method="post">
			<label class="f_left">搜索旅客信息：</label> <input name="search" type="search"
				placeholder="请输入姓名" class="f_left search_input" /> <input
				type="submit" value="搜索" class="btn blue_btn search" /></form>
		</div>
		<div class="cont_box">
			<dl class="tab_conbox">
				<!--会员基本信息-->
				<!--旅客-->
				<dd>
					<div class="margin_tb clearfix">
						<button type="button" class="btn blue_btn add_car">添加旅客</button>
					</div>
					<!--旅客信息-->
					<c:forEach items="${requestScope.psgs}" var="psg">
						<div class="ucar_list">
							<table border="0" cellspacing="0" cellpadding="0" class="table">
								<tbody>
									<tr>
										<td>姓名</td>
										<td>${psg.passenName }</td>
										<td>乘客类型</td>
										<td>${psg.passenType }</td>
									</tr>
									<tr>
										<td>证件类型</td>
										<td>${psg.passenLtype }</td>
										<td>证件号</td>
										<td>${psg.passenLinsen }</td>
									</tr>
									<tr id="${psg.passenId }">
									<td>操作</td>
									<td colspan="3" style="text-align: center;">
									
								<a href="javascript:void(0);" class="edit_car" style="color: #e7711d;">编辑旅客信息</a>
								<a href="javascript:void(0);" class="del_car" style="color: #e7711d;">删除旅客</a>
				
									</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:forEach>
				</dd>

			</dl>
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
	<script src="${pageContext.request.contextPath }/app/user/js/other.js"></script>
	<script>
		$(function() {
			$.jqtab(".tab_tit", ".tab_conbox", "click");
			var  addid="";
			$("body").other({
				tableId : "#table_box",
				tableWrap : [ 0, 1, 2, 3, 4, 7, 8 ],
				tableAas : [ 6, "desc" ]
			});//formUrl 表单请求地址,linkHref 请求成功后跳转地址，此页面不需要，已删除
			$("body")
					.tipWindow(
							{
								method : "edit",
								type : "form",
								Class : ".add_car",
								even : "click",
								dataId :addid,
								tipTit : "添加旅客",
								Twidth : "700",
								Theight : "520",
								editUrl : "${pageContext.request.contextPath }/useraddpsg"
							});//editUrl 添加旅客请求地址
			$("body")
					.tipWindow(
							{
								method : "edit",
								type : "form",
								Class : ".edit_car",
								even : "click",
								tipTit : "编辑旅客",
								Twidth : "700",
								Theight : "520",
								editUrl:"${pageContext.request.contextPath }/editpsg"
							});//editUrl 编辑旅客请求地址
			$("body").tipWindow({
				method : "delete",
				Class : ".del_car",
				even : "click",
				Twidth : "400",
				Theight : "180",
				delUrl : "${pageContext.request.contextPath }/deletepsg"
			});//delUrl 删除旅客请求地址
		});
	</script>
</body>
</html>

