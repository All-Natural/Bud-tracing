<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js" lang="zxx">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 登录</title>
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
		<style type="text/css">
		    .code
		    {
		         font-family:Arial;
		         font-style:italic;
		         color:blue;
		         font-size:30px;
		         border:0;
		         padding:2px 3px;
		         letter-spacing:3px;
		         font-weight:bolder;            
		         float:left;           
		         cursor:pointer;
		         width:150px;
		         height:50px;
		         line-height:60px;
		         text-align:center;
		         vertical-align:middle;
		         background-color:#D8B7E3;
		     }
		     span {
		        text-decoration:none;
		        font-size:12px;
		        color:#288bc4;
		        padding-left:10px;
		    }
		
		    span:hover {
		        text-decoration:underline;
		        cursor:pointer;
		    }
   
	</style>
	<script type="text/javascript">
	    //页面加载时，生成随机验证码
	    window.onload=function(){
	     createCode(4);    
	    }
	
	    //生成验证码的方法
	    function createCode(length) {
	        var code = "";
	        var codeLength = parseInt(length); //验证码的长度
	        var checkCode = document.getElementById("checkCode");
	        ////所有候选组成验证码的字符，当然也可以用中文的
	        var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
	        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
	        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); 
	        //循环组成验证码的字符串
	        for (var i = 0; i < codeLength; i++)
	        {
	            //获取随机验证码下标
	            var charNum = Math.floor(Math.random() * 62);
	            //组合成指定字符验证码
	            code += codeChars[charNum];
	        }
	        if (checkCode)
	        {
	            //为验证码区域添加样式名
	            checkCode.className = "code";
	            //将生成验证码赋值到显示区
	            checkCode.innerHTML = code;
	        }
	    }
	    
	    //检查验证码是否正确
	    function validateCode()
	    {
	        //获取显示区生成的验证码
	        var checkCode = document.getElementById("checkCode").innerHTML;
	        //获取输入的验证码
	        var inputCode = document.getElementById("inputCode").value;
	        console.log(checkCode);
	        console.log(inputCode);
	        if (inputCode.length <= 0)
	        {
	            alert("请输入验证码！");
	        }
	        else if (inputCode.toUpperCase() != checkCode.toUpperCase())
	        {
	            alert("验证码输入有误！");
	            createCode(4);
	        }
	        else
	        {
	           var currForm = document.getElementById("login");
	           currForm.action = "login.htm";
	           currForm.submit();
	        }       
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
												<li><a href="login.jsp" class="header-btn">登录</a></li>
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
									<li class="breadcrumb-item"><a href="login.jsp">登录</a></li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!-- breadcrumb End -->
			<!-- login Area Start -->
			<div class="login-form-area">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-7 col-md-10">
							<div class="login-form">
								<!-- Login Heading -->
								<div class="login-heading">

									<span>登录</span>
									<p>输入登录详细信息以获取访问权限</p>
								</div>
								<!-- Single Input Fields -->
								<form action="login.htm" method="post" id="login">
									<div class="input-box">

										<div class="single-input-fields">
											<label>手机号</label>
											<input type="text" name="uphone" placeholder="phoneNumber">
										</div>
										<div class="single-input-fields">
											<label>密码</label>
											<input type="password" name="upassword" placeholder="Enter Password">
										</div>
										<p style="text-color:red">${msg}</p>
										<div class="single-input-fields login-check">
											<input type="checkbox" id="fruit1" name="keep-log">
											<label for="fruit1">记住</label>
										</div>
										<div class="single-input-fields">
											<label>验证码</label>
											<div  id="checkCode" class="code"  onclick="createCode(4)" ></div>
									        <div>   
									            <input type="text" id="inputCode"  style="float:left;width:310px;" />
									        </div>
										</div>
									</div>
								 		 
									<!-- form Footer -->
									<div class="login-footer">
										<p>没有账号? <a href="register.jsp">注册</a> here<a href="index.html"> 游客登录</a></p>
										<a herf="#" onclick="validateCode()" class="btn">登录</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- login Area End -->
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
										<li><a href="#">首页</a></li>
										<li><a href="#"> 设备列表</a></li>
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
