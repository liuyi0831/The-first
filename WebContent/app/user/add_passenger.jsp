<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>本店会员</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/css/style.css" />
</head>
<body>
	<div class="main_box">
		<h2>
			<span></span>常用旅客
		</h2>
		<div class="cont_box">
			<table border="0" cellspacing="0" cellpadding="0" class="table"
				id="table_box">
				<thead>
					<tr>
						<th>姓名</th>
						<th>类型</th>
						<th width="268">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.psgs }" var="psg">
						<tr>
							<!--此处id为进行查看该会员相关信息时，当前数据的唯一标识-->
							<td>${psg.passenName }</td>
							<td>${psg.passenType}</td>
							<td> <a href="${pageContext.request.contextPath }/updatepsg?psgid=${psg.passenId }&priceid=${priceid}"
								class="table_btn table_info edit_btn"> <i class="fa fa-eye"></i>
									<span>确认修改</span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
			$("body").other({
				tableId : "#table_box",
				tableWrap : [ 1, 2, 8 ],
				tableAas : [ 0, "desc" ],
				tableSearch : true
			});
			$("body")
					.tipWindow(
							{
								method : "edit",
								type : "form",
								Class : ".edit_btn",
								even : "click",
								tipTit : "会员详情",
								Twidth : "1200",
								Theight : "800",
								editUrl : "${pageContext.request.contextPath }/app/user/user_Detail.jsp"
							});//editUrl 编辑请求地址
		});
	</script>
</body>
</html>
