<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<html class="no-js" lang="zxx">
	<head>
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>花蕾寻迹 & 基于儿童行为轨迹的状态分析及预警系统| 查看找寻通知详情</title>
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

		<!--  依赖  -->
		<script src="js/vue.js"></script>
		<!-- 引入样式 -->
		<link rel="stylesheet" href="element-ui/theme-chalk/index.css">

		<!-- 引入组件库 -->
		<script src="element-ui/index.js"></script>
		<script src="js/jquery.js"></script>
		<script src="js/proj.js"></script>
  <style type="text/css">
   		.whats-right-single .whats-right-img img {
		    border-radius: 6px;
		    width: 420px;
		}
   </style>
   <script type="text/javascript">
	    let onEdit=function(veid){
	    	let currForm=document.getElementById("myform");
	    	currForm.action="del_my_post.htm?delflag="+veid;
	    	currForm.submit();
	    }
   </script>
	</head>
	<body>
			<section class="tranding-area ">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="section-tittle mb-30 d-flex align-items-center justify-content-between">
								<h2>找寻通知详情 </h2>
								<a href="add_timeline.jsp?lipostid=${ins.postid }"
									class="genric-btn success radius">新增时间线</a>
							</div>
						</div>
					</div>

				</div>
			</section>
			<div>
				<div class="register-form-area">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-xl-6 col-lg-7 col-md-10">
								<div class="register-form text-center">
									<!-- Login Heading -->
									<div class="register-heading">
										<span>找寻通知详情</span>
										<p>每人多点关注，就会多些希望</p>
									</div>
									<form name="myform" id="myform" action="mod_my_post.htm" method="post">
										<!-- Single Input Fields -->
										<div class="input-box">
											<div class="single-input-fields">
												<label>找寻通知内容</label>
												<textarea class="form-control" cols="20" rows="5"
													placeholder="找寻通知具体内容描述" wrap="hard" id="story"
													name="postcontent" required>${ins.postcontent}</textarea>
											</div>
											<div class="single-input-fields">
												<label class="control-label">找寻状态</label>
												<select name="poststatus">
													<option value="2">未找到</option>
													<option value="1">已找到</option>
												</select>
											</div>
											<div class="single-input-fields">
												<label>丢失发生时间</label>
												<input type="datetime" class="form-control" name="posttimehap1"
													value="${ins.posttimehap }">
												<input type="datetime-local" class="form-control" name="posttimehap"
													value="${ins.posttimehap }" required>
											</div>
											<div class="single-input-fields">
												<label>手机号</label>
												<input type="text" class="form-control" id="Phone" name="postrelaphone"
													value="${ins.postrelaphone }" required>
											</div>
											<div class="single-input-fields">
												<label>联系人姓名</label>
												<input type="text" class="form-control" id="name" name="postrelaname"
													value="${ins.postrelaname }" required>
											</div>
											<div class="single-input-fields">
												<label>审核状态</label>
												<c:choose>
													<c:when test="${ins.poststate==0 }">未审核</c:when>
													<c:when test="${ins.poststate==1 }">审核已通过</c:when>
													<c:when test="${ins.poststate==2 }">审核未通过</c:when>
												</c:choose>
											</div>
											<div class="single-input-fields">
												<label>备注</label>
												<textarea class="form-control" cols="20" rows="5" placeholder="描述部分补充"
													wrap="hard" id="story" name="postmemo">${ins.postmemo }</textarea>
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
										<div style="font-family:arial;color:black;font-size:20px;text-align:center">${msg }</div>
										<div class="register-footer">
											<button type="submit" class="btn">修改</button>
											<a href="#" class="btn" onclick="onEdit('${ins.postdelflag+1 }')">删除</a>
										</div>
										<input type="hidden" value="${ins.postid }" id="postid"/>
									</form>
								</div>
							</div>
							<div id="app" style="margin-top: 10px;">
								<div class="block">
									<div class="radio" style="margin-bottom: 20px;">
										<!-- 排序： -->
										<el-radio-group v-model="reverse">
											<el-radio :label="true">倒序</el-radio>
											<el-radio :label="false">正序</el-radio>
										</el-radio-group>
									</div>
							
									<el-timeline :reverse="reverse">
										<el-timeline-item v-for="(activity, index) in activities" :key="index"
											:timestamp="activity.litimehap" type="success">
											{{activity.liimfor}}
										</el-timeline-item>
									</el-timeline>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			<br>
			<br>
			<div>
				<p align="center">
					<iframe src="show_clue_list.htm?postid=${postid }" width="600px" height="370px"
						style="border:3px solid grey;">
					</iframe>
				</p>
			</div>

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
	
	<script>
		
		var linelist = [{
			content: '活动按期开始',
			timestamp: '2018-04-15'
		}, {
			content: '通过审核',
			timestamp: '2018-04-13'
		}, {
			content: '创建成功',
			timestamp: '2018-04-11'
		}];
		var act = {};
		act.timelist = function(postid) {
			var url = '/bud/timeline/list?postid=' + postid;
			k.gest_rest(url, function(result) {
				var dataArr = result.data;
				if (dataArr == null) return;
				linelist.splice(0, linelist.length); //清空数组
				for (var index = 0; index < dataArr.length; index++) {
					linelist.push(dataArr[index]);
				}
			})
		}
		
		var app = new Vue({
			el: "#app",
			data: {
				reverse: true,
				activities: linelist
			},
			methods: {
		
			}
		})
		var postid = document.getElementById("postid").value;
		act.timelist(postid);
	
	</script>

</html>
