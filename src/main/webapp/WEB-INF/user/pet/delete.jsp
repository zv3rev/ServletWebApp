<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zv3re
  Date: 17.04.2023
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Delete pet</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/pet/delete">
  <select name="id" required>
    <c:forEach var="pet" items="${pets}">
      <option value="${pet.getId()}"><c:out value="${pet.getNickname()}"/></option>
    </c:forEach>
  </select>
  <input type="submit" value="Submit">
</form>
</body>
</html>
