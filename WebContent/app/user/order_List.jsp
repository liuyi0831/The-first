<%@page import="com.cx.pojo.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单列表</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/app/user/css/datepicker.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/app/user/css/style.css" />
</head>
<body>
	<div class="main_box">
		<h2>
			<span></span>订单列表
		</h2>
		<form action="${pageContext.request.contextPath }/searchorder" method="post" id="order_shform">
			<div class="search_box clearfix">
				<input type="text" class="f_left" name="order_code"
					placeholder="输入订单号查询" style="margin-right: 15px;" /> <label
					class="f_left">航班出发日期：</label>
				<div class="date_box">
					<input type="text" name="ftime" id="start_date" readonly /> <i
						class="fa fa-calendar"></i>
				</div>
				<label
					class="f_left">下单日期：</label>
				<div class="date_box">
					<input type="text" name="otime" id="start_date" readonly /> <i
						class="fa fa-calendar"></i>
				</div>
				<input type="submit" value="搜索" class="btn blue_btn search" />
				</form>
			</div>

		<div class="cont_box">
			<table border="0" cellspacing="0" cellpadding="0" class="table">
				<thead>
					<tr>
						<th>订单号</th>
						<th>航班出发日期</th>
						<th>航班名称</th>
						<th>订单金额</th>
						<th>下单日期</th>
						<th>订单状态</th>
						<th width="200">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${requestScope.orders}" var="order">
					<tr id="${order.orderId },${order.flightid}">
						<!--此处id为进行编辑或删除时该条数据的唯一标识-->
						<td>${order.orderId }</td>
						<td>${order.flightftime }</td>
						<td>${order.flightname }</td>
						<td>${order.amount }</td>
						<td>${order.orderTime }</td>
						<td>${order.statement }</td>
						<td><a href="javascript:void(0);"
							class="table_btn table_info detail_btn"> <i class="fa fa-eye"></i>
								<span>订单详情</span>
						</a><c:if test="${order.statement.equals('订购成功') or ('订购中') }">
						<a href="javascript:void(0);"
							class="table_btn table_edit edit_btn"> <i class="fa fa-edit"></i>
								<span>编辑订单</span>
						</a> 
						 </c:if>
						
						 
						<a href="javascript:void(0);" class="table_btn table_del del_btn">
								<i class="fa fa-recycle"></i> <span>删除订单</span>
						</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--javascript include-->
	<script src="${pageContext.request.contextPath }/app/user/js/jquery-2.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/app/user/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath }/app/user/js/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath }/app/user/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath }/app/user/js/other.js"></script>
	<script>
		$(function() {
			$("body").other({
				tableId : "#table_box",
				tableWrap : [ 0, 1, 2, 3, 4, 7, 8 ],
				tableAas : [ 6, "desc" ]
			});
			$("body").tipWindow({
				method : "delete",
				Class : ".del_btn",
				even : "click",
				deleteTxt : "确认删除吗？",
				Twidth : "400",
				Theight : "180",
				delUrl : "${pageContext.request.contextPath }/deleteorder"
			});//delUrl 退单请求地址
			$("body").tipWindow({
				method : "edit",
				type : "form",
				Class : ".detail_btn",
				even : "click",
				tipTit : "订单详情",
				Twidth : "800",
				Theight : "600",
				editUrl : "${pageContext.request.contextPath }/orderdetail"
			});
			 $("body").tipWindow({
				method : "edit",
				type : "form",
				Class : ".edit_btn",
				even : "click",
				tipTit : "编辑订单",
				Twidth : "800",
				Theight : "600",
				editUrl : "${pageContext.request.contextPath }/orderedit"
			});//editUrl 编辑请求地址 
		});
	</script>
</body>
</html>
