<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
  <link href="${contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>

<h6>Имя пользователя: ${user.getName()}</h6>
<h6>Количество игр: ${user.getGamesCount()}</h6>

</body>
</html>
