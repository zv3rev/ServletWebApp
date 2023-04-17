<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<body>
<h2>Hello World!</h2>
<form method="get" action="${pageContext.request.contextPath}/signup">
    <input type="submit" value="Sign Up">
</form>
<form method="get" action="${pageContext.request.contextPath}/signin">
    <input type="submit" value="Sign In">
</form>
</body>
</html>
