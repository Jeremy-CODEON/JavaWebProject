<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>确认订单</title>
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
						<li class="nav-item dropdown u-pro"><a href="userInfo"
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
								<div class="d-flex">
									<div>
										<h5 class="card-title">购物车列表</h5>
									</div>
								</div>
								<div id="order-num">订单编号：${requestScope.forConfirmOrder.id}</div>
								<div id="order-costSum">订单总额：${requestScope.forConfirmOrder.costSum}</div>
								<!-- 显示订单状态 -->
								<c:choose>
									<c:when test="${requestScope.forConfirmOrder.state==0}">
										<div id="order-state">订单状态：订单未完成初始化，请刷新页面！</div>
									</c:when>
									<c:when test="${requestScope.forConfirmOrder.state==1}">
										<div id="order-state">订单状态：订单待确认！</div>
									</c:when>
									<c:when test="${requestScope.forConfirmOrder.state==2}">
										<div id="order-state">订单状态：订单已确认，待完成！</div>
									</c:when>
									<c:when test="${requestScope.forConfirmOrder.state==3}">
										<div id="order-state">订单状态：订单已完成！</div>
									</c:when>
									<c:otherwise>
										<div id="order-state">订单状态：订单状态获取出错！</div>
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
										<h5 class="card-title">购物车列表</h5>
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
											</tr>
										</thead>
										<tbody>
											<c:forEach var="orderMusic"
												items="${requestScope.forConfirmOrderMusicList}"
												varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${orderMusic.music.name}</td>
													<td>${orderMusic.music.singer}</td>
													<td>${orderMusic.music.album}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<img src="${path}/IMAGES/payQRcode.png" alt="payQRcode"
										style="width: 200px; margin-left: auto; margin-right: auto; display: block;"
										class="" />
									<button id="confirmButton"
										onclick="buttonConfirmOrder('${requestScope.forConfirmOrder.id}')"
										style="display: block; margin-left: auto; margin-right: auto; margin-top: 10px;"
										class="pure-button">确认支付订单</button>
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
		}
		function buttonConfirmOrder(id) {
			$("#confirmButton").attr("disabled","true");
			$("#confirmButton").text("订单正在确认中，请稍等");
			$.ajax({
				url : "confirmOrder",
				type : 'GET',
				data : {
					order_id : id
				},
				dataType : 'json',
				success : function(json) {
					alert(json.message);
					window.location.replace("index");
				},
				error : function() {
					alert("请求发送确认订单邮件出错！请检查邮箱地址是否有效！");
					$("#confirmButton").attr("disabled",false);
					$("#confirmButton").text("确认支付订单");
				}
			});
		}
	</script>
</body>
</html>