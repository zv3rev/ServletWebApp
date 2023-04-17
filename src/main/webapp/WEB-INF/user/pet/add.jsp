<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Add pet</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/user/pet/add">
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
