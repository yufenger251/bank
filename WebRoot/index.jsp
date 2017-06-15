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
		<div class="logo"><a href="#"><img src="images/logo.png" width="54" height="54" border="0"></a></div>
<form action="<c:url value='/ShowServlet'/>" method="post">
		<div class="zhuce_kong login_kuang">
			<div class="zc">
				<div class="bj_bai">
					<h3>登录</h3>
					<div>
					<input name="cardId" type="text" id="cardId" placeholder="银行卡号" cssClass="kuang_txt phone"/>

					<a href="reg.jsp">忘记密码？</a>
					<input name="checkbox" type="checkbox" value="" checked><span>记住我</span> 
						</div>

				</div>

				</div>
			</div>
			</form>
		</div>
	</div>

</body>
</html>