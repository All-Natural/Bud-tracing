<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
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
	    	currForm.action="show_admin.htm?auid="+veid;
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
		<style>
			body{
		background: url("img/wp2757874.gif") no-repeat center center fixed;
                /*兼容浏览器版本*/
                -webkit-background-size: cover;
                -o-background-size: cover;                
                background-size: cover;   
	}
	   	h4{
			border-radius: 10px;
			color: #5b7da3;
			text-align:center;
			background: #FFF;
			width:50%;
	   	}
	   	/* Border styles */
		#table-1 thead, #table-1 tr {
			border-top-width: 1px;
			border-top-style: solid
		}
		#table-1 {
			border-bottom-width: 1px;
			border-bottom-style: solid;
			border-bottom-color: #a8bfde;
		}
		
		/* Padding and font style */
		#table-1 td, #table-1 th {
			padding: 5px 10px;
			font-size: 18px;
			font-family: Verdana;
			color: #5b7da3;
			text-align:center;
		}
		
		/* Alternating background colors */
		#table-1 tr:nth-child(even) {
			background: #d3dfed
		}
		#table-1 tr:nth-child(odd) {
			background: #FFF
		}
		p{
			padding-top:0px;
			background: #d3dfed;
			width:857px;
			height:40px;
		}
		a{
			color:black;
		}
		a:hover{
			color:red;
		}
    	</style>
</head>
<body>
	<form action="del_admin.htm" method="post" id="myform">
	<div class="data">
		<div align="center">
			&nbsp;
			<h4>在职审核员</h4>
		</div>
			<div align="center">
				<table id="table-1">
					<thead>
						<tr>
							<th>序号</th>
							<th>用户名</th>
							<th>性别</th>
							<th>真实姓名</th>
							<th>电话号码</th>
							<th>电子邮箱</th>
							<th>家庭住址</th>
							<th>勾选</th>
						</tr>
					</thead>
					<c:choose>  
						<c:when test="${rows!=null}">
						 <c:forEach items="${rows}" var="ins" varStatus="vs">
						 <tr>
							<td>${vs.count}</td>
							<td>${ins.uname}</td>
							<td>
							<c:choose>
							<c:when test="${ins.usex==1}">男</c:when>
							<c:otherwise>女</c:otherwise>
							</c:choose>
							</td>
							<td><a href="#" onclick="onEdit('${ins.uid}')"><img src="img/edit.png"/>${ins.urealname}</a></td>
							<td>${ins.uphone}</td>
							<td>${ins.uemail}</td>
							<td>${ins.uaddress}</td>
							<td><input type="checkbox" name="groupid" value="${ins.uid }"/></td>
						</tr>
						</c:forEach>
						<script>
		   	 				newRows(5-${fn:length(rows)},8);
		 				</script>		
					   </c:when>
					   <c:otherwise>   <!-- else -->
		    				<script>
		     	 				newRows(5,8);
		  					 </script>
     			 		</c:otherwise>
					</c:choose>
				</table>
				<div>
				<p>
				${msg }
				<input type="submit" class="genric-btn success radius" 
				value="删除" style="width:100px;height:40px;"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pageController }</p>
				</div>
			</div>
		</div>
	</form>

</body>
</html>