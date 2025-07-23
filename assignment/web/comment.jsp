
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Comments</title></head>
<body>
<h2>Comments for Game</h2>
<c:forEach var="cmt" items="${comments}">
    <div>
        <p>${cmt.commentText}</p>
        <small>By User ID: ${cmt.userID} on ${cmt.commentDate}</small>
        <hr>
    </div>
</c:forEach>
</body>
</html>
