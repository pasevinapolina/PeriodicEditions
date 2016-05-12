<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>

    <link href="../../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/custom_styles.css" rel="stylesheet">

    <title>Регистрация</title>
</head>
<body>

<c:import url="header.jsp" />

<div class="container">
    <div class="jumbotron">
        <h2><strong>Присоединяйтесь</strong></h2>
        <p class="lead">Получите еще больше возможностей
        </p>
    </div>

    <div class="col-md-8">

        <form name="registerForm" method="POST" action="PeriodicEdition" role="form">

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">
                    <p><span class="glyphicon glyphicon-remove"></span>${errorMessage}</p>
                </div>
            </c:if>

            <input type="hidden" name="command" value="register"/>

            <div class="form-group">
                <label for="login">Логин</label>
                <input id="login" type="text" name="login" class="form-control" value="" placeholder="Введите логин" />
            </div>

            <div class="form-group">
                <c:if test="${not empty duplicateMessage}">
                    <div class="alert alert-danger">
                        <p><span class="glyphicon glyphicon-remove"></span> ${duplicateMessage}</p>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="name">Ваше имя</label>
                <input id="name" type="text" name="name" class="form-control" value="" placeholder="Введите имя пользователя">
            </div>

            <div class="form-group">
                <label for="password">Пароль</label>
                <input id="password" type="password" name="password" class="form-control" value="" placeholder="Введите пароль" />
            </div>

            <div class="form-group">
                <c:if test="${not empty mismatchMessage}">
                    <div class="alert alert-danger">
                        <p><span class="glyphicon glyphicon-remove"></span> ${mismatchMessage}</p>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="confirm">Повторите пароль</label>
                <input id="confirm" type="password" name="confirm" class="form-control" value="" placeholder="Повторите пароль">
            </div>

            <input type="submit" value="Зарегистрироваться" class="btn btn-primary"/>
        </form>
    </div>

</div>

<c:import url="footer.jsp" />

</body>
</html>
