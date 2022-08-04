<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%String lipostid=request.getParameter("lipostid"); %>
<html>
<head>
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加时间线</title>
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
			<div align="center">
				<div class="register-form-area">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-xl-6 col-lg-7 col-md-10">
								<div class="register-form text-center">
									<!-- Login Heading -->
									<div class="register-heading">
										<span>添加时间线</span>
										<p>记录每一进展，既是历史，也是教训，更是希望</p>
									</div>
									<form name="myform" id="myform" method="post" action="add_timeline.htm">
										<!-- Single Input Fields -->
										<div class="input-box">
											<div class="single-input-fields">
												<label>时间线发生时间</label>
												<input type="datetime-local" class="form-control" name="litimehap" required>
											</div>
											<div class="single-input-fields">
												<label>时间线信息</label>
												<input type="text" class="form-control" name="liimfor" required>
											</div>
											<div style="font-family:arial;color:black;font-size:20px;text-align:center">${msg }</div>
											<div class="register-footer">
												<button type="submit" class="btn">添加</button>
												<a href="show_post.htm?path=1&postid=<%=lipostid %>" class="btn">返回</a>
											</div>
										</div>
										<input type="hidden" name="lipostid" value="<%=lipostid %>">
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
</body>
</html>