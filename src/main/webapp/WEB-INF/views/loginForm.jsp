<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인페이지입니다.</h1>
<hr/>
<form action="/loginProc" method="post">
	<input type="text" name="username"/>
	<input type="password" name="password"/>
	<button>로그인</button>
</form>
</body>
</html>