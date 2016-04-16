<%@ page contentType="text/html;charset=windows-1251" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>

    <link href="../../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/custom_styles.css" rel="stylesheet">

    <title>Главная</title>
</head>
<body>

<c:import url="header.jsp" />

<div class="container">
    <div class="jumbotron">
        <h2><strong>Периодические издания</strong></h2>
        <p class="lead">Единая система по управлению периодическими изданиями.
            Позволяет просматривать доступные издания, регистрировать новые издания, подписываться, проводить платежи.
        </p>
        <p>
            <form name="logoutForm" method="POST" action="PeriodicEdition" role="form">
                <input type="hidden" name="command" value="editions" />
                <button type="submit" class="btn btn-primary">Доступные издания</button>
            </form>
        </p>
    </div>

    <div class="row">
        <div class="col-lg-4">
            <h3>Издания</h3>
            <p>Раздел Издания позволит Вам просматривать информацию о доступных изданиях.
                Удобная таблица поможет выбрать наиболее интересное для Вас издание </p>
            <p>
                <form name="logoutForm" method="POST" action="PeriodicEdition" role="form">
                    <input type="hidden" name="command" value="editions" />
                    <button type="submit" class="btn btn-primary">Перейти к списку &raquo;</button>
                </form>
            </p>
        </div>
        <div class="col-lg-4">
            <h3>Подписки</h3>
            <p>Подписываться на любимые издания теперь стало удобнее!
                Появилась возможность просмотра всех подписок и подписок определенного пользователя</p>
            <p>
                <form name="logoutForm" method="POST" action="PeriodicEdition" role="form">
                    <input type="hidden" name="command" value="subscriptions" />
                    <button type="submit" class="btn btn-primary">Подробнее &raquo;</button>
                </form>
            </p>
        </div>
        <div class="col-lg-4">
            <h3>Платежи</h3>
            <p>Легко оплатить и получать свежие номера изданий.
                Контроль оплаты и неоплаченных подписок поможет соорентироваться быстрее </p>
            <p>
                <form name="logoutForm" method="POST" action="PeriodicEdition" role="form">
                    <input type="hidden" name="command" value="payments" />
                    <button type="submit" class="btn btn-primary">Оплатить подписку &raquo;</button>
                </form>
            </p>
        </div>
    </div>
</div>

<c:import url="footer.jsp" />

</body>
</html>
