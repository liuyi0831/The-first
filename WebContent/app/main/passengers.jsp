<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>添加乘客</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Govihar Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		




</script>
<link rel="stylesheet" type="text/css"
	href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/app/main/css/build.css">
<!-- //Custom Theme files -->
<link
	href="${pageContext.request.contextPath }/app/main/css/bootstrap.css"
	type="text/css" rel="stylesheet" media="all">
<link href="${pageContext.request.contextPath }/app/main/css/style.css"
	type="text/css" rel="stylesheet" media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/app/main/css/flexslider.css"
	type="text/css" media="screen" />
	<script
	src="${pageContext.request.contextPath }/app/main/js/menu_jquery.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/app/main/css/JFFormStyle-1.css" />
<!-- js -->
<script
	src="${pageContext.request.contextPath }/app/main/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath }/app/main/js/modernizr.custom.js"></script>
<!-- //js -->
<!-- fonts -->
<link
	href='https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,700,500italic,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!-- //fonts -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#horizontalTab').easyResponsiveTabs({
			type : 'vertical', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true
		// 100% fit in a container
		});
	});
</script>
<!-- 复选框 -->
<script type="text/javascript">
	function changeState(el) {
		if (el.readOnly)
			el.checked = el.readOnly = false;
		else if (!el.checked)
			el.readOnly = el.indeterminate = true;
	}
</script>
<!--pop-up-->
<script
	src="${pageContext.request.contextPath }/app/main/js/menu_jquery.js"></script>
<!--//pop-up-->
</head>

<body>
	<!--header-->
	<div class="header">
		<div class="container">
			<div class="header-grids">
				<div class="logo">
					<h1>
						<a href="index.html"><span>Go</span>vihar</a>
					</h1>
				</div>
				<!--navbar-header-->
				<div class="header-dropdown">
					<div class="emergency-grid">
						<ul>
							<li>Toll Free :</li>
							<li class="call">+1 234 567 8901</li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="nav-top">
				<div class="top-nav">
					<span class="menu"><img
						src="${pageContext.request.contextPath }/app/main/images/menu.png"
						alt="" /></span>
					<div class="clearfix"></div>
					<!-- script-for-menu -->
					<script>
						$("span.menu").click(function() {
							$("ul.nav1").slideToggle(300, function() {
								// Animation complete.
							});
						});
					</script>
					<!-- /script-for-menu -->
				</div>
				<%
					if (session.getAttribute("uname") == null) {
				%>
				<div class="dropdown-grids">
					<div id="loginContainer">
						<a href="#" id="loginButton"><span>登陆</span></a>
						<div id="loginBox">
							<form id="loginForm"
								action="${pageContext.request.contextPath }/login" method="post">
								<div class="login-grids">
									<div class="login-grid-left">
										<fieldset id="body">
											<fieldset>
												<label for="email">邮箱地址</label> <input type="text"
													name="email" id="email">
											</fieldset>
											<fieldset>
												<label for="password">密码</label> <input type="password"
													name="password" id="password">
											</fieldset>
											<fieldset>
												<span> <%
 	if (session.getAttribute("backnews") != null)
 			out.print(session.getAttribute("backnews"));
 %>
												</span>
											</fieldset>
											<input type="submit" id="login" value="登录"> <label
												for="checkbox"><input type="checkbox" id="checkbox">
												<i>记住我</i></label>
										</fieldset>
										<span><a href="#">忘记密码?</a></span>
										<div class="or-grid">
											<p>OR</p>
										</div>
										<div class="social-sits">
											<div class="facebook-button">
												<a href="#">Connect with Facebook</a>
											</div>
											<div class="chrome-button">
												<a href="#">Connect with Google</a>
											</div>
											<div class="button-bottom">
												<p>
													新用户? <a href="signup.jsp">请注册</a>
												</p>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<%
					} else {
				%><div class="dropdown-grids">
					<div id="loginContainer">
						<a href="#" id="loginButton"><span><%=session.getAttribute("uname")%></span></a>
						<div id="loginBox">
							<form id="loginForm"
								action="${pageContext.request.contextPath }/logout"
								method="post">
								<div class="login-grids">
									<div class="login-grid-left">
										<fieldset id="body">
											<a href="${pageContext.request.contextPath }/app/user/index.jsp">进入个人中心</a>
										</fieldset>
										</fieldset>
										<input type="submit" id="login" value="退出登录">
										</fieldset>
									</div>
								</div>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<%
			}
		%>
		<div class="clearfix"></div>
	</div>
	<!--//header-->
	<!-- banner-bottom -->
	<div class="banner-bottom">
		<!-- container -->
		<div class="container">
			<div class="faqs-top-grids">
				<div class="book-grids">
					<div class="col-md-6 book-left">
						<div class="book-left-info">
							<h3>添加旅客信息</h3>
							<input type="hidden" value="${priceid}" id="priceid">
						</div>
						<div class="book-left-info">
							<br>
							<h4>常用旅客信息</h4>
							<br>
						</div>
						<script type="text/javascript">
							function batchAdd() {
								var arr = [];
								var ck = document.getElementsByName("checkbox3");
								var f = false;
								for (var i = 0; i < ck.length; i++) {
									if (ck[i].checked == true) {//代表选中
										arr.push(ck[i].value);
										f = true;
									}
								}
								if (f == false) {
									alert("请至少选择一位旅客");
									return;
								} else {
									var price=document.getElementById("priceid").value;
									arr.push(price);
									location.href="${pageContext.request.contextPath }/order?id="+arr;
								}
							}
						</script>
							<c:forEach items="${requestScope.passengers}" var="passenger">
								<div class="checkbox checkbox-info checkbox-circle">
									<input id="checkbox3" class="styled" type="checkbox" name="checkbox3"
										value="${passenger.passenId}"> <label for="checkbox3">
										<a href="#" onclick="detail(${passenger.passenId})">${passenger.passenName}</a> </label>
								</div>
							</c:forEach>
							<!-- <div id="fill" style="width: 100%; height: 100%; background-color: #000; position: absolute; top: 0px; left: 0px; opacity: 0.3; display: none; z-index: 100">
							</div> -->
							<!-- 用来查看修改常用旅客信息 -->
							<!-- <div id="myform" >
							<form action="">
							<input type="text" id="ptype" name="ptype">
							<input type="text" id="ltype" name="ltype">
							<input type="text" id="linsen" name="linsen">
							<input type="submit" value="确认"> <input type="reset" value="重置">
							</form>
							</div>
							<script type="text/javascript">
							function detail(id) {
								$.ajax({
									type:"get",
									url:"${pageContext.request.contextPath}/getdetail",
									data:{"psgid":id},
									success:function(data) {
									    $("#fill").show();
										$("#myform").show();
										$("#ptype").val(data.passenType);
										$("#ltype").val(data.passenLtype);
										$("#linsen").val(data.passenLinsen);
									}
								});
							}
							</script> -->
						<%
							int i = (int) request.getAttribute("psgnum");
							if (i < 6) {
						%>
						<div class="book-left-info">
							<br>
							<h4>添加新旅客信息</h4>
						</div>
						<form action="" method="post" onsubmit="return addpassenger(this)">
							<div class="book-left-form">
								<p>乘客类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									证件类型</p>
								<select id="adult" name="passenType">
									<option value="成年">成年</option>
									<option value="儿童">儿童</option>
								</select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<select id="card" name="passenLtype">
									<option value="二代居民身份证">二代居民身份证</option>
									<option value="护照">护照</option>
									<option value="港澳台通行证">港澳台通行证</option>
								</select>
								<p>姓名（与证件一致）</p>
								<input type="text" id="name" name="passenName">
								<p>证件号码</p>
								<input type="text" id="passport" name="passenLinsen">
								<p>
									<input type="submit" value="添加"
										style="text-transform: capitalize; background: #6fd508; color: #FFFFFF; padding: .5em 1em; border: none; font-size: 1em; outline: none;">
								</p>
								<br>
						</form>
						<%
							} else {
						%><div></div>
						<%
							}
						%>
						<script type="text/javascript">
							function addpassenger(form) {
								var url = "${pageContext.request.contextPath}/addpsg";
								$.post(url, $(form).serialize(),
										function(data) {
											if (data == 'ok') {
												window.location.reload();
											} else {
												alert("更新失败");
											}
										});
							}
						</script>
						<input type="button" onclick="batchAdd()" id="login" value="确认"
							style="text-transform: capitalize; background: #6fd508; color: #FFFFFF; padding: .5em 1em; border: none; font-size: 1em; outline: none;"></a>
					</div>
				</div>
				<div class="col-md-6 book-left book-right">
					<div class="book-left-info">
						<h3>请认真填写信息</h3>
					</div>
					<div class="book-left-bottom"></div>
					<ul>
						<li>访问即将到来的旅行预订记录</li>
						<li>打印门票和发票</li>
						<li>只输入一次您的联系方式</li>
						<li>只输入一次您的联系方式</li>
						<li>获取低票价的提醒</li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //container -->
	</div>
	<!-- //banner-bottom -->
	<!-- footer -->
	<div class="footer">
		<!-- container -->
		<div class="container">
			<div class="footer-top-grids">
				<div class="footer-grids">
					<div class="col-md-3 footer-grid">
						<h4>Our Products</h4>
						<ul>
							<li><a href="index.html">Flight Schedule</a></li>
							<li><a href="flights-hotels.html">City Airline Routes</a></li>
							<li><a href="index.html">International Flights</a></li>
							<li><a href="hotels.html">International Hotels</a></li>
							<li><a href="bus.html">Bus Booking</a></li>
							<li><a href="index.html">Domestic Airlines</a></li>
							<li><a href="holidays.html">Holidays Trip</a></li>
							<li><a href="hotels.html">Hotel Booking</a></li>
						</ul>
					</div>
					<div class="col-md-3 footer-grid">
						<h4>Company</h4>
						<ul>
							<li><a href="about.html">About Us</a></li>
							<li><a href="faqs.html">FAQs</a></li>
							<li><a href="terms.html">Terms &amp; Conditions</a></li>
							<li><a href="privacy.html">Privacy </a></li>
							<li><a href="contact.html">Contact Us</a></li>
							<li><a href="#">Careers</a></li>
							<li><a href="blog.html">Blog</a></li>
							<li><a href="booking.html">Feedback</a></li>
						</ul>
					</div>
					<div class="col-md-3 footer-grid">
						<h4>Travel Resources</h4>
						<ul>
							<li><a href="holidays.html">Holidays Packages</a></li>
							<li><a href="weekend.html">Weekend Getaways</a></li>
							<li><a href="index.html">International Airports</a></li>
							<li><a href="index.html">Domestic Flights Booking</a></li>
							<li><a href="booking.html">Customer Support</a></li>
							<li><a href="booking.html">Cancel Bookings</a></li>
							<li><a href="booking.html">Print E-tickets</a></li>
							<li><a href="booking.html">Customer Forums</a></li>
							<li><a href="booking.html">Make a Payment</a></li>
							<li><a href="booking.html">Complete Booking</a></li>
							<li><a href="booking.html">Assurance Claim</a></li>
							<li><a href="booking.html">Retail Offices</a></li>
						</ul>
					</div>
					<div class="col-md-3 footer-grid">
						<h4>More Links</h4>
						<ul class="chf_footer_list">
							<li><a href="#">Flights Discount Coupons</a></li>
							<li><a href="#">Domestic Airlines</a></li>
							<li><a href="#">Indigo Airlines</a></li>
							<li><a href="#">Air Asia</a></li>
							<li><a href="#">Jet Airways</a></li>
							<li><a href="#">SpiceJet</a></li>
							<li><a href="#">GoAir</a></li>
							<li><a href="#">Air India</a></li>
							<li><a href="#">Domestic Flight Routes</a></li>
							<li><a href="#">Indian City Flight</a></li>
							<li><a href="#">Flight Sitemap</a></li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
				<!-- news-letter -->
				<div class="news-letter">
					<div class="news-letter-grids">
						<div class="col-md-4 news-letter-grid">
							<p>
								Toll Free No : <span>1234-5678-901</span>
							</p>
						</div>
						<div class="col-md-4 news-letter-grid">
							<p class="mail">
								Email : <a href="mailto:info@example.com">mail@example.com</a>
							</p>
						</div>
						<div class="col-md-4 news-letter-grid">

							<input type="text" value="Email" onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Email';}"
								required=""> <input type="submit" value="Subscribe">

						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<!-- //news-letter -->
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- //footer -->
	<div class="footer-bottom-grids">
		<!-- container -->
		<div class="container">
			<div class="footer-bottom-top-grids">
				<div class="col-md-4 footer-bottom-left">
					<h4>Download our mobile Apps</h4>
					<div class="d-apps">
						<ul>
							<li><a href="#"><img src="images/app1.png" alt="" /></a></li>
							<li><a href="#"><img src="images/app2.png" alt="" /></a> </a></li>
							<li><a href="#"><img src="images/app3.png" alt="" /></a> </a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 footer-bottom-left">
					<h4>We Accept</h4>
					<div class="a-cards">
						<ul>
							<li><a href="#"><img src="images/c1.png" alt="" /></a></li>
							<li><a href="#"><img src="images/c2.png" alt="" /></a> </a></li>
							<li><a href="#"><img src="images/c3.png" alt="" /></a> </a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 footer-bottom-left">
					<h4>Follow Us</h4>
					<div class="social">
						<ul>
							<li><a href="#" class="facebook"> </a></li>
							<li><a href="#" class="facebook twitter"> </a></li>
							<li><a href="#" class="facebook chrome"> </a></li>
							<li><a href="#" class="facebook dribbble"> </a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="copyright">
					<p>
						Copyright &copy; 2015.Company name All rights reserved. <a
							target="_blank" href="http://www.cssmoban.com/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<script defer src="${pageContext.request.contextPath }/app/main/js/jquery.flexslider.js"></script>
	<script src="${pageContext.request.contextPath }/app/main/js/easyResponsiveTabs.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/app/main/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/app/main/js/script.js"></script>
</body>

</html>