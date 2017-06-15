<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	
    <link type="text/css" rel="stylesheet" href="css/login.css">
    <link rel="shortcut icon" href="<%=basePath%>images/logo.png">
    
</head>
<body class="login_bj">
	<div class="zhuce_body">
		
<form action="<c:url value='/LoginServlet'/>" method="post">
		<div class="zhuce_kong login_kuang">
			<div class="zc">
				<div class="bj_bai">
					<h3>登录</h3>
					<div>
					<input name="cardId" type="text" id="cardId" placeholder="账号" cssClass="kuang_txt phone"/>
					<input name="password" type="password" id="password" placeholder="密码" cssClass="kuang_txt phone"/>
							</div>
						 <input type="submit" name="Submit" value="登录" />
				</div>
				
			</div>
			</form>
		</div>
	
</body>
</html>