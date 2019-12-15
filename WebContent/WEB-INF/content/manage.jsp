<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<html>
<head>
<meta charset="UTF-8">
<title>销售管理</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"
	type="text/javascript"></script>
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
					<a class="navbar-brand" href="manage"> <!-- Logo icon --> <b>
							<!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
							<!-- Dark Logo icon --> <img src="${path}/ICON/logo-icon.png"
							alt="homepage" class="dark-logo" />
					</b> <span>销售信息管理</span>
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
						<c:choose>
							<c:when test="${sessionScope.saler!=NULL}">
								<li class="nav-item dropdown u-pro"><a href=""
									class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"><span class="hidden-md-down">欢迎销售(id:${sessionScope.saler.id})！
											&nbsp;</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item dropdown u-pro"><a href="loginForm"
									class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"><span class="hidden-md-down">请登录
											&nbsp;</span></a></li>
							</c:otherwise>
						</c:choose>
						<li class="nav-item dropdown u-pro"><a href="index"
							class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span
								class="hidden-md-down">返回音乐充电小店 &nbsp;</span></a></li>
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
					<div class="col-12">
						<div class="card" id="music-card">
							<div class="card-body">
								<div class="d-flex">
									<div>
										<h5 class="card-title">音乐列表</h5>
									</div>
								</div>
								<button onclick="buttonAddMusic()" class="pure-button">点击添加音乐</button>
								<div class="table-responsive m-t-20 no-wrap">
									<table class="table vm no-th-brd pro-of-month">
										<thead>
											<tr>
												<th>id</th>
												<th>音乐标题</th>
												<th>歌手</th>
												<th>专辑</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="music" items="${requestScope.musicList}"
												varStatus="status">
												<tr>
													<td>${music.id}</td>
													<td>${music.name}</td>
													<td>${music.singer}</td>
													<td>${music.album}</td>
													<td>
														<button onclick="buttonDetail('${music.id}')"
															class="pure-button">详情</button>
														<button onclick="buttonDelete('${music.id}')"
															class="pure-button">删除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<!-- Column -->
					<div class="col-12">
						<div class="card" id="statistic-card">
							<div class="card-body">
								<div class="d-flex">
									<div>
										<h5 class="card-title">销售统计报表</h5>
									</div>
								</div>
								<div class="table-responsive m-t-20 no-wrap">
									<table class="table vm no-th-brd pro-of-month">
										<thead>
											<tr>
												<th></th>
												<th>类别名称</th>
												<th>音乐数量</th>
												<th>音乐销售总量</th>
												<th>音乐销售总额</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="cate" items="${requestScope.cateInfoList}"
												varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td id="cateName${status.index+1}">${cate.category}</td>
													<td id="cateMusicNum${status.index+1}">${cate.musicNum}</td>
													<td id="cateSaleNum${status.index+1}">${cate.totalSaleNum}</td>
													<td id="cateSale${status.index+1}">${cate.totalSale}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div id="cateNum" style="display: none">${fn:length(requestScope.cateInfoList)}</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<!-- Column -->
					<div class="col-12">
						<div class="card" id="charts-card">
							<div class="card-body">
								<div class="d-flex">
									<div>
										<h5 class="card-title">销售量饼图</h5>
									</div>
								</div>
								<div id="statisticChart-his"
									style="width: 1000px; height: 400px;"></div>

								<div class="d-flex">
									<div>
										<h5 class="card-title">销售额直方图</h5>
									</div>
								</div>
								<div id="statisticChart-pie"
									style="width: 1000px; height: 400px;"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<!-- Column -->
					<div class="col-12">
						<div class="card" id="log-card">
							<div class="card-body">
								<div class="d-flex">
									<div>
										<h5 class="card-title">用户日志记录</h5>
									</div>
								</div>
								<div class="table-responsive m-t-20 no-wrap">
									<table class="table vm no-th-brd pro-of-month">
										<thead>
											<tr>
												<th></th>
												<th>用户id</th>
												<th>日志信息</th>
												<th>操作时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="log" items="${requestScope.logList}"
												varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${log.user_id}</td>
													<td>${log.message}</td>
													<td>${log.time}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div id="cateNum" style="display: none">${fn:length(requestScope.cateInfoList)}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function buttonDetail(id) {
			window.location.replace("musicInfoForSaler/" + id);
		}
		function buttonAddMusic() {
			window.location.replace("addMusicForm");
		}
		function buttonIndex() {
			window.location.replace("index");
		};
		$(document).ready(function() {
			var hisData = [];
			var pieData = [];
			for (var i = 0; i < parseInt($("#cateNum").text()); i++) {
				var test = $("#cateSaleNum" + (i + 1)).text();
				var saleNum = parseInt($("#cateSaleNum" + (i + 1)).text());
				var cateName = $("#cateName" + (i + 1)).text();
				var sale = parseFloat($("#cateSale" + (i + 1)).text())
				//直方图画销售量
				hisData.push({
					value : saleNum,
					name : cateName
				});
				//饼图画销售额
				pieData.push({
					value : sale,
					name : cateName
				})
			}
			drawPiePic('statisticChart-his', hisData);
			drawProductPic('statisticChart-pie', pieData);
		});
		function buttonDelete(id) {
			$.ajax({
				url : "deleteMusic",
				type : 'GET',
				data : {
					id : id
				},
				dataType : 'json',
				success : function(json) {
					alert(json.message);
					window.location.replace("manage");
				},
				error : function() {
					alert("请求删除音乐出错！");
				}
			});
		}
	</script>
</body>
</html>