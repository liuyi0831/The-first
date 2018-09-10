<%@page import="com.cx.pojo.Flight"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>编辑订单</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/app/user/fontsawesome/css/font-awesome.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/app/user/css/style.css"/>
	</head>
	<body>
		<div class="main_box">
			<div class="cont_box">
				<!--订单号-->
			
				<div class="user_top">订单号：<span><%=session.getAttribute("orderid") %></span></div>
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
							
							<th>操作</th>
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
							
							<td>
								<a href="${pageContext.request.contextPath }/retickit" class="table_btn table_del del_btn">
									<i class="fa fa-trash-o"></i>
									<span>退票</span>
								</a>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="clearfix" style="margin-top:20px;">
					<input type="button" value="更改航班" class="btn line_btn" onclick="changefli(${flight.flightId})">
				</div>
				<script type="text/javascript">
				function changefli(flid) {
					location.href="${pageContext.request.contextPath }/psgchange?flid="+flid
				}
				</script>
				<!--订单服务-->
				<form action="${pageContext.request.contextPath }/orderupdate" method="post">
				<div class="user_top order_top">乘客信息：</div>
				<table border="0" cellspacing="0" cellpadding="0" class="table pre_table">
					<thead>
						<tr>
							<th>姓名</th>
							<th>乘客类型</th>
							<th>证件号码</th>
							<th>证件类型</th>
							<!-- 
							<th>操作</th> -->
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.psgs }" var="psg">
					<input type="hidden" name="psgids" value="${psg.passenId }">
						<tr>
							<td>${psg.passenName }</td>
							<td>${psg.passenType }</td>
							<td>${psg.passenLinsen }</td>
							<td>${psg.passenLtype }</td>
							<%-- <td>
								<a href="${pageContext.request.contextPath }/userdeletepsg?psgid=${psg.passenId }" class="table_btn table_del del_btn">
									<i class="fa fa-trash-o"></i>
									<span>删除</span>
								</a>
							</td> --%>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="clearfix" style="margin-top:20px;">
					<input type="button" value="添加乘客" class="btn line_btn" onclick="addpsg()">
				</div>
				<script type="text/javascript">
				function addpsg() {
					location.href="${pageContext.request.contextPath }/orderaddpsg?priceid=${price.priceId}"
				}
				 function submit() {
					var ids=[];
					var get=document.getElementsByName("psgid");
					for (var i = 0; i < get.lenth; i++) {
						if (get[i].value!=null) {
							ids.push(get[i].value);
						}
						
					}
					location.href="${pageContext.request.contextPath }/orderupdate?psgids="+ids;
				}
				</script>
				<!--订单备注-->
				<div class="clearfix" style="margin-top:20px;">
					<input type="submit" value="确认保存" class="btn blue_btn total_btn">
				</div>
				</form>
				
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
				tableShow();
				$(".del_btn").click(function(){
					if($(this).parents("tbody").find("tr").length<=1){
						$(this).parents(".table").prev().remove();
						$(this).parents(".table").remove();
					}else{
						$(this).parents("tr").remove();
					}
				});
			});
			function tableShow(){
				$(".table").each(function(){
					if($(this).find("tbody tr").length<=0){
						$(this).prev().remove();
						$(this).remove();
					}
				});
			}
		</script>
	</body>
</html>