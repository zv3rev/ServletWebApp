<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <h1>Sign up</h1>
    <form method="post" action="${pageContext.request.contextPath}/signup">
        <input type="text" name="username" value="Username"><br/>
        <input type="text" name="password" value="Password"><br/>
        <input type="email" name="email" value="Email"><br/>
        <input type="number" name="age" value="0"><br/>
        <input type="submit" value="Sign up">
    </form>
</body>
</html>
