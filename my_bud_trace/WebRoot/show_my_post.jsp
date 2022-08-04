<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 我的找寻通知列表</title>
	<script type="text/javascript">
	    let onEdit=function(veid){
	    	let currForm=document.getElementById("myform");
	    	currForm.action="show_post.htm?path=1&postid="+veid;
	    	currForm.submit();
	    }
   </script>
   <style type="text/css">
   		.whats-right-single .whats-right-img img {
		    border-radius: 6px;
		    width: 260px;
		}
   </style>
    <script type="text/javascript" src="js/tools1.js"></script>
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
<div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-tittle mb-30 d-flex align-items-center justify-content-between" style="padding-bottom: 0px;">
                        <h1 style="margin: 0 auto;">找寻通知</h1>
                    </div>
                </div>
            </div>
            <div style="font-family:arial;color:LightSkyBlue;font-size:20px;text-align:center">${msg}</div>
     
            <div class="row justify-content-between">
           	<form id="myform" action="" method="post">
           	<table>
                <div class="col-xl-8 col-lg-8 col-md-8">
                    <!-- single -->
                    <tr>
                       <c:choose>
      					<c:when test="${rows!=null }">   <!-- if -->
       		 			 <c:forEach items="${rows }" var="ins" varStatus="vs">
       		 			 <div class="whats-right-single mb-40">
	       		 			 <div class="whats-right-img">
	                            <img src="show_img?iname=${ins.imgurl }" alt="">
	                        </div>
                        	<div class="whats-right-cap">
	                            <span>序号&nbsp;&nbsp;${vs.count}</span>
	                            <h4><a href="#" onclick="onEdit('${ins.postid}')">${ins.postdigest }</a></h4>
	                              <p> <c:choose>
			       					 <c:when test="${ins.poststatus==2 }">未找到</c:when>
			      					 <c:when test="${ins.poststatus==1 }">已找到</c:when>
			      					 </c:choose>
			      				</p>
			      				<p>${ins.posttimecreate }<p>
	                            <p><a href="#" onclick="onEdit('${ins.postid}')" style="color:black;">联系人姓名:&nbsp;${ins.postrelaname }</a></p>
	                            <p><a href="#" onclick="onEdit('${ins.postid}')" style="color:black;">电话:&nbsp;${ins.postrelaphone }</a></p>
                       		 </div>
                       	 </div>
                        </c:forEach>
                        		<script>
		    						newRows(4-${fn:length(rows)},12);
		 						</script>
               			 </c:when> 
		 					<c:otherwise>
               					<script>
		     						newRows(4,12);
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

</body>
</html>