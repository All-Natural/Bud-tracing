<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%boolean path=false; %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核员列表</title>
  	<script type="text/javascript" src="js/tools.js"></script>
	<script type="text/javascript">
	    let onEdit=function(veid){
	    	let currForm=document.getElementById("myform");
	    	currForm.action="show_audit_post.htm?postid="+veid;
	    	currForm.submit();
	    }
   </script>
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
		<section class="tranding-area ">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-tittle mb-30 d-flex align-items-center justify-content-between" align="center">
                        <h2  style="background-color:LightSkyBlue;border-radius: 10px;
								border:3px solid grey;color:white;width:50%">待审核通知</h2>
                    </div>
                </div>
            </div>
            <div style="font-family:arial;color:black;font-size:20px;">${msg}</div>
     
            <div class="row justify-content-between">
           	<form id="myform" action="" method="post">
           	<table>
                <div class="col-xl-12 col-lg-12 col-md-12">
                    <!-- single -->
                     <tr>
                       <c:choose>
      					<c:when test="${rows!=null }">   <!-- if -->
       		 			 <c:forEach items="${rows }" var="ins" varStatus="vs">
       		 			 <div class="whats-right-single mb-40">
	       		 			 <div class="whats-right-img">
	                            <a href="post_details.html"><img src="assets/img/gallery/whats_right_img1.jpg" alt=""></a>
	                        </div>
                        	<div class="whats-right-cap">
	                            <span>序号:${vs.count}</span>
	                            <h4><a href="#" onclick="onEdit('${ins.postid}')"><img src="img/edit.png"/>${ins.postdigest }</a></h4>
	                            <p>发布时间:</p>
			      				<p><a href="#" onclick="onEdit('${ins.postid}')">${ins.posttimecreate }</a><p>
			      				<p>联系人:</p>
	                            <p><a href="#" onclick="onEdit('${ins.postid}')" style="color:black;">${ins.postrelaname }</a></p>
	                            <p>联系人电话:</p>
	                            <p><a href="#" onclick="onEdit('${ins.postid}')" style="color:black;">${ins.postrelaphone }</a></p>
                       		 </div>
                       	 </div>
                        </c:forEach>
                        		<script>
		    						newRows(3-${fn:length(rows)},12);
		 						</script>
               			 </c:when>
               			 <c:otherwise>
               					<script>
		     						newRows(3,12);
		  						</script>
               				</c:otherwise>
               			 </c:choose>
               		  </tr>
               		  ${pageController }
                </div>
                </table>
               </form>
           	 </div>
        </div>
    </section>
	
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