<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%String uid=request.getParameter("auid");%>
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
</style>
<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 审核员</title>
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
									<span>修改自身信息</span>
									<p>更新准确的自身信息</p>
								</div>
								<form action="mod_user.htm?auid=<%=uid %>" method="post">
								<!-- Single Input Fields -->
								<div class="input-box">
									<div class="single-input-fields">
										<label>用户名</label>
										<input type="text" class="form-control" id="name" name="uname"
											placeholder="username" data-error="请输入您的用户名" value="${ins.uname }" required>
									</div>
									<div class="single-input-fields">
										<label>姓名</label>
										<input type="text" class="form-control" id="name" name="urealname"
											placeholder="name" data-error="请输入您的真实姓名" value="${ins.urealname }" required>
									</div>
									<div class="single-input-fields">
										<label>邮箱</label>
										<input type="email" class="form-control" id="inputEmail" name="uemail"
											placeholder="Email" data-error="请输入您的正确邮箱" value="${ins.uemail }" required>
									</div>
									<div class="single-input-fields">
										<label>手机号</label>
										<input type="text" class="form-control" id="Phone" name="uphone"
											placeholder="Phone" data-error="请输入您的手机号" value="${ins.uphone }" required>
									</div>
									<div class="single-input-fields">
										<label>家庭住址</label>
										<input type="text" class="form-control" id="name" name="uaddress"
											placeholder="Address" data-error="请输入您的家庭地址" value="${ins.uaddress }" required>
									</div>
									<div class="single-input-fields">
										<label style="display: inline-block;margin-right: 262px;" for="sex" class="control-label">性别</label>
										<div class="seloption">
										<e:select value="男:1,女:2" name="usex" defval="${ins.usex }"/>
										</div>
									</div>
								</div>
								<!-- form Footer -->
									<div class="register-footer">
										<button type="submit" class="btn">修改</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>