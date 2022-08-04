<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%String path1=request.getContextPath(); %>
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
<%boolean path=false; %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 个人中心</title>
		<style type="text/css">
			iframe {
				background-color: white;
				width: 970px;
				height: 900px;
				border-left: 1px outset white;
				border-top: 1px solid white;
				border-right: 1px solid white;

			}
		</style>
		<!--meta tags -->
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
		<script type="text/javascript">
			let onEdit = function(veid) {
				let currForm = document.getElementById("myform");
				currForm.action = "show_post.htm?postid=" + veid;
				currForm.submit();
			}
		</script>
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
									<li class="breadcrumb-item"><a href="#">个人中心</a></li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
			</div>

			<section class="tranding-area ">
				<div class="container">
					<div class="page-header" style="border-bottom: 2px groove lightgrey;">
						<h1>个人中心</h1>
					</div>
					<div class="row" style="border-bottom: 1px groove lightgrey;">
						<div class="col-md-2">
							<ul id="navigation">
								<%
          //1.获取session存储的用户菜单
          List<Map<String,String>> menuList=(List)session.getAttribute("userMenuList");
          //判断menuList是否存在
          if(menuList!=null)
          {
        	  //遍历菜单
        	  for(Map<String,String>  menu:menuList)
        	  {
        		 %>
								<li style="padding-top:10px;text-align:center;"><a href="<%= menu.get("openurl") %>"
										style="width:168px;height:48;background-color: skyblue;color:white;padding:24px;"
										target="views" class="btn1"><%= menu.get("menuname")%></a></li>

								<%
        	  }
          }
       %>
							</ul>
						</div>
						<div class="col-md-10">
							<iframe name="views"> </iframe>
						</div>
					</div>
				</div>
			</section>
			<div style="height: 200px;">
			</div>
			<div style=" text-align: center;">
				<img style="width: 1300px;height: 370px" src="image/new.jpg">

			</div>

			<div style="height: 200px;">
			</div>
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
