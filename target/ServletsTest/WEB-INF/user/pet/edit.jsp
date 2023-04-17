<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zv3re
  Date: 16.04.2023
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Edit pet</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/user/pet/edit">
        <select name="id" required>
            <c:forEach var="pet" items="${pets}">
                <option value="${pet.getId()}"><c:out value="${pet.getNickname()}"/></option>
            </c:forEach>
        </select>
        Nickname<br/>
        <input type="text" name="nickname"><br/>
        Age<br/>
        <input type="date" value="0" required name="birthday"><br/>
        Type<br/>
        <input type="text" name="type"><br/>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
