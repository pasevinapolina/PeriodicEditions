<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/subscriptions.js" defer></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap-select.min.js"></script>

    <link rel="stylesheet" href="../../static/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../static/css/magic-check.css"/>
    <link rel="stylesheet" href="../../static/css/bootstrap-select.css">
    <link href="../../static/css/custom_styles.css" rel="stylesheet">

    <title>Подписки</title>
</head>
<body>
<c:import url="header.jsp"/>

<div class="container">
    <div class="jumbotron">
        <h2><strong>Подписки</strong></h2>
    </div>

    <div class="container">
        <h4>Фильтр</h4>
        <hr>

        <div class="form-group">
            <form class="form-inline" method="GET" name="readerForm" id="readerForm" action="PeriodicEdition"
                  role="form">
                <input type="hidden" name="command" value="subscriptions">
                <div class="form-group">
                    <label class="col-md-2 control-label" for="readerList">Читатель</label>
                </div>

                <div class="form-group">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                            Выбрать читателя
                            <span class="caret"></span></button>
                        <ul id="readerList" name="readerList" class="dropdown-menu" onclick="document.getElementById('readerForm').submit()">
                            <c:forEach var="reader" items="${readers}" varStatus="status">
                                <li class="myReader"><span class="readerId" hidden>${reader.id}</span>
                                    <a>${reader.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                        <input type="hidden" name="readerName" value="" id="readerName">
                    </div>
                </div>
            </form>

            <form method="GET" name="unpaidForm" id="unpaidForm" action="PeriodicEdition" role="form">
                <input type="hidden" name="command" value="subscriptions">
                <div class="row">
                    <input id="unpaidCheck" class="magic-checkbox" name="unpaidCheck" type="checkbox"
                    ${unpaidCheck ? 'checked' : ''} onclick="this.form.submit()">
                    <label for="unpaidCheck" class="text">Только неоплаченные</label>
                </div>
            </form>
        </div>
    </div>

    <hr>

    <div class="panel panel-default">

        <div class="panel panel-heading">Подписки</div>
        <!-- Table -->
        <table class="table table-responsive table-hover">
            <thead>
            <tr>
                <th>Номер подписки</th>
                <th>Издание</th>
                <th>Читатель</th>
                <th>Длительность, дни</th>
                <th>Оплачено</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="">

            </c:if>
            <c:forEach var="subscription" items="${subscriptions}" varStatus="status">
                <tr>
                    <td>${subscription.id}</td>
                    <td>${subscription.edition.name}</td>
                    <td>${subscription.reader.name}</td>
                    <td>${subscription.duration}</td>
                    <td>
                        <c:if test="${subscription.paid}">
                            <span class="glyphicon glyphicon-ok paid-ok"></span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <hr>

</div>

<c:import url="footer.jsp"/>

</body>
</html>
