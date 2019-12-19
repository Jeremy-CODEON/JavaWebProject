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
<title>订单详情页面</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>

<link
	href="<%=basePath%>CSS/indexCSS/assets/node_modules/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=basePath%>CSS/indexCSS/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css"
	rel="stylesheet">
<!-- This page CSS -->
<!-- chartist CSS -->
<link
	href="<%=basePath%>CSS/indexCSS/assets/node_modules/morrisjs/morris.css"
	rel="stylesheet">
<!--c3 CSS -->
<link
	href="<%=basePath%>CSS/indexCSS/assets/node_modules/c3-master/c3.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=basePath%>CSS/indexCSS/html/css/style.css"
	rel="stylesheet">
<!-- Dashboard 1 Page CSS -->
<link href="<%=basePath%>CSS/indexCSS/html/css/pages/dashboard1.css"
	rel="stylesheet">
<!-- You can change the theme colors from here -->
<link href="<%=basePath%>CSS/indexCSS/html/css/colors/default.css"
	id="theme" rel="stylesheet">
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.1/build/pure-min.css">
</head>
<body class="fix-header fix-sidebar card-no-border">
	<div id="main-wrapper">
		<header class="topbar" style="margin-top: 0px;">
			<nav class="navbar top-navbar navbar-expand-md navbar-light">
				<div class="navbar-header">
					<a class="navbar-brand" href="<%=basePath%>index"> <!-- Logo icon -->
						<b> <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
							<!-- Dark Logo icon --> <img
							src="<%=basePath%>ICON/logo-icon.png" alt="homepage"
							class="dark-logo" />
					</b> <span> 音乐充电小店 </span>
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
							href="<%=basePath%>userInfo"
							class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span
								class="hidden-md-down">返回 &nbsp;</span></a></li>
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
							href=""><span class="hide-menu">音乐类别</span> </a></li>
						<li><a class="waves-effect waves-dark"
							href="<%=basePath%>searchMusicListByCategory1" aria-expanded="false"><i
								class="fa fa-tachometer"></i><span class="hide-menu">纯音乐</span></a>
						</li>
						<li><a class="waves-effect waves-dark"
							href="<%=basePath%>searchMusicListByCategory2" aria-expanded="false"><i
								class="fa fa-user-circle-o"></i><span class="hide-menu">欧美民谣</span></a>
						</li>
						<li><a class="waves-effect waves-dark"
							href="<%=basePath%>searchMusicListByCategory3" aria-expanded="false"><i
								class="fa fa-table"></i><span class="hide-menu">国语</span></a></li>
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
				<div class="row">
					<!-- Column -->
					<div class="col-lg-8">
						<div class="card">
							<!-- Tab panes -->
							<div class="card-body">
								<div class="d-flex">
									<div>
										<h5 class="card-title">订单详情</h5>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-12">订单编号</label>
									<div class="col-md-12">
										<div class="form-control form-control-line">${order.id}</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-12">订单总额</label>
									<div class="col-md-12">
										<div class="form-control form-control-line">${order.costSum}</div>
									</div>
								</div>
								<c:choose>
									<c:when test="${order.state==0}">
										<div class="form-group">
											<label class="col-md-12">订单状态</label>
											<div class="col-md-12">
												<div class="form-control form-control-line">未完成初始化！</div>
											</div>
										</div>
									</c:when>
									<c:when test="${order.state==1}">
										<div class="form-group">
											<label class="col-md-12">订单状态</label>
											<div class="col-md-12">
												<div class="form-control form-control-line">待确认！</div>
											</div>
										</div>
									</c:when>
									<c:when test="${order.state==2}">
										<div class="form-group">
											<label class="col-md-12">订单状态</label>
											<div class="col-md-12">
												<div class="form-control form-control-line">已确认，待完成！</div>
											</div>
										</div>
									</c:when>
									<c:when test="${order.state==3}">
										<div class="form-group">
											<label class="col-md-12">订单状态</label>
											<div class="col-md-12">
												<div class="form-control form-control-line">已完成！</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="form-group">
											<label class="col-md-12">订单状态</label>
											<div class="col-md-12">
												<div class="form-control form-control-line">状态获取出错！</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<!-- Column -->
					<div class="col-lg-8">
						<div class="card">
							<div class="card-body">
								<div class="d-flex">
									<div>
										<h5 class="card-title">订单音乐列表</h5>
									</div>
								</div>
								<c:forEach var="orderMusic" items="${order.orderMusicList}"
									varStatus="status">
									<div id="info">${status.index+1}:${orderMusic.music.name}--${orderMusic.music.singer}--${orderMusic.music.album}</div>
									<div id="category">类别：${orderMusic.music.category}</div>
									<div id="category">单价：${orderMusic.music.cost}</div>
								</c:forEach>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- 								<div id="content-area"> -->
	<%-- 									<div id="id">${order.id}</div> --%>
	<%-- 									<div id="costSum">${order.costSum}</div> --%>
	<%-- 									<c:choose> --%>
	<%-- 										<c:when test="${order.state==0}"> --%>
	<!-- 											<div id="state">未完成初始化</div> -->
	<%-- 										</c:when> --%>
	<%-- 										<c:when test="${order.state==1}"> --%>
	<!-- 											<div id="state">待确认！</div> -->
	<%-- 										</c:when> --%>
	<%-- 										<c:when test="${order.state==2}"> --%>
	<!-- 											<div id="state">已确认，待完成！</div> -->
	<%-- 										</c:when> --%>
	<%-- 										<c:when test="${order.state==3}"> --%>
	<!-- 											<div id="state">已完成！</div> -->
	<%-- 										</c:when> --%>
	<%-- 										<c:otherwise> --%>
	<!-- 											<div id="state">状态获取出错！</div> -->
	<%-- 										</c:otherwise> --%>
	<%-- 									</c:choose> --%>
	<%-- 									<c:forEach var="orderMusic" items="${order.orderMusicList}" --%>
	<%-- 										varStatus="status"> --%>
	<%-- 										<div id="name">${orderMusic.music.name}</div> --%>
	<%-- 										<div id="singer">${orderMusic.music.singer}</div> --%>
	<%-- 										<div id="description">${orderMusic.music.description}</div> --%>
	<%-- 										<div id="album">${orderMusic.music.album}</div> --%>
	<%-- 										<div id="duration">${orderMusic.music.duration}</div> --%>
	<%-- 										<div id="category">${orderMusic.music.category}</div> --%>
	<%-- 										<div id="category">${orderMusic.music.cost}</div> --%>
	<%-- 									</c:forEach> --%>
	<!-- 								</div> -->
</body>
</html>