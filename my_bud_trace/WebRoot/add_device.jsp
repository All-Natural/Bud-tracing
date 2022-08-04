<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<html>
<head>
<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 增加设备</title>
	<style type="text/css">@IMPORT url("/css/style1.css");</style>
   <script type="text/javascript" src="/js/tools1.js"></script>
<style type="text/css">
	body{
		
                -webkit-background-size: cover;
                -o-background-size: cover;                
                background-size: cover;   

		line-height: 1.5em;
		font-size: 14px;
        color: white;
	}
	
	a {
		line-height: 1.5em;
        font-family: "Times New Roman", Times, serif;
		font-size: 17px;
  		color: white;
  		text-shadow: 4px 4px 5px black;
  		text-decoration: none;
	}
	a:hover {
  		color: black;
	}
	h1{
		padding-top: 5px;
  		height: 7%;
  		width: 20%;
  		text-align:center;
  		border-radius: 7px;
  		font-weight: 300;
	}
	table{
		border-radius: 7px;
		width:65%;
		height:60%;
	}
	input[type=text], select {
	  width: 70%;
	  padding: 8px 10px;
	  margin: 8px 0;
	  display: inline-block;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	  box-sizing: border-box;
	}
	
	input[type=submit] {
	  width: 15%;
	  background-color: #28a745;
	  color: white;
	  padding: 14px 20px;
	  margin: 8px 0;
	  border: none;
	  border-radius: 4px;
	  cursor: pointer;
	  algin:center;
	  transition: .75s;
	}
	input[type=submit]:hover {
	  background-color: #4caf50;
	  transition: .75s;
	}
	div {
	  border-radius: 5px;
	  padding-left: 530px;
	  text-align:center;
  	}
</style>
  
</head>
<body>
<div id="msg" class="msg"></div>
${msg}
<br>
<br>
<h1 style="color:black;margin: 0 auto;"> 设备添加</h1>
<form action="" method="post">
 <div class="edit">
   <table style="width: 450px;box-shadow: 5px 5px 3px #eee;">       
     
     
     <tr>
       <td width="8%" style="color:black;font-size: 21px;font-weight: 300;">手机号</td>
       <td width="30%">
         <e:text name="uphone" defval=""/>
       </td>
       </tr>
       <tr>
       <td width="8%" style="color:black;font-size: 21px;font-weight: 300;">孩童姓名</td>
       <td width="30%">
         <e:text name="childname" defval=""/>
       </td>
     </tr>
     <tr>
		<td colspan="4" style="color:black;text-align:center;">
	          <input type="submit" name="next" value="添加" 
	                 formaction="add_dev.htm">
	       </td>
        </tr>
   </table>
 </div>

</form>
</body>
</html>