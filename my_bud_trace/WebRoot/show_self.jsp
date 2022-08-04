<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自我信息查看</title>
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
	    let onEdit=function(veid){
	    	let currForm=document.getElementById("myform");
	    	currForm.action="show_user.htm?auid="+veid;
	    	currForm.submit();
	    }
	    function disableOcx() {
    		var form = document.forms[0];
    		for ( var i = 0; i < form.length; i++) {
    		var element = form.elements[i];
    		//部分元素可以编号 element.name 是元素自定义 name
    		if (element.name != "auditEntity.auditContent"
    		&& element.name != "auditEntity.auditAutograph"
    		&& element.name != "auditEntity.auditTime"
    		&& element.name != "auditEntity.auditState"
    		&& element.name != "submitBtn"
    		&& element.name != "reset"
    		&& element.name != "id"
    		&& element.name != "processInstanceId"
    		&& element.name != "updateForm") {
    		element.disabled = "true";
    		}
    		}
    }
    window.onload = disableOcx;
   </script>

</head>
<body>
	<main>
			<div class="register-form-area">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-7 col-md-10">
							<div class="register-form text-center">
								<!-- Login Heading -->
								<div class="register-heading">
									<span>我的信息</span>
									<p>${msg }</p>
								</div>
								<form action="" method="post" id="myform">
								<!-- Single Input Fields -->
								<div class="input-box">
									<div class="single-input-fields">
										<label>用户名</label>
										<input type="text" class="form-control" name="posttimehap" value="${ins.uname}" required>
									</div>
									<div class="single-input-fields">
										<label>电话号码</label>
										<input type="text" class="form-control" id="Phone" name="postrelaphone"
											placeholder="请留下一个联系方式" data-error="请输入您的手机号" value="${ins.uphone }" required>
									</div>
									<div class="single-input-fields">
										<label>电子邮箱</label>
										<input type="text" class="form-control" id="name" name="postrelaname"
											placeholder="请留下一位联系人姓名" data-error="请输联系人姓名" value="${ins.uemail }" required>
									</div>
									<div class="single-input-fields">
										<label>家庭住址</label>
										<textarea class="form-control" cols = "20" rows = "5" placeholder="描述部分补充" wrap = "hard" id = "story" 
										name = "postmemo">${ins.uaddress }</textarea>
									</div>
								</div>
									<div class="register-footer">
										<a href="#" class="btn" onclick="onEdit('${ins.uid}')">修改</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
</main>
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