<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
</head>
<body>
欢迎[${sessionScope.user.username}]访问
<c:forEach items="${requestScope.book_list}" var="book">
  ${book.name} ${book.author}
</c:forEach>
</body>
</html>