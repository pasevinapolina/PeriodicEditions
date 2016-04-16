<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../../static/js/bootstrap.min.js"></script>

    <link href="../../../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../../static/css/custom_styles.css" rel="stylesheet">

    <title>Error Page</title>
</head>
<body>

<div class="container">
    <h2>Ошибка выполнения</h2>

    <hr>
    <div class="panel">
        <div class="panel-heading">
            <h3 class="panel-title">Дополнительная информация по исключению</h3>
        </div>
        <div class="panel-body">
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
