<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Pet editor</title>
</head>
<body>
<c:set var="action" value="${requestScope.get('action')}"></c:set>

<form method="post" action="${pageContext.request.contextPath}/user/peteditor">
    <input type="hidden" name="action" value="${action}">
    <c:if test="${action != 'add'}">
        <select name="id" required>
            <c:forEach var="pet" items="${pets}">
                <option value="${pet.getId()}"><c:out value="${pet.getNickname()}"/></option>
            </c:forEach>
        </select>
        <br/>
    </c:if>

    <c:if test="${action != 'delete'}">
        Nickname<br/>
        <input type="text" name="nickname"><br/>
        Age<br/>
        <input type="number" value="0" required name="age"><br/>
        Type<br/>
        <input type="text" name="type"><br/>
    </c:if>
    <input type="submit" value="Submit">
</form>
</body>
</html>
