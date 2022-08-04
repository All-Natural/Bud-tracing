<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@page import="java.util.Map"%>
<%
	String Logtxt="";
	String toPath="";
	Map<String,String> userinfo=(Map<String, String>)request.getSession().getAttribute("userinfo");
	if(userinfo!=null) {
		Logtxt = userinfo.get("uname");
		toPath = "mycenter.jsp";
		}
		else {
			Logtxt = "登录";
			toPath = "login.jsp";
		};
%>
<%String path = request.getParameter("path"); %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 发布找寻通知</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="keywords"
			content="Oblige Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	         Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="manifest" href="site.webmanifest">
		<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

		<!-- CSS here -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
		<link rel="stylesheet" href="assets/css/slicknav.css">
		<link rel="stylesheet" href="assets/css/lightslider.min.css">
		<link rel="stylesheet" href="assets/css/animate.min.css">
		<link rel="stylesheet" href="assets/css/magnific-popup.css">
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" href="assets/css/themify-icons.css">
		<link rel="stylesheet" href="assets/css/slick.css">
		<link rel="stylesheet" href="assets/css/nice-select.css">
		<link rel="stylesheet" href="assets/css/style.css">
	</head>
	<body>
		<header>
			<div class="header-area">
				<div class="main-header ">
					<div class="header-top ">
						<div class="container">
							<div class="row">
								<div class="col-xl-12">
									<div class="d-flex justify-content-between align-items-center">
										<div class="header-info-left ">
											<!-- Social -->
											<div class="header-social d-none d-sm-block">
												<a href="https://bit.ly/sai4ull"><i class="fab fa-facebook-f"></i></a>
												<a href="#"><i class="fab fa-instagram"></i></a>
												<a href="#"><i class="fab fa-twitter"></i></a>
												<a href="#"><i class="fab fa-linkedin-in"></i></a>
												<a href="#"><i class="fab fa-tumblr"></i></a>
											</div>
										</div>
										<div class="header-info-mid">
											<!-- logo -->
											<div class="logo">
												<a href="index.html"><img src="assets/img/logo/logo.png" alt=""></a>
											</div>
										</div>
										<div class="header-info-right d-flex align-items-center">
											<ul>
												<li>
													<form class="search-form" action="#">
														<i class="fas fa-search"></i>
														<input type="search" class="form-control"
															placeholder="Search Here" title="Search here">
													</form>
												</li>
												<li><a href="index.html">首页</a>
													<ul class="multipage">
														<!-- <li><a href="about.html">关于我们</a></li> -->
														<li><a href="about.html">团队使命</a></li>
														<!-- <li><a href="category.html">最新资讯</a></li> -->
														<li><a href="post_details.html">资讯详情</a></li>
														<!-- <li><a href="blog_details.html">平台守护者</a></li> -->
														<li><a href="blog_details.html">活动风采</a></li>
														<li><a href="contact.html">联系我们</a></li>
													</ul>
												</li>
												<li><a href="<%=toPath %>" class="header-btn"><%=Logtxt%></a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="header-bottom  header-sticky">
						<div class="container">
							<div class="row align-items-center">
								<div class="col-xl-12">
									<!-- logo 2 -->
									<div class="logo2">
										<a href="index.html"><img src="assets/img/logo/logo.png" alt=""></a>
									</div>
									<!-- logo 3 -->
									<div class="logo3 d-block d-sm-none">
										<a href="index.html"><img src="assets/img/logo/logo-mobile.png" alt=""></a>
									</div>
									<!-- Main-menu -->
									<div class="main-menu text-center d-none d-lg-block">
										<nav>
											<ul id="navigation">
												<li><a href="index.html">首 页</a></li>
												<li> </li>
												<li><a href="show_dev.htm">设 备 列 表</a></li>
												<li> </li>
												<li><a href="show_com_post_list.htm">花 蕾 社 区</a></li>
												<li> </li>
												<li><a href="message_list.html">消 息</a></li>
												<li> </li>
												<li><a href="mycenter.jsp">个 人 中 心</a></li>
											</ul>
										</nav>
									</div>
								</div>
								<!-- Mobile Menu -->
								<div class="col-xl-12">
									<div class="mobile_menu d-block d-lg-none"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>
		<main>
			<!-- breadcrumb Start-->
			<div class="page-notification">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb justify-content-center">
									<li class="breadcrumb-item"><a href="index.html">首页</a></li>
									<li class="breadcrumb-item"><a href="show_com_post_list.htm">花蕾社区</a></li>
									<li class="breadcrumb-item"><a href="#">发布找寻通知</a></li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<div class="register-form-area">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-7 col-md-10">
							<div class="register-form text-center">
								<!-- Login Heading -->
								<div class="register-heading">
									<span>发布找寻通知</span>
									<p>审核通过后，整个花蕾社区均可见</p>
								</div>
								<form action="issue_post.htm" method="post">
									<!-- Single Input Fields -->
									<div class="input-box">
										<div class="single-input-fields">
											<label>找寻通知内容</label>
											<textarea class="form-control" cols="20" rows="5" placeholder="找寻通知具体内容描述"
												wrap="hard" id="story" name="postcontent" required></textarea>
										</div>
										<div class="single-input-fields">
											<label class="control-label">找寻状态</label>
											<select name="poststatus">
												<option value="2">未找到</option>
												<option value="1">已找到</option>
											</select>
										</div>
										<div class="single-input-fields">
											<label>丢失发生时间</label>
											<input type="datetime-local" class="form-control" name="posttimehap" required>
										</div>
										<div class="single-input-fields">
											<label>手机号</label>
											<input type="text" class="form-control" id="Phone" name="postrelaphone"
												placeholder="请留下一个联系方式" data-error="请输入您的手机号" required>
										</div>
										<div class="single-input-fields">
											<label>联系人姓名</label>
											<input type="text" class="form-control" id="name" name="postrelaname"
												placeholder="请留下一位联系人姓名" data-error="请输联系人姓名" required>
										</div>

										<div class="single-input-fields">
											<label>备注</label>
											<textarea class="form-control" cols="20" rows="5" placeholder="描述部分补充"
												wrap="hard" id="story" name="postmemo"></textarea>
										</div>
									</div>
									<div style="font-family:arial;color:black;font-size:20px;text-align:center">${msg }
									</div>
									<c:choose>
										<c:when test="${flag!=1 }">
											<div class="register-footer">
												<button type="submit" class="btn">提交</button>
											</div>
										</c:when>
									</c:choose>
								</form>
								<c:choose>
									<c:when test="${flag==1 }">
										<div>
											<p align="center">
											 	 <iframe src="upload.jsp?postid=${key }&type=0" width="400px" height="245px"
														style="border:3px solid grey;">
												</iframe>
											</p>
										</div>	
									</c:when>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>							
			<section class="technology-area  section-padding">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="section-tittle mb-30 d-flex align-items-center justify-content-between">
								<h2>平台活动</h2>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xl-3 col-lg-3">
							<div class="row">
								<div class="col-lg-12 col-md-6 col-sm-6">
									<div class="technology-post mb-30">
										<div class="technology-wrapper">
											<div class="properties-img">
												<a href="post_details.html"><img
														src="assets/img/gallery/technology1.jpg" alt=""></a>
											</div>
											<div class="properties-caption">
												<h3><a href="post_details.html">快乐亲子时光</a></h3>
												<p>2021-06-1 <a href="#">lwh</a></p>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-6 col-sm-6">
									<div class="technology-post mb-30">
										<div class="technology-wrapper">
											<div class="properties-img">
												<a href="post_details.html"><img
														src="assets/img/gallery/technology2.jpg" alt=""></a>
											</div>
											<div class="properties-caption">
												<h3><a href="post_details.html">海边休闲亲子时光</a></h3>
												<p>2021-05-30 <a href="#">xzk</a></p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-6 col-lg-6">
							<div class="video-slider-area mb-30">
								<div id="vertical">
									<div class="single-slider" data-thumb="assets/img/gallery/news-slider1.jpg">
										<img src="assets/img/gallery/news-slider1.jpg" alt="slider-img" class="w-100">
										<h3><a href="post_details.html">海边创意大赛</a></h3>
										<a class="popup-video"
											href="https://www.bilibili.com/video/BV1S7411d7Bo?from=search&seid=8633425938997859733"><i
												class="far fa-play-circle"></i></a>
									</div>
									<div class="single-slider" data-thumb="assets/img/gallery/news-slider2.jpg">
										<img src="assets/img/gallery/news-slider2.jpg" alt="slider-img" class="w-100">
										<h3><a href="post_details.html">海边创意大赛</a></h3>
										<a class="popup-video"
											href="https://www.bilibili.com/video/BV1S7411d7Bo?from=search&seid=8633425938997859733"><i
												class="far fa-play-circle"></i></a>
									</div>
									<div class="single-slider" data-thumb="assets/img/gallery/news-slider3.jpg">
										<img src="assets/img/gallery/news-slider3.jpg" alt="slider-img" class="w-100">
										<h3><a href="post_details.html">海边创意大赛</a></h3>
										<a class="popup-video"
											href="https://www.bilibili.com/video/BV1S7411d7Bo?from=search&seid=8633425938997859733"><i
												class="far fa-play-circle"></i></a>
									</div>
									<div class="single-slider" data-thumb="assets/img/gallery/news-slider2.jpg">
										<img src="assets/img/gallery/news-slider2.jpg" alt="slider-img" class="w-100">
										<h3><a href="post_details.html">海边创意大赛</a></h3>
										<a class="popup-video"
											href="https://www.bilibili.com/video/BV1S7411d7Bo?from=search&seid=8633425938997859733"><i
												class="far fa-play-circle"></i></a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-3 col-lg-3">
							<div class="row">
								<div class="col-lg-12 col-md-6 col-sm-6">
									<div class="technology-post mb-30">
										<div class="technology-wrapper">
											<div class="properties-img">
												<a href="post_details.html"><img
														src="assets/img/gallery/technology3.jpg" alt=""></a>
											</div>
											<div class="properties-caption">
												<h3><a href="post_details.html">绘画大赛</a>
												</h3>
												<p>2021-05-20 <a href="#">hwj</a></p>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-6 col-sm-6">
									<div class="technology-post mb-30">
										<div class="technology-wrapper">
											<div class="properties-img">
												<a href="post_details.html"><img
														src="assets/img/gallery/technology4.jpg" alt=""></a>
											</div>
											<div class="properties-caption">
												<h3><a href="post_details.html">积木大赛</a>
												</h3>
												<p>2021-04-10 <a href="#">lht</a></p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- Technology Area End -->
			<!--? instagram-social start -->
			<div class="instagram-area fix">
				<div class="container-fluid p-0">
					<div class="row">
						<div class="col-xl-12">
							<div class="instagram-active owl-carousel">
								<div class="single-instagram">
									<img src="assets/img/gallery/instra1.jpg" alt="">
									<a href="#"><i class="ti-instagram"></i></a>
								</div>
								<div class="single-instagram">
									<img src="assets/img/gallery/instra2.jpg" alt="">
									<a href="#"><i class="ti-instagram"></i></a>
								</div>
								<div class="single-instagram">
									<img src="assets/img/gallery/instra3.jpg" alt="">
									<a href="#"><i class="ti-instagram"></i></a>
								</div>
								<div class="single-instagram">
									<img src="assets/img/gallery/instra4.jpg" alt="">
									<a href="#"><i class="ti-instagram"></i></a>
								</div>
								<div class="single-instagram">
									<img src="assets/img/gallery/instra5.jpg" alt="">
									<a href="#"><i class="ti-instagram"></i></a>
								</div>
								<div class="single-instagram">
									<img src="assets/img/gallery/instra6.jpg" alt="">
									<a href="#"><i class="ti-instagram"></i></a>
								</div>
								<div class="single-instagram">
									<img src="assets/img/gallery/instra3.jpg" alt="">
									<a href="#"><i class="ti-instagram"></i></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- instagram-social End -->
		</main>
		<footer>
			<div class="footer-area footer-padding">
				<div class="container">
					<div class="row d-flex justify-content-between">
						<div class="col-xl-4 col-lg-4 col-md-6 col-sm-6">
							<div class="single-footer-caption mb-50">
								<div class="single-footer-caption mb-30">
									<!-- logo -->
									<div class="footer-logo mb-25">
										<a href="index.html"><img src="assets/img/logo/logo2_footer.png" alt=""></a>
									</div>
									<div class="footer-tittle">
										<div class="footer-pera">
											<p align="left">
												花蕾寻迹从行之有效的角度全面改善现今社会少年儿童的安全隐患问题，为全国每一个孩子提供最为完善的安全防护系统服务，发动社会全员参与其中。</p>
										</div>
									</div>
									<div class="footer-social">
										<a href="https://bit.ly/sai4ull"><i class="fab fa-facebook-f"></i></a>
										<a href="#"><i class="fab fa-instagram"></i></a>
										<a href="#"><i class="fab fa-twitter"></i></a>
										<a href="#"><i class="fab fa-linkedin-in"></i></a>
										<a href="#"><i class="fab fa-tumblr"></i></a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-5">
							<div class="single-footer-caption mb-50">
								<div class="footer-tittle">
									<h4>相关链接</h4>
									<ul>
										<li><a href="about.html">团队使命</a></li>
										<!-- <li><a href="category.html">最新资讯</a></li> -->
										<li><a href="post_details.html">资讯详情</a></li>
										<!-- <li><a href="blog_details.html">平台守护者</a></li> -->
										<li><a href="blog.html">活动风采</a></li>
										<li><a href="contact.html">联系我们</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
							<div class="single-footer-caption mb-50">
								<div class="footer-tittle">
									<h4>菜单</h4>
									<ul>
										<li><a href="index.html">首页</a></li>
										<li><a href="device_list.html"> 设备列表</a></li>
										<li><a href="#">花蕾社区</a></li>
										<li><a href="#">消息</a></li>
										<li><a href="#">个人中心</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
							<div class="single-footer-caption mb-50">
								<div class="footer-tittle mb-50">
									<h4>联系我们</h4>
									<p>加入我们、提供建议，为人间铸就出更多大善，大爱之人！</p>
								</div>
								<!-- Form -->
								<div class="footer-form">
									<div id="mc_embed_signup">
										<form target="_blank"
											action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
											method="get" class="subscribe_form relative mail_part" novalidate="true">
											<input type="email" name="EMAIL" id="newsletter-form-email"
												placeholder="  请输入你的邮箱">
											<div class="form-icon">
												<button type="submit" name="submit" id="newsletter-submit"
													class="email_icon newsletter-submit button-contactForm">

													提交
												</button>
											</div>
											<div class="mt-10 info"></div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- footer-bottom area -->
			<div class="footer-bottom-area">
				<div class="container">
					<div class="footer-border">
						<div class="row d-flex align-items-center">
							<div class="col-xl-12 ">
								<div class="footer-copy-right text-center">
									<p>版权 &copy;<script>
											document.write(new Date().getFullYear());
										</script> All rights reserved | Designed by 你说的队
									<p><a href="https://beian.miit.gov.cn" target="view_window"> 蜀ICP备20013451号</a></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
		<!-- Scroll Up -->
		<div id="back-top">
			<a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
		</div>

		<!-- JS here -->
		<!-- Jquery, Popper, Bootstrap -->
		<script src="./assets/js/vendor/modernizr-3.5.0.min.js"></script>
		<script src="./assets/js/vendor/jquery-1.12.4.min.js"></script>
		<script src="./assets/js/popper.min.js"></script>
		<script src="./assets/js/bootstrap.min.js"></script>

		<!-- Slick , Owl-Carousel , lightslider , Mobile Menu-->
		<script src="./assets/js/owl.carousel.min.js"></script>
		<script src="./assets/js/slick.min.js"></script>
		<script src="./assets/js/lightslider.min.js"></script>
		<script src="./assets/js/jquery.slicknav.min.js"></script>

		<!-- wow, popup, Nice-select  -->
		<script src="./assets/js/wow.min.js"></script>
		<script src="./assets/js/jquery.magnific-popup.js"></script>
		<script src="./assets/js/jquery.nice-select.min.js"></script>

		<!-- contact js -->
		<script src="./assets/js/contact.js"></script>
		<script src="./assets/js/jquery.form.js"></script>
		<script src="./assets/js/jquery.validate.min.js"></script>
		<script src="./assets/js/mail-script.js"></script>
		<script src="./assets/js/jquery.ajaxchimp.min.js"></script>

		<!-- Jquery Plugins, main Jquery -->
		<script src="./assets/js/plugins.js"></script>
		<script src="./assets/js/main.js"></script>
	</body>

</html>
