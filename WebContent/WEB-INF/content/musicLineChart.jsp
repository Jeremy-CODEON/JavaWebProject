<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>销量历史折线图</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/JS/echarts.js"></script>
<script type="text/javascript" src="${path}/JS/drawPic.js"></script>
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
					<a class="navbar-brand" href="${path}/managerEnter"> <!-- Logo icon -->
						<b> <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
							<!-- Dark Logo icon --> <img src="${path}/ICON/logo-icon.png"
							alt="homepage" class="dark-logo" />
					</b> <span> 管理员信息管理 </span>
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
						<li class="nav-item dropdown u-pro"><a href="${path}/managerEnter"
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
						<li><a class="waves-effect waves-dark" href="${path}/managerEnter"
							aria-expanded="false"><i class="fa fa-tachometer"></i><span
								class="hide-menu">销售管理列表</span></a></li>
						<li><a class="waves-effect waves-dark" href="${path}/managerEnter"
							aria-expanded="false"><i class="fa fa-tachometer"></i><span
								class="hide-menu">音乐列表</span></a></li>
						<li><a class="waves-effect waves-dark" href="${path}/managerEnter"
							aria-expanded="false"><i class="fa fa-user-circle-o"></i><span
								class="hide-menu">销售报表</span></a></li>
						<li><a class="waves-effect waves-dark" href="${path}/managerEnter"
							aria-expanded="false"><i class="fa fa-table"></i><span
								class="hide-menu">用户统计数据</span></a></li>
						<li><a class="waves-effect waves-dark" href="${path}/managerEnter"
							aria-expanded="false"><i class="fa fa-table"></i><span
								class="hide-menu">销售统计图</span></a></li>
						<li><a class="waves-effect waves-dark" href="${path}/managerEnter"
							aria-expanded="false"><i class="fa fa-table"></i><span
								class="hide-menu">日志记录</span></a></li>
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
					<div class="col-12">
						<div class="card" id="charts-card">
							<div class="card-body">
								<div class="d-flex">
									<div>
										<h5 class="card-title">历史销量折线图</h5>
									</div>
								</div>
								<div id="statisticChart-line"
									style="width: 1000px; height: 400px;"></div>								
							</div>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="display:none" >
		<c:forEach var="countValue" items="${requestScope.countValueList}" varStatus="status">
			<div id="countValue${status.index}">${countValue}</div>
		</c:forEach>
		<div id="listLength">${fn:length(requestScope.countValueList)}</div>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		var lineData = [];
		for (var i = 0; i < parseInt($("#listLength").text()); i++) 
		{
			var saleNum = parseInt($("#countValue" + i).text());
			var cateName = i+1;
			// 折线图数据
			lineData.push({
				value : saleNum,
				name : cateName
			})
		}
		drawProductHistoryLine('statisticChart-line', lineData);
		if(parseInt($("#listLength").text())==0)
		{
			alert("该音乐暂无历史数据！");
		}
	});
		function buttonIndex() {
			window.location.replace("managerEnter");
		}
	</script>
</body>
</html>