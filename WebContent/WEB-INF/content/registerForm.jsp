<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"
	scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
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
		<div class="row">
			<div class="col-md-4 col-md-push-8">
				<form id="registerForm" action="register" method="post"
					class="fh5co-form animate-box" data-animate-effect="fadeInRight">
					<h2>用户注册</h2>
					<div class="form-group">
						<label for="loginname" class="sr-only">昵称：</label> <input
							type="text" class="form-control" id="loginname" name="loginname"
							placeholder="Loginname" autocomplete="off">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">密码：</label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="Password" autocomplete="off">
					</div>
					<div class="form-group">
						<label for="confirmPassword" class="sr-only">密码：</label> <input
							type="password" class="form-control" id="confirmPassword"
							name="confirmPassword" placeholder="confirmPassword"
							autocomplete="off">
					</div>
					<div class="form-group">
						<input type="submit" id="submit" value="注册"
							class="btn btn-primary" disabled="disabled">
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
		$("#confirmPassword").bind('blur', function() {
			var password = $("#password").val();
			var confirmPassword = $("#confirmPassword").val();
			//console.log(password);
			//console.log(confirmPassword);
			if (password != null && password == confirmPassword) {
				$("#submit").removeAttr("disabled");
			}
			else
			{
				alert("两次输入的密码不一致！");
			}
		});
		function buttonIndex() {
			window.location.replace("index");
		};
	</script>
</body>
</html>