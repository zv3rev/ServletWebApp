<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Sign in</title>
</head>
<body>
  <h1>Sign in</h1>
  <form method="post" action="${pageContext.request.contextPath}/signin">
    <input type="text" name="username" value="Username"><br/>
    <input type="text" name="password" value="Password"><br/>
    <input type="submit" value="Sign in">
  </form>
  <c:if test="${requestScope.getOrDefault('wrongPassword',false) == true}">
    Wrong username or password
  </c:if>
</body>
</html>
