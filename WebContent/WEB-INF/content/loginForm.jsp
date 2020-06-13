<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="${path}/CSS/loginCSS/bootstrap.min.css">
<link rel="stylesheet" href="${path}/CSS/loginCSS/animate.css">
<link rel="stylesheet" href="${path}/CSS/loginCSS/style.css">
</head>
<body class="style-3">
	<div class="container">
		<div id="message-area" style="display: none">${requestScope.message}</div>
		<div class="row">
			<div class="col-md-4 col-md-push-8" style="width: 40%">
				<form id="loginUserForm" action="loginUser" method="post"
					class="fh5co-form animate-box" data-animate-effect="fadeInRight">
					<h2>用户登录</h2>
					<div class="form-group">
						<label for="loginname" class="sr-only">登录名：</label> <input
							type="text" class="form-control" id="loginname" name="loginname"
							placeholder="Loginname" autocomplete="off">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">密码：</label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="Password" autocomplete="off">
					</div>
					<div class="form-group">
						<input type="submit" value="登录" class="btn btn-primary">
						<button type="button" id="changerSalerLoginFormButton"
							onclick="changerSalerLoginForm()" class="btn btn-primary">切换销售登录</button>
						<button type="button" onclick="buttonIndex()"
							class="btn btn-primary">返回首页</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-push-8" style="width: 40%">
				<form id="loginSalerForm" action="loginSaler" method="post"
					style="display: none" class="fh5co-form animate-box"
					data-animate-effect="fadeInRight">
					<h2>销售登录</h2>
					<div class="form-group">
						<label for="loginid" class="sr-only">登录账号：</label> <input
							type="text" class="form-control" id="loginid" name="loginid"
							placeholder="Loginid" autocomplete="off">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">密码：</label> <input
							type="password" class="form-control" name="password"
							placeholder="Password" autocomplete="off">
					</div>
					<div class="form-group">
						<input type="submit" value="登录" class="btn btn-primary">
						<button type="button" id="changerManagerLoginFormButton"
							onclick="changerManagerLoginForm()" class="btn btn-primary">切换管理员登录</button>
						<button type="button" onclick="buttonIndex()"
							class="btn btn-primary">返回首页</button>
					</div>
				</form>
			</div>

		</div>
		<div class="row">
			<div class="col-md-4 col-md-push-8" style="width: 40%">
				<form id="loginManagerForm" action="loginManager" method="post"
					style="display: none" class="fh5co-form animate-box"
					data-animate-effect="fadeInRight">
					<h2>管理员登录</h2>
					<div class="form-group">
						<label for="loginid" class="sr-only">登录账号：</label> <input
							type="text" class="form-control" id="loginid" name="loginid"
							placeholder="Loginid" autocomplete="off">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">密码：</label> <input
							type="password" class="form-control" name="password"
							placeholder="Password" autocomplete="off">
					</div>
					<div class="form-group">
						<input type="submit" value="登录" class="btn btn-primary">
						<button type="button" id="changerUserLoginFormButton"
							onclick="changerUserLoginForm()" class="btn btn-primary">切换用户登录</button>
						<button type="button" onclick="buttonIndex()"
							class="btn btn-primary">返回首页</button>
					</div>
				</form>
			</div>

		</div>
		<div class="row" style="padding-top: 60px; clear: both;">
			<div class="col-md-12 text-center">
				<p>
					<small>&copy; All Rights Reserved. More Information <a
						href="index" target="_blank" title="音乐充电小店">音乐充电小店</a>
					</small>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function buttonIndex() {
			window.location.replace("index");
		}
		function changerUserLoginForm() {
			$("#loginManagerForm").hide();
			$("#loginUserForm").show();
		}

		function changerSalerLoginForm() {
			$("#loginSalerForm").show();
			$("#loginUserForm").hide();
		}
		function changerManagerLoginForm(){
			$("#loginManagerForm").show();
			$("#loginSalerForm").hide();
		}
	</script>
</body>
</html>