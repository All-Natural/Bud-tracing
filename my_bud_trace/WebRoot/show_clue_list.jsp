<%@ page language="java"  pageEncoding="utf-8"%>
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
<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 相关线索列表</title>
   <script type="text/javascript" src="js/tools1.js"></script>
<style type="text/css">
 		#pageController{
       		width: 100px;
       		border:blue;
     	 }
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
		}
		
		/* Alternating background colors */
		#table-1 tr:nth-child(even) {
			background: #d3dfed
		}
		#table-1 tr:nth-child(odd) {
			background: #FFF
		}
		a{
			color:black;
		}
		a:hover{
			color:red;
		}
</style>
	<script type="text/javascript">
	    let onEdit=function(veid){
	    	let currForm=document.getElementById("myform");
	    	currForm.action="show_clue.htm?path=0&clid="+veid;
	    	currForm.submit();
	    }
   </script>
</head>
<body>


<form id="myform" action="" method="post">
<div>
  <div style="font-family:arial;color:black;font-size:20px;text-align:center ">${msg}</div>
  </div>
<div align="center">
  <table id="table-1">
  <thead>
    <tr>
       <th style="text-align:center">序号</th>
       <th style="text-align:center">提供者</th>
       <th style="text-align:center">线索摘要</th>
       <th style="text-align:center">线索创建时间</th>
       <th style="text-align:center">线索发现时间</th>
    </tr>
    </thead>
    <!-- 行多行少都是事儿 -->
    <c:choose>
      <c:when test="${rows!=null }">   <!-- if -->
         <c:forEach items="${rows }" var="ins" varStatus="vs">
		    <tr>
		       <td style="text-align:center">${vs.count}</td>
		       <td style="text-align:center">${ins.uname}</td>
		       <td><a href="#" onclick="onEdit('${ins.clid}')"><img src="img/view.png">${ins.cldigest }</a></td>
		       <td>${ins.cltimecreate }</td>
		       <td>${ins.cltimehap }</td>
		    </tr>
         </c:forEach>
		 <script>
		    newRows(6-${fn:length(rows)},12);
		 </script>
      </c:when>
      <c:otherwise>   <!-- else -->
		    <script>
		      newRows(6,9);
		    </script>
      </c:otherwise>
    </c:choose>
    <tfoot>
    	<tr>
    		<td colspan="5" style="text-align:right">
    		 ${pageController }
    		 </td>
    	</tr>
    </tfoot>
    
  </table>
  
</div>

<!-- 功能按钮 -->
<div class="button">
  <table>
    <tr>
      <td>
      </td>
    </tr>
  </table>
</div>

</form>   
</body>
</html>