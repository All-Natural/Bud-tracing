<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%
	String type = request.getParameter("type");
	String id = type.equals("1")?request.getParameter("clid"):request.getParameter("postid");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="all">
<!--//booststrap end-->
<!-- font-awesome icons -->
<link href="css/font-awesome.min.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!--stylesheets-->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all">
<!--//stylesheets-->
<link href="//fonts.googleapis.com/css?family=Fira+Sans:400,500,600,700"
	rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Oxygen:400,700"
	rel="stylesheet">
<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 查看线索详情</title>
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
    	input[type="submit"] {
			    background-color: #009688;
			    color: antiquewhite;
			    border-radius: 10px;
			}
    </style>
    
</head>
<body>
<div class="register-form-area">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-7 col-md-10">
							<div class="register-form text-center">
								<form action="upload.htm?type=<%=type %>&id=<%=Integer.parseInt(id) %>" method="post" enctype="multipart/form-data">
									<div class="single-input-fields">
										<label>相关图片(最多上传两张))</label>
										<input type="file" class="form-control" name="file">
									</div>
									<div class="register-footer">
										<p>${msg }</p>
									</div>
									<div class="register-footer">
										<input type="submit" value="提交">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>   
</body>
</html>