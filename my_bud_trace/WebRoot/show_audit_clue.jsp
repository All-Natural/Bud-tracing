<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 审核找寻通知</title>
<script type="text/javascript">
    function disableOcx() {
    		var form = document.forms[0];
    		for ( var i = 0; i < form.length; i++) {
    		var element = form.elements[i];
    		//部分元素可以编号 element.name 是元素自定义 name
    		if (element.name != "recontent"
    		 && element.name != "rememo"
    		 && element.name != "submit"
    	     && element.name != "clstate"
    	     && element.name != "clid"
    	     && element.name != "cluid"
    	     && element.name != "urealaname") {
    		element.disabled = "true";
    		}
    		}
    }
    window.onload = disableOcx;
  </script>
  
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
<main>
			<div class="register-form-area">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-7 col-md-10">
							<div class="register-form text-center">
								<!-- Login Heading -->
								<div class="register-heading">
									<span>审核找寻通知线索</span>
									<p>审核是任务，也是使命</p>
								</div>
								<form action="audit_clue.htm" method="post">
								<!-- Single Input Fields -->
								<div class="input-box">
									<div class="single-input-fields">
										<label>线索内容</label>
										<textarea class="form-control" cols = "20" rows = "5" placeholder="找寻通知具体内容描述" wrap = "hard" id = "story" 
										name = "postcontent" required>${ins.clcontent }</textarea>
									</div>
									<div class="single-input-fields">
										<label>线索发现时间</label>
										<input type="datetime" class="form-control" name="cltimehap" value="${ins.cltimehap }" required>
									</div>
									<div class="single-input-fields">
										<label>线索发布时间</label>
										<input type="text" class="form-control" id="Phone" name="cltimecreate" value="${ins.cltimecreate }" required>
									</div>
									<div class="single-input-fields">
										<label>线索备注</label>
										<input type="text" class="form-control" id="name" name="clmemo" value="${ins.clmemo }">
									</div>
									<div class="single-input-fields">
										<label class="control-label">线索附件</label>
										<input type="text" class="form-control" name="cldoc1" value="${ins.cldoc1 }" required>
									</div>
									<div class="single-input-fields">
										<label class="control-label">审核结果</label>
										<e:select value="审核通过:1,审核未通过:2" name="clstate"/>
									</div>
									<div class="single-input-fields">
										<label>审核记录内容填写</label>
										<textarea class="form-control" cols = "20" rows = "5" placeholder="描述审核行为" wrap = "hard" id = "story1" 
										name = "recontent"></textarea>
									</div>
									<div class="single-input-fields">
										<label>审核记录备注</label>
										<textarea class="form-control" cols = "20" rows = "5" placeholder="描述审核行为" wrap = "hard" id = "story2" 
										name = "rememo"></textarea>
									</div>
									
									<input type="hidden" name="clid" value="${ins.clid }"/>
									<input type="hidden" name="cluid" value="${ins.cluid }"/>
									<input type="hidden" name="urealaname" value="${ins.urealaname }"/>
									${msg }
									<div class="register-footer">
										<input type="submit" class="btn" name="submit" value="提交">
									</div>
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