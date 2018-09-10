<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/app/behind/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/app/behind/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/app/behind/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/app/behind/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/app/behind/js/xadmin.js"></script>
  </head>
  <body>
    <div class="x-body">
        <form class="layui-form" action="${pageContext.request.contextPath }/editpower" method="post">
          <table class="layui-table">
        <thead>
          <tr>
            <th>座位类型</th>
            <th>价格</th>
            <th>剩余座位数</th>
            <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.seats }" var="seat">
          <tr>
            <td>${seat.seatType }</td>
            <td>${seat.flightPrice }</td>
            <td>${seat.flightSeats }</td>
            <td class="td-manage">
              <a title="编辑"  onclick="x_admin_show('编辑','${pageContext.request.contextPath }/seatedit?seatid=${seat.priceId }')" href="javascript:;">
                <i class="layui-icon">&#xe6b2;</i>
              </a>
              <a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr></c:forEach>
          <tr><td colspan="4" style="text-align: center;">
          <a title="添加"  onclick="x_admin_show('编辑','${pageContext.request.contextPath }/addseat?flightid=${fliid }')" href="javascript:;">
                <i class="iconfont">&#xe6b9;</i>
              </a>
          </td></tr>
        </tbody>
      </table>
      </form>
    </div>
    
  </body>

</html>