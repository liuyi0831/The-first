<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
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
        <form class="layui-form" action="${pageContext.request.contextPath }/updateseat" method="post">
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>航班名
              </label>
              <div class="layui-input-inline">            
                 <input type="text"  name="flightName" readonly="readonly" required="" lay-verify="pass" value="${flight.flightName }"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label class="layui-form-label">
               <span class="x-red">*</span>出发地
              </label>
              <div class="layui-input-block">
              <input type="hidden" name="flightId" value="${flight.flightId }">
                  <input type="text" style="width: 190px;" name="flightFrom" readonly="readonly" required="" lay-verify="pass" value="${flight.flightFrom }"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                                                           目的地
              </label>
              <div class="layui-input-inline">
                  <input type="text" name="flightArr" required="" lay-verify="pass" value="${flight.flightArr }"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
           <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                                                           出发时间
              </label>
              <div class="layui-input-inline">
                  <input type="text"  name="flightFtime" required="" lay-verify="pass" value=" ${flight.flightFtime}"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                                                           到达时间
              </label>
              <div class="layui-input-inline">
                  <input type="text"  name="flightAtime" required="" lay-verify="pass" value=" ${flight.flightAtime}"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" value="保存修改" class="layui-btn">
          </div>
      </form>
    </div>
  </body>
<script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;
          //监听提交
          form.on('submit(add)', function(data){
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("增加成功", {icon: 6},function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });
            return false;
          });
          
          
        });
    </script>

  
</html>