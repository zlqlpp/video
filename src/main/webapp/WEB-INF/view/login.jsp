<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="utf-8"> 
	<title> </title>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="
    margin-top: 20%;
    margin-left: 20%;
    font-size: 40;
">
 <h3>${err }</h3>
<form role="form" action="login" method="post">
	 	账号<input type="text"   id="userName" name="userName"   style="
    padding-left: 1px;
    padding-right: 1px;
    margin-left: 2px;
    margin-bottom: 10px;
"><br/>
		密码<input type="text"    id="passWord" name="passWord"  style="
    padding-left: 1px;
    margin-left: 2px;
    margin-top: 10px;
    border-top-width: 2px;
"  ><br/>
<input type="hidden" name="video" value="${video }">
		<button type="submit"  style="
    margin-top: 20px;
">登录</button>
</form>
 
</body>
</html>