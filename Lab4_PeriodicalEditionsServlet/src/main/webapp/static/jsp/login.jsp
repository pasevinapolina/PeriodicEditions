<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>

    <link href="../../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/custom_styles.css" rel="stylesheet">

    <title>Вход | Периодические издания</title>
</head>
<body>

<c:import url="header.jsp" />

<div class="container">
    <div class="jumbotron">
        <h2><strong>Вход в систему</strong></h2>
    </div>

    <div class="col-md-8">
        <form name="loginForm" method="POST" action="PeriodicEdition" role="form">
            <input type="hidden" name="command" value="login"/>

            <div class="form-group">
                <label for="login">Логин</label>
                <input id="login" type="text" name="login" class="form-control" value="" placeholder="Введите имя пользователя" />
            </div>

            <div class="form-group">
                <label for="password">Пароль</label>
                <input id="password" type="password" name="password" class="form-control" value="" placeholder="Введите пароль" />
            </div>

            <div class="form-group">
                <c:if test="${not empty errorLoginPassMessage}">
                <div class="alert alert-danger">
                    <p>
                        ${errorLoginPassMessage}
                    </p>
                </div>
                </c:if>
            </div>

            <input type="submit" value="Войти" class="btn btn-default"/>
        </form>
    </div>
</div>

<c:import url="footer.jsp" />

</body>
</html>
