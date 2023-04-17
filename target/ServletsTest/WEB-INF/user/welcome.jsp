<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Profile page</title>
</head>
<body>
<h1>
Hello, ${sessionScope.get("loggedUsername")}!</h1>
Your pets here
<c:forEach items="${pets}" var="pet">
    <ul>
        <li>${pet.toString()}</li>
    </ul>
</c:forEach>
<form method="get" action="${pageContext.request.contextPath}/user/pet/add">
    <input type="submit" value="Add">
</form>
<form method="get" action="${pageContext.request.contextPath}/user/pet/edit">
    <input type="submit" value="Edit">
</form>
<form method="get" action="${pageContext.request.contextPath}/user/pet/delete">
    <input type="submit" value="Delete">
</form>
</body>
</html>
