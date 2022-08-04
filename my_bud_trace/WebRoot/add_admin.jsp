<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<head>
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
  <style type="text/css">
	body{
		background: url("img/wp2757874.gif") no-repeat center center fixed;
                /*兼容浏览器版本*/
                -webkit-background-size: cover;
                -o-background-size: cover;                
                background-size: cover;   
	}
	select{
	border: none;
	outline: none;
	width: 100%;
	text-align: center;
	height: 100%;
	}
	.seloption{
	height: 25px;
	width: 95px;
	margin: 0px;
	border: 1px solid #cccccc;
    float: right;
	}
</style>
<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 增加审核员</title>
</head>
<body>
<div id="msg" class="msg"></div>
${msg }
<<div class="register-form-area">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-7 col-md-10">
							<div class="register-form text-center">
								<!-- Login Heading -->
								<div class="register-heading">
									<span>增加审核员</span>
									<p>选择适合的人，负责审核</p>
								</div>
								<form action="register.htm" method="post">
								<!-- Single Input Fields -->
								<div class="input-box">
									<div class="single-input-fields">
										<label>用户名</label>
										<input type="text" class="form-control" id="name" name="uname"
											placeholder="username" data-error="请输入您的用户名" required>
									</div>
									<div class="single-input-fields">
										<label>姓名</label>
										<input type="text" class="form-control" id="name" name="urealname"
											placeholder="name" data-error="请输入您的真实姓名" required>
									</div>
									<div class="single-input-fields">
										<label>邮箱</label>
										<input type="email" class="form-control" id="inputEmail" name="uemail"
											placeholder="Email" data-error="请输入您的正确邮箱" required>
									</div>
									<div class="single-input-fields">
										<label>手机号</label>
										<input type="text" class="form-control" id="Phone" name="uphone"
											placeholder="Phone" data-error="请输入您的手机号" required>
									</div>
									<div class="single-input-fields">
										<label>家庭住址</label>
										<input type="text" class="form-control" id="name" name="uaddress"
											placeholder="Address" data-error="请输入您的家庭地址" required>
									</div>
									<div class="single-input-fields">
										<label>密码</label>
										<input type="password" name="upassword" data-minlength="6" class="form-control"
											id="inputPassword" placeholder="Password" required>
									</div>
									<div class="single-input-fields">
										<label>确认密码</label>
										<input type="password" class="form-control" id="inputPasswordConfirm"
											data-match="#inputPassword" data-match-error="两次密码不匹配"
											placeholder="Confirm Password" required>
									</div>
									<div class="single-input-fields">
										<label style="display: inline-block;margin-right: 262px;" for="sex" class="control-label">性别</label>
										<div class="seloption">
										<select name="usex" id="sex">
											<option value="1">男</option>
											<option value="2">女</option>
										</select>
										</div>
									</div>
									<div class="single-input-fields">
										<label style="display: inline-block;margin-right: 260px;" for="systype" class="control-label">用户类别</label>
										<div class="seloption">
										<select name="usystype">
											<option value="1">审核员</option>
											<option value="3">用户</option>
											<option value="2">志愿者</option>
										</select>
										</div>
									</div>
								</div>
								<!-- form Footer -->
									<div class="register-footer">
										<button type="submit" class="btn">提交</button>
										<button type="reset" class="btn">清除</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>