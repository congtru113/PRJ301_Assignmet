<%-- 
    Document   : login
    Created on : Apr 26, 2025, 8:58:20 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
    <form method="post" action="MainController">
        <input type="hidden" name="action" value="login"/>
        <label>Tên đăng nhập:</label><br/>
        <input type="text" name="username" required><br/><br/>
        
        <label>Mật khẩu:</label><br/>
        <input type="password" name="password" required><br/><br/>
        <c:if test="${not empty requestScope.message}">
            <p class="text-danger">${requestScope.message}</p>
        </c:if>
            <input type="submit" value="Đăng nhập"><br/>
            <a href="register.jsp">đăng kí</a>
    </form>

</body>
</html>