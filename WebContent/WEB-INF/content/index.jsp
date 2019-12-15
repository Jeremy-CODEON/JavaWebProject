<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<html>
<head>
<meta charset="UTF-8">
<title>音乐充电站</title>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>

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
					<a class="navbar-brand" href="index"> <!-- Logo icon --> <b>
							<!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
							<!-- Dark Logo icon --> <img src="${path}/ICON/logo-icon.png"
							alt="homepage" class="dark-logo" />
					</b> <span> 音乐充电小店 </span>
					</a>
				</div>
				<!-- ============================================================== -->
				<!-- End Logo -->
				<!-- ============================================================== -->
				<div class="navbar-collapse">
					<!-- ============================================================== -->
					<!-- toggle and nav items -->
					<!-- ============================================================== -->
					<ul class="navbar-nav mr-auto">
						<li class="nav-item">
							<form action="searchMusicListByWord" class="app-search">
								<div id="search-title"
									style="display: inline; font-size: 18px; margin: 10px;">查询音乐</div>

								<input id="search-input" type="text" name="searchInput"
									class="form-control" placeholder="Input Search word"
									style="display: inline; width: 500px;" /> <input
									id="search-submit" type="submit" value="确定"
									class="nav-link waves-effect waves-dark fa fa-bars pure-button"
									style="display: inline" /> <a href="index"
									class="nav-link waves-effect waves-dark fa fa-bars ">显示全部</a>
							</form>
					</ul>
					<!-- ============================================================== -->
					<!-- User profile and search -->
					<!-- ============================================================== -->
					<ul class="navbar-nav my-lg-0">
						<!-- ============================================================== -->
						<!-- Profile -->
						<!-- ============================================================== -->
						<c:choose>
							<c:when test="${sessionScope.user!=NULL}">
								<li class="nav-item dropdown u-pro">
								<a href="userInfo"
									class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<c:choose>
											<c:when test="${sessionScope.user.avatar!=NULL}">
												<img src="${path}${sessionScope.user.avatar}" alt="user"
													class="" />
											</c:when>
											<c:otherwise>
												<img src="${path}/IMAGES/defaultAvatar.jpeg" alt="user"
													class="" />
											</c:otherwise>
									</c:choose> 
									<span class="hidden-md-down">${sessionScope.user.loginname}&nbsp;</span>
								</a>
								</li>
								<li class="nav-item dropdown u-pro"><a href="logout"
									class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> 
									
									<span class="hidden-md-down">退出登录 &nbsp;</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item dropdown u-pro"><a href="loginForm"
									class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"><span class="hidden-md-down">请登录
											&nbsp;</span></a></li>
								<li class="nav-item dropdown u-pro"><a href="registerForm"
									class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"><span class="hidden-md-down">注册
											&nbsp;</span></a></li>
							</c:otherwise>
						</c:choose>

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
							href="searchMusicListByCategory1" aria-expanded="false"><i
								class="fa fa-tachometer"></i><span class="hide-menu">纯音乐</span></a>
						</li>
						<li><a class="waves-effect waves-dark"
							href="searchMusicListByCategory2" aria-expanded="false"><i
								class="fa fa-user-circle-o"></i><span class="hide-menu">欧美民谣</span></a>
						</li>
						<li><a class="waves-effect waves-dark"
							href="searchMusicListByCategory3" aria-expanded="false"><i
								class="fa fa-table"></i><span class="hide-menu">国语</span></a></li>
						<c:if test="${sessionScope.user!=NULL}">
							<li><a class="waves-effect waves-dark" aria-expanded="false"
								href="userInfo"><span class="hide-menu">个人中心</span> </a></li>
						</c:if>
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
						<div class="card">
							<div class="card-body">
								<div class="d-flex">
									<div>
										<h5 class="card-title">音乐列表</h5>
									</div>
								</div>
								<div class="table-responsive m-t-20 no-wrap">
									<table class="table vm no-th-brd pro-of-month">
										<thead>
											<tr>
												<th></th>
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
													<td style="width: 50px;"><span class="round">${status.index+1}</span></td>
													<td>${music.name}</td>
													<td>${music.singer}</td>
													<td>${music.album}</td>

													<td>
														<button onclick="buttonDetail('${music.id}')"
															class="pure-button">详情</button> <c:choose>
															<c:when test="${music.state==1}">
																<button onclick="buttonAddToCart('${music.id}')"
																	class="pure-button">加入购物车</button>
															</c:when>
															<c:when test="${music.state==0}"></c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
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
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function buttonDetail(id) {
			window.location.replace("musicDetail/" + id);
		};
		function buttonAddToCart(music_id) {
			$.ajax({
				url : "addToCart",
				type : 'GET',
				data : {
					music_id : music_id
				},
				dataType : 'json',
				success : function(json) {
					alert(json.message);
				},
				error : function() {
					alert("请求加入购物车出错！");
				}
			});
		};
		function buttonIndex() {
			window.location.replace("index");
		};
	</script>
</body>
</html>
