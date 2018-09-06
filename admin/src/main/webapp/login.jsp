<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MKTM管理系统</title>
    <link rel="Stylesheet" type="text/css" href="css/login.css"/>
    <link rel="Stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.3.js"></script>
</head>
<body onload='document.f.j_username.focus();'>
<div class="container">
    <form class="form-signin" name='f' action="login" method='post'>
        <h3 class="form-signin-heading">MKTM管理系统</h3>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <input type="text" name="j_username" placeholder="用户名" class="input-block-level"/>
        <input type="password" name="j_password" placeholder="密码" class="input-block-level"/>
        <input name="submit" type="submit" value=" 登录 " class="btn btn-large btn-primary"/>
        <br/>
    </form>
</div>
</body>
</html>
