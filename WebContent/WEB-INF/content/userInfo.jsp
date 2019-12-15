<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<html>
<head>
<meta charset="UTF-8">
<title>个人中心</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"
	type="text/javascript"></script>

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
					<!-- User profile and search -->
					<!-- ============================================================== -->
					<ul class="navbar-nav my-lg-0">
						<!-- ============================================================== -->
						<!-- Profile -->
						<!-- ============================================================== -->
						<li class="nav-item dropdown u-pro"><a href="index"
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
							<div class="card-body">
								<center class="m-t-30">
									<c:choose>
										<c:when test="${sessionScope.user.avatar!=NULL}">
											<img src="${path}${sessionScope.user.avatar}"
												class="img-circle" width="150" />
										</c:when>
										<c:otherwise>
											<img src="${path}/IMAGES/defaultAvatar.jpeg"
												class="img-circle" width="150" />
										</c:otherwise>
									</c:choose>

									<h4 class="card-title m-t-10">${sessionScope.user.loginname}</h4>
									<h6 class="card-subtitle">音乐充电小店用户</h6>
								</center>
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
										<h5 class="card-title">购物车列表</h5>
									</div>
								</div>
								<div class="table-responsive m-t-20 no-wrap">
									<form action="orderConfirmPage">
										<table class="table vm no-th-brd pro-of-month">
											<thead>
												<tr>
													<th></th>
													<th>编号</th>
													<th>音乐标题</th>
													<th>歌手</th>
													<th>专辑</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="cart" items="${requestScope.cartList}"
													varStatus="status">
													<tr>
														<td><input name="purchaseList" type="checkbox"
															style="position: inherit; opacity: unset"
															value="${cart.music.id}"></td>
														<td>${status.index+1}</td>
														<td>${cart.music.name}</td>
														<td>${cart.music.singer}</td>
														<td>${cart.music.album}</td>
														<td>
															<button type="button"
																onclick="buttonMusicDetail('${cart.music.id}')"
																class="pure-button">详情</button>
															<button type="button"
																onclick="buttonRemoveFromCart('${cart.id}')"
																class="pure-button">移除购物车</button>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<input type="submit" value="确认购买" class="pure-button" />
									</form>
								</div>
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
										<h5 class="card-title">订单列表</h5>
									</div>
								</div>
								<div class="table-responsive m-t-20 no-wrap">
									<table class="table vm no-th-brd pro-of-month">
										<thead>
											<tr>
												<th></th>
												<th>订单编号</th>
												<th>订单总额</th>
												<th>订单状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="order" items="${requestScope.orderList}"
												varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${order.id}</td>
													<td>${order.costSum}</td>
													<c:choose>
														<c:when test="${order.state==0}">
															<td>未完成初始化</td>
														</c:when>
														<c:when test="${order.state==1}">
															<td>待确认！</td>
														</c:when>
														<c:when test="${order.state==2}">
															<td>已确认，待完成！</td>
														</c:when>
														<c:when test="${order.state==3}">
															<td>已完成！</td>
														</c:when>
														<c:otherwise>
															<td>状态获取出错！</td>
														</c:otherwise>
													</c:choose>
													<td>
														<button onclick="buttonOrderDetail('${order.id}')"
															class="pure-button">订单详情</button>
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
					<div class="col-lg-8">
						<div class="card">
							<!-- Tab panes -->
							<div class="card-body">
								<form action="alterUserInfo"
									class="form-horizontal form-material"
									enctype="multipart/form-data" method="post">
									<div class="d-flex">
										<div>
											<h5 class="card-title">用户信息修改</h5>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">用户id</label>
										<div class="col-md-12">
											<input type="text" placeholder="${sessionScope.user.id}"
												name="id" class="form-control form-control-line"
												readonly="true">
										</div>
									</div>
									<div class="form-group">
										<label for="example-email" class="col-md-12">昵称</label>
										<div class="col-md-12">
											<input type="text"
												placeholder="${sessionScope.user.loginname}"
												class="form-control form-control-line" name="loginname"
												id="example-email">
										</div>
									</div>
									<c:choose>
										<c:when test="${sessionScope.user.username!=NULL}">
											<div class="form-group">
												<label class="col-md-12">真实姓名</label>
												<div class="col-md-12">
													<input type="text" name="username"
														class="form-control form-control-line"
														placeholder="${sessionScope.user.username}">
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-group">
												<label class="col-md-12">真实姓名</label>
												<div class="col-md-12">
													<input type="text" name="username"
														class="form-control form-control-line">
												</div>
											</div>
										</c:otherwise>
									</c:choose>
									<div class="form-group">
										<label class="col-md-12">上传用户头像</label>
										<div class="col-md-12">
											<input type="file" placeholder="请选择本地图片" name="avatar"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">新密码</label>
										<div class="col-md-12">
											<input type="password" name="password" placeholder="请输入新密码"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">确认新密码</label>
										<div class="col-md-12">
											<input type="password" name="confirmPassword"
												placeholder="请确认新密码" class="form-control form-control-line">
										</div>
									</div>
									<c:choose>
										<c:when test="${sessionScope.user.phone!=NULL}">
											<div class="form-group">
												<label class="col-md-12">电话</label>
												<div class="col-md-12">
													<input type="tel" name="phone"
														placeholder="${sessionScope.user.phone}"
														class="form-control form-control-line">
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-group">
												<label class="col-md-12">电话</label>
												<div class="col-md-12">
													<input type="tel" name="phone" placeholder=""
														class="form-control form-control-line">
												</div>
											</div>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${sessionScope.user.mail!=NULL}">
											<div class="form-group">
												<label class="col-md-12">e-mail</label>
												<div class="col-md-12">
													<input type="email" name="mail"
														placeholder="${sessionScope.user.mail}"
														class="form-control form-control-line">
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-group">
												<label class="col-md-12">e-mail</label>
												<div class="col-md-12">
													<input type="email" name="mail" placeholder=""
														class="form-control form-control-line">
												</div>
											</div>
										</c:otherwise>
									</c:choose>
									<input type="submit" value="确认修改信息" class="pure-button" />
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

		function buttonMusicDetail(id) {
			window.location.replace("musicDetail/" + id);
		}

		function buttonRemoveFromCart(id) {
			$.ajax({
				url : "removeFromCart",
				type : 'GET',
				data : {
					id : id
				},
				dataType : 'json',
				success : function(json) {
					alert(json.message);
					window.location.replace("userInfo");
				},
				error : function() {
					alert("请求移除购物车出错！");
				}
			});
		}
		function buttonIndex() {
			window.location.replace("index");
		}
		function buttonOrderDetail(id) {
			window.location.replace("orderDetail/" + id);
		}
	</script>
</body>
</html>