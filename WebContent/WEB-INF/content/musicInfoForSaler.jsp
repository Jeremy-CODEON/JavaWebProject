<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>音乐详情页面</title>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>

<link
	href="<%=basePath%>/CSS/indexCSS/assets/node_modules/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=basePath%>/CSS/indexCSS/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css"
	rel="stylesheet">
<!-- This page CSS -->
<!-- chartist CSS -->
<link
	href="<%=basePath%>/CSS/indexCSS/assets/node_modules/morrisjs/morris.css"
	rel="stylesheet">
<!--c3 CSS -->
<link
	href="<%=basePath%>/CSS/indexCSS/assets/node_modules/c3-master/c3.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=basePath%>/CSS/indexCSS/html/css/style.css"
	rel="stylesheet">
<!-- Dashboard 1 Page CSS -->
<link href="<%=basePath%>/CSS/indexCSS/html/css/pages/dashboard1.css"
	rel="stylesheet">
<!-- You can change the theme colors from here -->
<link href="<%=basePath%>/CSS/indexCSS/html/css/colors/default.css"
	id="theme" rel="stylesheet">
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.1/build/pure-min.css">
</head>
<body class="fix-header fix-sidebar card-no-border">
	<div id="main-wrapper">
		<header class="topbar" style="margin-top: 0px;">
			<nav class="navbar top-navbar navbar-expand-md navbar-light">
				<div class="navbar-header">
					<a class="navbar-brand" href="<%=basePath%>/manage"> <!-- Logo icon -->
						<b> <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
							<!-- Dark Logo icon --> <img
							src="<%=basePath%>/ICON/logo-icon.png" alt="homepage"
							class="dark-logo" />
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
						<li class="nav-item dropdown u-pro"><a
							href="<%=basePath%>manage"
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
						<li><a class="waves-effect waves-dark" href="#statistic-card"
							aria-expanded="false"><i class="fa fa-user-circle-o"></i><span
								class="hide-menu">销售报表</span></a></li>
						<li><a class="waves-effect waves-dark" href="#charts-card"
							aria-expanded="false"><i class="fa fa-table"></i><span
								class="hide-menu">销售统计图</span></a></li>
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
			
			    <div id="message" style="display: none;">${requestScope.message}</div>
			    
				<div class="row">
					<!-- Column -->
					<div class="col-lg-8">
						<div class="card">
							<!-- Tab panes -->
							<div class="card-body">
								<form action="<%=basePath%>/alterMusicInfo"
									class="form-horizontal form-material">
									<div class="d-flex">
										<div>
											<h5 class="card-title">音乐信息修改</h5>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐id</label>
										<div class="col-md-12">
											<input type="text" name="id" value="${music.id}"
												class="form-control form-control-line" readonly="true">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐名</label>
										<div class="col-md-12">
											<input type="text" name="name" placeholder="${music.name}"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">演唱歌手</label>
										<div class="col-md-12">
											<input type="text" name="singer"
												placeholder="${music.singer}"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐描述</label>
										<div class="col-md-12">
											<input type="text" name="description"
												placeholder="${music.description}"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">所属专辑</label>
										<div class="col-md-12">
											<input type="text" name="album" placeholder="${music.album}"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐时长</label>
										<div class="col-md-12">
											<input type="text" name="duration"
												placeholder="${music.duration}"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">所属类型</label>
										<div class="col-md-12">
											<input type="text" name="category"
												placeholder="${music.category}"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">单价</label>
										<div class="col-md-12">
											<input type="text" name="cost"
												placeholder="${music.cost}"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">音乐状态：(0:未上架;1:已上架)</label>
										<div class="col-md-12">
											<input type="number" name="state"
												placeholder="${music.state}"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">已购买量</label>
										<div class="col-md-12">
											<input type="text" name="purchases"
												placeholder="${music.purchases}"
												class="form-control form-control-line" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">销售总额</label>
										<div class="col-md-12">
											<input type="text" name="purchases"
												placeholder="${music.purchases*music.cost}"
												class="form-control form-control-line" disabled="disabled">
										</div>
									</div>
									<input type="submit" value="确认修改音乐信息" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		if ($("#message").text() != "") {
			alert($("#message").text());
			//$("#message").show();
		}
		//else {
		//	$("#message").hide();
		//}
	});
	</script>
</body>
</html>