<%-- 
    Document   : register
    Created on : Jul 22, 2025, 8:50:06 AM
    Author     : ASUS
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký</title>
    <link rel="stylesheet" href="assets/css/register.css">
</head>
<body>
    <h2>Đăng ký tài khoản</h2>
    <form method="post" action="MainController">
        <input type="hidden" name="action" value="register"/>
        <label>Tên đăng nhập:</label><br/>
        <input type="text" name="username" required><br/><br/>

        <label>Mật khẩu:</label><br/>
        <input type="password" name="password" required><br/><br/>

        <label>Email:</label><br/>
        <input type="email" name="email" required><br/><br/>

        <input type="submit" value="Đăng ký">
    </form>

    <p style="color: red">${ERROR}</p>
    <p style="color: green">${MESSAGE}</p>
</body>
</html>