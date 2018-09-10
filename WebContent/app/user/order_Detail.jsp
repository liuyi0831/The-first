<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>订单详情</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/app/user/css/style.css"/>
	</head>
	<body>
		<div class="main_box">
			<div class="cont_box">
				<!--订单号-->
				<div class="user_top">订单号：<span>${order.orderId }</span></div>
				<!--订单商品-->
				<div class="user_top order_top">航班信息：</div>
				<table border="0" cellspacing="0" cellpadding="0" class="table">
					<thead>
						<tr>
						    <th>航班名</th>
							<th>出发地</th>
							<th>目的地</th>
							<th>出发时间</th>
							<th>到达时间</th>
							<th>座位类型</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${flight.flightName }</td>
							<td>${flight.flightFrom }</td>
							<td>${flight.flightArr }</td>
							<td>${flight.flightFtime }</td>
							<td>${flight.flightAtime }</td>
							<td>${price.seatType }</td>
						</tr>
					</tbody>
				</table>
				<!--订单服务-->
				<div class="user_top order_top">乘客信息：</div>
				<table border="0" cellspacing="0" cellpadding="0" class="table pre_table">
					<thead>
						<tr>
							<th>姓名</th>
							<th>乘客类型</th>
							<th>证件号码</th>
							<th>证件类型</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.psgs }" var="psg">
						<tr>
							<td>${psg.passenName }</td>
							<td>${psg.passenType }</td>
							<td>${psg.passenLinsen }</td>
							<td>${psg.passenLtype }</td>
						</tr></c:forEach>
					</tbody>
				</table>
				<!--订单备注-->
			</div>
		</div>
		<!--javascript include-->
		<script src="${pageContext.request.contextPath }/app/user/js/jquery-2.2.1.min.js"></script>
		<script src="${pageContext.request.contextPath }/app/user/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath }/app/user/js/bootstrap-datepicker.js"></script>
		<script src="${pageContext.request.contextPath }/app/user/js/jquery.validate.min.js"></script>
		<script src="${pageContext.request.contextPath }/app/user/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath }/app/user/js/other.js"></script>
		<script>
			$(function(){
				$("body").other({formId:"#editPro_form",formUrl:"#"});//formUrl 表单请求地址
			});
		</script>
	</body>
</html>