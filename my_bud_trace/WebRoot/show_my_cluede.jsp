<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
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
       <script type="text/javascript">
	    let onEdit=function(){
	    	let currForm=document.getElementById("myform");
	    	currForm.action="del_my_clue.htm";
	    	currForm.submit();
	    }
   </script>
     <style type="text/css">
   		.whats-right-single .whats-right-img img {
		    border-radius: 6px;
		    width: 430px;
		}
   </style>
</head>
<body>
<div class="register-form-area">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-7 col-md-10">
							<div class="register-form text-center">
								<!-- Login Heading -->
								<div class="register-heading">
									<span>线索详情</span>
									<p>线索是希望的火苗，总会将人带出黑暗</p>
								</div>
								<form action="mod_clue.htm?" method="post" id="myform">
								<!-- Single Input Fields -->
								<div class="input-box">
									<div class="single-input-fields">
										<label>线索内容</label>
										<textarea class="form-control" cols = "20" rows = "5" placeholder="找寻通知具体内容描述" wrap = "hard" id = "story" 
										name = "clcontent" required>${ins.clcontent }</textarea>
									</div>
									<div class="single-input-fields">
										<label>线索发现时间</label>
										<input type="datetime" class="form-control" name="cltimehap1" value="${ins.cltimehap }">
										<input type="datetime-local" class="form-control" name="cltimehap" required>
									</div>
									<div class="single-input-fields">
										<label>审核状态</label>
										<c:choose>
											<c:when test="${ins.clstate==0 }">未审核</c:when>
											<c:when test="${ins.clstate==1 }">审核已通过</c:when>
											<c:otherwise>审核未通过</c:otherwise>
										</c:choose>
									</div>
									<div class="single-input-fields">
										<label>线索其他备注</label>
										<textarea class="form-control" cols = "20" rows = "5" placeholder="描述部分补充" wrap = "hard" id = "story" 
										name = "clmemo">${ins.clmemo }</textarea>
									</div>
  										<div class="single-input-fields">
												<label>相关图片</label>
												<div class="whats-right-single">
												<div class="whats-right-img">
													 <c:forEach items="${rows }" var="ins" varStatus="vs">
													 	<img src="show_img?iname=${ins.imgurl }" alt="">
												 	 </c:forEach>
												</div>
												</div>
										</div>
								</div>
								<!-- form Footer -->
								<div style="font-family:arial;color:black;font-size:20px;text-align:center ">${msg}</div>
								<div class="register-footer">
									<button type="submit" class="btn">修改</button>
									<a href="#" class="btn" onclick="onEdit()">删除</a>
								</div>
								<input type="hidden" name="clid" value="${ins.clid }">
								<input type="hidden" name="clpostid" value="${ins.clpostid }">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>   
</body>
</html>