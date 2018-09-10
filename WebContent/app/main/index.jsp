<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<title>首页</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Govihar Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<!-- //Custom Theme files -->
<link
	href="${pageContext.request.contextPath }/app/main/css/bootstrap.css"
	type="text/css" rel="stylesheet" media="all">
<link href="${pageContext.request.contextPath }/app/main/css/style.css"
	type="text/css" rel="stylesheet" media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/app/main/css/flexslider.css"
	type="text/css" media="screen" />
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
			type : 'default', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true
		// 100% fit in a container
		});
	});
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
						<a href="index.jsp"><span>Go</span>vihar</a>
					</h1>
				</div>
				<!--navbar-header-->
				<div class="header-dropdown">
					<div class="emergency-grid">
						<ul>
							<li>热线电话:</li>
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
												<span>
													<%
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
													新用户? <a href="${pageContext.request.contextPath }/app/main/signup.jsp">请注册</a>
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
	</div>
	</div>

	<!--//header-->
	<!-- banner -->
	<div class="banner">
		<!-- container -->
		<div class="container">
			<div class="col-md-4 banner-left">
				<section class="slider2">
					<div class="flexslider">
						<ul class="slides">
							<li>
								<div class="slider-info">
									<img
										src="${pageContext.request.contextPath }/app/main/images/1.jpg"
										alt="">
								</div>
							</li>
							<li>
								<div class="slider-info">
									<img
										src="${pageContext.request.contextPath }/app/main/images/2.jpg"
										alt="">
								</div>
							</li>
							<li>
								<div class="slider-info">
									<img
										src="${pageContext.request.contextPath }/app/main/images/3.jpg"
										alt="">
								</div>
							</li>
							<li>
								<div class="slider-info">
									<img
										src="${pageContext.request.contextPath }/app/main/images/4.jpg"
										alt="">
								</div>
							</li>
							<li>
								<div class="slider-info">
									<img
										src="${pageContext.request.contextPath }/app/main/images/2.jpg"
										alt="">
								</div>
							</li>
						</ul>
					</div>
				</section>
				<!--FlexSlider-->
			</div>
			<div class="copyrights">
				Collect from <a href="http://www.cssmoban.com/">网页模板</a>
			</div>
			<div class="col-md-8 banner-right">
				<div class="sap_tabs">
					<div class="booking-info">
						<h2>预订国内和国际机票</h2>
					</div>
					<div id="horizontalTab"
						style="display: block; width: 100%; margin: 0px;">
						<ul class="resp-tabs-list">
							<li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>往返机票</span></li>
							<li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>单程机票</span></li>
							<div class="clearfix"></div>
						</ul>
						<!---->
						<script type="text/javascript">
						</script>
						<div class="resp-tabs-container">
							<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
								<div class="facts">
									<div class="booking-form">
										<!---strat-date-piker---->
										<script>
											$(function() {
												$("#datepicker,#datepicker1")
														.datepicker();
											});
										</script>
										<!---/End-date-piker---->
										<!-- Set here the key for your domain in order to hide the watermark on the web server -->

										<div class="online_reservation">
											<div class="b_room">
												<div class="booking_room">
													<div class="reservation">
														<form action="${pageContext.request.contextPath }/trip" method="post">
															<ul>
																<li class="span1_of_1 desti">
																	<h5>出发地</h5>
																	<div class="book_date">
																		<span class="glyphicon glyphicon-map-marker"
																			aria-hidden="true"></span> <input type="text"
																			placeholder="选择出发城市" required=""
																			class="typeahead1 input-md form-control tt-input"
																			 name="departure">

																	</div>
																</li>
																<li class="span1_of_1 left desti">
																	<h5>目的地</h5>
																	<div class="book_date">

																		<span class="glyphicon glyphicon-map-marker"
																			aria-hidden="true"></span> <input type="text"
																			placeholder="选择到达城市" required=""
																			class="typeahead1 input-md form-control tt-input"
																			name="destination">

																	</div>
																</li>
																<div class="clearfix"></div>
															</ul>
													</div>
													<div class="reservation">
														<ul>
															<li class="span1_of_1">
																<h5>出发时间</h5>
																<div class="book_date">

																	<span class="glyphicon glyphicon-calendar"
																		aria-hidden="true"></span> <input type="date" required=""
																		value="Select date" onFocus="this.value = '';"
																		onBlur="if (this.value == '') {this.value = 'Select date';}"
																		 name="deptime">

																</div>
															</li>
															<li class="span1_of_1 left">
																<h5>返程时间</h5>
																<div class="book_date">

																	<span class="glyphicon glyphicon-calendar"
																		aria-hidden="true"></span> <input type="date" required=""
																		value="Select date" onFocus="this.value = '';"
																		onBlur="if (this.value == '') {this.value = 'Select date';}"
																		 name="destime">

																</div>
															</li>
															<li class="span1_of_1 left adult">
																<h5>成年人(18+)</h5> <!----------start section_room----------->
																<div class="section_room">
																	<select id="adult"
																		onChange="change_country(this.value)"
																		class="frm-field required">
																		<option value="1">1</option>
																		<option value="2">2</option>
																		<option value="3">3</option>
																		<option value="4">4</option>
																		<option value="5">5</option>
																		<option value="6">6</option>
																	</select>
																</div>
															</li>
															<li class="span1_of_1 left children">
																<h5>儿童 (0-17)</h5> <!----------start section_room----------->
																<div class="section_room">
																	<select id="kid"
																		onChange="change_country(this.value)"
																		class="frm-field required">
																		<option value="1">1</option>
																		<option value="2">2</option>
																		<option value="3">3</option>
																		<option value="4">4</option>
																		<option value="5">5</option>
																		<option value="6">6</option>
																	</select>
																</div>
															</li>
															<li class="span1_of_1 economy">
																<h5>&nbsp;座位</h5> <!----------start section_room----------->
																<div class="section_room">
																	<select id="seat"
																		onChange="change_country(this.value)"
																		class="frm-field required">
																		<option value="头等舱">头等舱</option>
																		<option value="商务舱">商务舱</option>
																		<option value="经济舱">经济舱</option>
																	</select>
																</div>
															</li>
															<div class="clearfix"></div>
														</ul>
													</div>
													<div class="reservation">
														<ul>
															<li class="span1_of_3">

																<div class="date_btn">
																	<input type="submit"
																		style="text-transform: capitalize; background: #6fd508; color: #FFFFFF; padding: .5em 1em; border: none; font-size: 1em; outline: none;"
																		value="查询" />
																	
																</div>
															</li>
															<div class="clearfix"></div>
														</ul>
														</ul>
													</div>
												</div>
											
												<div class="clearfix"></div>
											</div>
										</div>
										<!---->
					</form>
									</div>
								</div>
							</div>
							<div class="tab-2 resp-tab-content" aria-labelledby="tab_item-1">
								<div class="facts">
									<div class="booking-form">
										<!---strat-date-piker---->
										<link rel="stylesheet" href="css/jquery-ui.css" />
										<script
											src="${pageContext.request.contextPath }/app/main/js/jquery-ui.js"></script>
										<script>
											$(function() {
												$("#datepicker,#datepicker1")
														.datepicker();
											});
										</script>

										<!---/End-date-piker---->
										<div class="online_reservation">
											<div class="b_room">
												<div class="booking_room">
													<div class="reservation">
													<form action="${pageContext.request.contextPath }/trip">
														<ul>
															<li class="span1_of_1 desti">
																<h5>从</h5>
																<div class="book_date">
																	
																		<span class="glyphicon glyphicon-map-marker"
																			aria-hidden="true"></span> <input type="text"
																			placeholder="Type Departure City"
																			class="typeahead1 input-md form-control tt-input"
																			required=""
																			name="departure">
																	
																</div>
															</li>
															<li class="span1_of_1 left desti">
																<h5>到</h5>
																<div class="book_date">
																
																		<span class="glyphicon glyphicon-map-marker"
																			aria-hidden="true"></span> <input type="text"
																			placeholder="Type Destination City" name="destination"
																			class="typeahead1 input-md form-control tt-input"
																			required="">
																	
																</div>
															</li>
															<div class="clearfix"></div>
														</ul>
													</div>
													<div class="reservation">
														<ul>
															<li class="span1_of_1">
																<h5>出发时间</h5>
																<div class="book_date">

																	<span class="glyphicon glyphicon-calendar"
																		aria-hidden="true"></span> <input type="date" required=""
																		value="Select date" onFocus="this.value = '';"
																		onBlur="if (this.value == '') {this.value = 'Select date';}"
																		 name="destime">

																</div>
															</li>
															<li class="span1_of_1 left">
																<h5>成年(18+)</h5> <!----------start section_room----------->
																<div class="section_room">
																	<select id="adult"
																		onChange="change_country(this.value)"
																		class="frm-field required">
																		<option value="1">1</option>
																		<option value="2">2</option>
																		<option value="3">3</option>
																		<option value="4">4</option>
																		<option value="5">5</option>
																		<option value="6">6</option>
																	</select>
																</div>
															</li>
															<li class="span1_of_1 left tab-children">
																<h5>儿童 (0-17)</h5> <!----------start section_room----------->
																<div class="section_room">
																	<select id="kid"
																		onChange="change_country(this.value)"
																		class="frm-field required">
																		<option value="1">1</option>
																		<option value="2">2</option>
																		<option value="3">3</option>
																		<option value="4">4</option>
																		<option value="5">5</option>
																		<option value="6">6</option>
																		</select>
																</div>
															</li>
															<li class="span1_of_1 economy">
																<h5>座位</h5> <!----------start section_room----------->
																<div class="section_room">
																	<select id="seat"
																		onChange="change_country(this.value)"
																		class="frm-field required">
																		<option value="头等舱">头等舱</option>
																		<option value="商务舱">商务舱</option>
																		<option value="经济舱">经济舱</option>
																	</select>
																</div>
															</li>
															<div class="clearfix"></div>
														</ul>
													</div>
													<div class="reservation">
														<ul>
															<li class="span1_of_3">
																<div class="date_btn">
																	
																	<input type="submit"
																		style="text-transform: capitalize; background: #6fd508; color: #FFFFFF; padding: .5em 1em; border: none; font-size: 1em; outline: none;"
																		value="查询" />
																	</form>
																</div>
															</li>
															<div class="clearfix"></div>
														</ul>
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
										<!---->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<!-- //container -->
	</div>
	<!-- //banner -->
	<div class="move-text">
		<div class="marquee">
			点击查看更多信息<a href="#">Here</a>
		</div>
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/app/main/js/jquery.marquee.min.js"></script>
		<script>
			$('.marquee').marquee({
				pauseOnHover : true
			});
			//@ sourceURL=pen.js
		</script>
	</div>
	<!-- banner-bottom -->

	<script type="text/javascript">
		$(window).load(function() {
			$("#flexiselDemo1").flexisel({
				visibleItems : 4,
				animationSpeed : 1000,
				autoPlay : true,
				autoPlaySpeed : 3000,
				pauseOnHover : true,
				enableResponsiveBreakpoints : true,
				responsiveBreakpoints : {
					portrait : {
						changePoint : 480,
						visibleItems : 1
					},
					landscape : {
						changePoint : 640,
						visibleItems : 2
					},
					tablet : {
						changePoint : 768,
						visibleItems : 3
					}
				}
			});
		});
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/app/main/js/jquery.flexisel.js"></script>
	</div>
	</div>
	<!-- //slider -->
	</div>
	<!-- //container -->
	</div>
	<!-- popular-grids -->
	<!-- footer -->
	<div class="footer">
		<!-- container -->
		<div class="container">
			<div class="footer-top-grids">
				<div class="footer-grids">
					<div class="col-md-3 footer-grid">
						<h4>Our Products</h4>
						<ul>
							<li><a href="index.jsp">Flight Schedule</a></li>
							<li><a href="flights-hotels.jsp">City Airline Routes</a></li>
							<li><a href="index.jsp">International Flights</a></li>
							<li><a href="hotels.jsp">International Hotels</a></li>
							<li><a href="bus.jsp">Bus Booking</a></li>
							<li><a href="index.jsp">Domestic Airlines</a></li>
							<li><a href="holidays.jsp">Holidays Trip</a></li>
							<li><a href="hotels.jsp">Hotel Booking</a></li>
						</ul>
					</div>
					<div class="col-md-3 footer-grid">
						<h4>Company</h4>
						<ul>
							<li><a href="about.jsp">About Us</a></li>
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
							<form>
								<input type="text" value="Email" onFocus="this.value = '';"
									onBlur="if (this.value == '') {this.value = 'Email';}"
									required=""> <input type="submit" value="Subscribe">
							</form>
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
							<li><a href="#"><img
									src="${pageContext.request.contextPath }/app/main/images/app1.png"
									alt="" /></a></li>
							<li><a href="#"><img
									src="${pageContext.request.contextPath }/app/main/images/app2.png"
									alt="" /></a></a></li>
							<li><a href="#"><img
									src="${pageContext.request.contextPath }/app/main/images/app3.png"
									alt="" /></a></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 footer-bottom-left">
					<h4>We Accept</h4>
					<div class="a-cards">
						<ul>
							<li><a href="#"><img
									src="${pageContext.request.contextPath }/app/main/images/c1.png"
									alt="" /></a></li>
							<li><a href="#"><img
									src="${pageContext.request.contextPath }/app/main/images/c2.png"
									alt="" /></a></a></li>
							<li><a href="#"><img
									src="${pageContext.request.contextPath }/app/main/images/c3.png"
									alt="" /></a></a></li>
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
						Copyright &copy; 2015.Company name All rights reserved.<a
							target="_blank" href="http://www.cssmoban.com/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<script defer
		src="${pageContext.request.contextPath }/app/main/js/jquery.flexslider.js"></script>
	<script
		src="${pageContext.request.contextPath }/app/main/js/easyResponsiveTabs.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath }/app/main/js/jquery-ui.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/app/main/js/script.js"></script>
	<script type="text/javascript">
		$(function() {
			SyntaxHighlighter.all();
		});
		$(window).load(function() {
			$('.flexslider').flexslider({
				animation : "slide",
				start : function(slider) {
					$('body').removeClass('loading');
				}
			});
		});
	</script>
</body>
</html>