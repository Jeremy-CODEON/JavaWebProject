<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加音乐页面</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
<link
	href="${path}/CSS/indexCSS/assets/node_modules/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${path}/CSS/indexCSS/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css"
	rel="stylesheet">
<!-- This page CSS -->
<!-- chartist CSS -->
<link
	href="${path}/CSS/indexCSS/assets/node_modules/morrisjs/morris.css"
	rel="stylesheet">
<!--c3 CSS -->
<link
	href="${path}/CSS/indexCSS/assets/node_modules/c3-master/c3.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="${path}/CSS/indexCSS/html/css/style.css" rel="stylesheet">
<!-- Dashboard 1 Page CSS -->
<link href="${path}/CSS/indexCSS/html/css/pages/dashboard1.css"
	rel="stylesheet">
<!-- You can change the theme colors from here -->
<link href="${path}/CSS/indexCSS/html/css/colors/default.css" id="theme"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.1/build/pure-min.css">
</head>
<body class="fix-header fix-sidebar card-no-border">
	<div id="main-wrapper">
		<header class="topbar" style="margin-top: 0px;">
			<nav class="navbar top-navbar navbar-expand-md navbar-light">
				<div class="navbar-header">
					<a class="navbar-brand" href="${path}/salerEnter"> <!-- Logo icon -->
						<b> <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
							<!-- Dark Logo icon --> <img src="${path}/ICON/logo-icon.png"
							alt="homepage" class="dark-logo" />
					</b> <span> 销售信息管理 </span>
					</a>
				</div>
				<!-- ============================================================== -->
				<!-- End Logo -->
				<!-- ============================================================== -->
				<div class="navbar-collapse">
					<!-- ============================================================== -->
					<!-- User profile and search -->
					<!-- ============================================================== -->
					<ul class="navbar-nav my-lg-0">
						<!-- ============================================================== -->
						<!-- Profile -->
						<!-- ============================================================== -->
						<li class="nav-item dropdown u-pro"><a href="${path}/salerEnter"
							class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span
								class="hidden-md-down">返回首页 &nbsp;</span></a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<aside class="left-sidebar">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar">
				<!-- Sidebar navigation-->
				<nav class="sidebar-nav">
					<ul id="sidebarnav">
						<li><a class="waves-effect waves-dark" aria-expanded="false"
							href=""><span class="hide-menu">管理导航</span> </a></li>
						<li><a class="waves-effect waves-dark" href="#music-card"
							aria-expanded="false"><i class="fa fa-tachometer"></i><span
								class="hide-menu">音乐列表</span></a></li>
						<li><a class="waves-effect waves-dark" href="#log-card"
							aria-expanded="false"><i class="fa fa-table"></i><span
								class="hide-menu">用户日志记录</span></a></li>
					</ul>
				</nav>
				<!-- End Sidebar navigation -->
			</div>
			<!-- End Sidebar scroll-->
		</aside>
		<!-- ============================================================== -->
		<!-- End Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Projects of the Month -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<div class="container-fluid">
				<div id="message-area" style="display: none">${requestScope.message}</div>
				<div class="row">
					<!-- Column -->
					<div class="col-lg-8">
						<div class="card">
							<!-- Tab panes -->
							<div class="card-body">
								<form action="${path}/addMusic" method="post"
									class="form-horizontal form-material">
									<div class="d-flex">
										<div>
											<h5 class="card-title">添加新的音乐</h5>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐名</label>
										<div class="col-md-12">
											<input id="name" type="text" name="name" placeholder=""
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">演唱歌手</label>
										<div class="col-md-12">
											<input id="singer" type="text" name="singer" placeholder=""
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐描述</label>
										<div class="col-md-12">
											<input id="description" type="text" name="description" placeholder=""
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">所属专辑</label>
										<div class="col-md-12">
											<input id="album" type="text" name="album" placeholder=""
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐时长</label>
										<div class="col-md-12">
											<input id="duration" type="text" name="duration" placeholder=""
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">所属类型</label>
										<div class="col-md-12">
											<input id="category" type="text" name="category" placeholder=""
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">单价</label>
										<div class="col-md-12">
											<input id="cost" type="number" name="cost" placeholder="请填入数字（大于0）"
												min="0" class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐状态：(0:未上架;1:已上架)</label>
										<div class="col-md-12">
											<input id="state" type="number" name="state"
												placeholder="请填入0（未上架）或1（已上架）" min="0" max="1"
												class="form-control form-control-line">
										</div>
									</div>
									<input type="submit" id="submit" value="确认添加音乐" style="pure-button"
										disabled="disabled" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>








	<%-- 	<div id="message-area" style="display:none">${requestScope.message}</div> --%>
	<!-- 	<form action="addMusic" method="post"> -->
	<!-- 		<div id="info_name"> -->
	<!-- 			名称：<input type="text" id="name" name="name" /> -->
	<!-- 		</div> -->
	<!-- 		<div id="info_singer"> -->
	<!-- 			歌手：<input type="text" id="singer" name="singer" /> -->
	<!-- 		</div> -->
	<!-- 		<div id="info_description"> -->
	<!-- 			音乐描述：<input type="text" id="description" name="description" /> -->
	<!-- 		</div> -->
	<!-- 		<div id="info_album"> -->
	<!-- 			专辑：<input type="text" id="album" name="album" /> -->
	<!-- 		</div> -->
	<!-- 		<div id="info_duration"> -->
	<!-- 			时长：<input type="text" id="duration" name="duration" /> -->
	<!-- 		</div> -->
	<!-- 		<div id="info_category"> -->
	<!-- 			类型：<input type="text" id="category" name="category" /> -->
	<!-- 		</div> -->
	<!-- 		<div id="info_cost"> -->
	<!-- 			单价：<input type="text" id="cost" name="cost" placeholder="请填入数字（大于0）"/> -->
	<!-- 		</div> -->
	<!-- 		<div id="info_state"> -->
	<!-- 			初始状态：<input type="text" id="state" name="state" placeholder="请填入0（未上架）或1（已上架）"/> -->
	<!-- 		</div> -->
	<!-- 			<input type="submit" id="submit" value="确认添加音乐" disabled="disabled" /> -->
	<!-- 	</form> -->
	<!-- 	<button id="indexButton" onclick="buttonIndex()">返回管理首页</button> -->
	<script type="text/javascript">
		$("#state")
				.bind(
						'blur',
						function() {
							var name = $("#name").val();
							var singer = $("#singer").val();
							var album = $("#album").val();
							var duration = $("#duration").val();
							var category = $("#category").val();
							var cost = $("#cost").val();
							var state = $("#state").val();
							if (name != null && singer != null && album != null
									&& duration != null && category != null
									&& cost != null && state != null) {
								if (parseFloat(cost) > 0
										&& (parseInt(state) == 1 || parseInt(state) == 0)) {
									$("#submit").removeAttr("disabled");
								} else {
									alert("请检查输入的信息：是否所有信息已填且单价和状态填写正确！");
								}

							}
						});
		function buttonIndex() {
			window.location.replace("manage");
		}
	</script>
</body>
</html>