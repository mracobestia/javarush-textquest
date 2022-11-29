<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Question</title>
    <link href="${contextPath}/css/bootstrap.css" rel="stylesheet">

    <script type="text/javascript" src="${contextPath}/js/bootstrap.js"></script>
</head>

<body>

<div class="container">
    <br>
    <h3>${requestScope.get("questionText")}</h3>
    <c:if test="${isGameEnd == false}">
        <form method="post" action="${contextPath}/question">
            <c:set var="isFirstRow" value="true"/>
            <div><c:forEach items="${answers}" var="answer">
                <div class="form-check">
                    <label class="form-check-label">
                        <c:choose>
                            <c:when test="${isFirstRow == true}">
                                <input class="form-check-input" type="radio" name="radioOptions"
                                       value="${answer.nextQuestionId}" checked>
                                <c:set var="isFirstRow" value="false"/>
                            </c:when>
                            <c:otherwise>
                                <input class="form-check-input" type="radio" name="radioOptions"
                                       value="${answer.nextQuestionId}">
                            </c:otherwise>
                        </c:choose>
                        <c:out value="${answer.text}"/>
                    </label>
                </div>
            </c:forEach></div>
            <br>
            <button type="submit" class="btn btn-success">Ответить</button>
        </form>
    </c:if>
    <c:if test="${isGameEnd == true}">
        <form method="post" action="startgame">
            <button type="submit" class="btn btn-success">Попробовать еще раз</button>
        </form>
    </c:if>
    <p>
        <a class="btn btn-link" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Посмотреть статистику
        </a>
    </p>
    <div class="collapse" id="collapseExample">
        <div class="card card-body">
            <jsp:include page="statistic.jsp" />
        </div>
    </div>
</div>

</body>
</html>
