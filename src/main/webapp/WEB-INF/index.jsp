<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Quest</title>
    <link href="${contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/css/bootstrap-utilities.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <br>
    <form name="start_form" method="post" action="${contextPath}/startgame" onsubmit="return validate_form();">
        <h2 class="h2 mb-3 font-weight-normal">Пролог</h2>
        <div class="text-wrap" style="width: 50rem;">
            <p>Ты стоишь в космическом порту и готов подняться на борт своего корабля. Разве ты не об этом мечтал? Стать
                капитаном галактического судна с экипажем. Вперед!</p>
            <br>
            <p>Для старта осталась самая малость - указать как экипажу к вам обращаться, господин капитан!</p>
        </div>
        <div>
            <label for="inputName">Ваше имя: </label>
            <input type="text" id="inputName" name="name">
            <button type="submit" class="btn btn-success btn-sm">Начать игру</button>
        </div>
    </form>
</div>

<script type="text/javascript">

    function validate_form() {
        valid = true;

        if (document.start_form.name.value === "") {
            alert("Пожалуйста заполните поле 'Ваше имя'.");
            valid = false;
        }

        return valid;
    }

</script>

</body>
</html>
