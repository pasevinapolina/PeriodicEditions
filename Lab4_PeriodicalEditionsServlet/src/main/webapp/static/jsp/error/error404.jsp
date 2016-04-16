<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../static/js/alphabet.js"></script>

    <link href="../../../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../../static/css/custom_styles.css" rel="stylesheet">
    <title>404 | Not found</title>
</head>
<body>

<input type="hidden" id="errorCode" value="${pageContext.errorData.statusCode}">
<canvas id="myCanvas"></canvas>
<script type="text/javascript" src="../../../static/js/bubbles.js"></script>
<script type="text/javascript" src="../../../static/js/error.js"></script>

<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <h2>Ой! Страница не была найдена</h2>
        </div>
        <div class="col-lg-4">
            <h3><a class="glyphicon glyphicon-home btn btn-lg btn-link" href="/PeriodicEdition">Вернуться на главную</a></h3>
        </div>
    </div>

    <hr>

    <div class="spoiler">
        <div class="spoiler-btn">Дополнительная информация по исключению
            <a class="glyphicon glyphicon-chevron-down"></a>
        </div>
        <div class="spoiler-body collapse">
            <p><strong>Запрос от: </strong> ${pageContext.errorData.requestURI}</p><br>
            <p><strong>Имя/тип сервлета: </strong> ${pageContext.errorData.servletName}</p><br>
            <p><strong>Код ошибки: </strong> ${pageContext.errorData.statusCode}</p><br>
            <p><strong>Исключение: </strong> ${pageContext.errorData.throwable}</p><br>
            <p><strong>Сообщение: </strong> ${pageContext.exception.message}</p><br>
        </div>
    </div>

</div>
</body>
</html>
