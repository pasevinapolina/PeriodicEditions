<%@ page import="by.pasevinapolina.models.ClientType" %>
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

<% ClientType userType = (ClientType) request.getSession().getAttribute("userType"); %>

<div class="container">
    <div class="jumbotron">
        <h2><strong>Подписки</strong></h2>

        <c:if test="${userType == ClientType.USER}">
            <div class="row">
                <button type="button" class="btn btn-primary subscribe-btn" data-target="#subscribeModal" data-toggle="modal">
                    <a class="glyphicon glyphicon-plus"></a> Подписаться
                </button>
            </div>
        </c:if>
    </div>

    <c:if test="${not empty addSuccess}">
        <div class="alert alert-success">
            <p><span class="glyphicon glyphicon-ok"></span> ${addSuccess}</p>
        </div>
    </c:if>

    <c:if test="${not empty addError}">
        <div class="alert alert-danger">
            <p><span class="glyphicon glyphicon-remove"></span> ${addError}</p>
        </div>
    </c:if>

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
                            <li aria-selected="true" class="myReader"><span class="readerId" hidden>0</span>
                                <a>Все читатели</a>
                            </li>
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

<div class="container modal-container">
    <!-- Modal -->
    <div class="modal fade" id="subscribeModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <span class="modal-title">Оформление подписки</span>
                </div>

                <div class="modal-body">
                    <form role="form" method="POST" action="PeriodicEdition" name="registerEditionForm">
                        <input type="hidden" name="command" value="subscribe">

                        <div class="form-group">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button"
                                        data-toggle="dropdown">
                                    <span id="editionSelect">Выбрать издание</span><span class="caret"></span>
                                </button>
                                <ul id="editionList" name="editionList" class="dropdown-menu" onclick="">
                                    <li class="myEdition"><span class="editionId" hidden>0</span>
                                        <a class="myEditionName">Все издания</a>
                                    </li>
                                    <c:forEach var="edition" items="${editions}" varStatus="status">
                                        <li class="myEdition">
                                            <span class="editionId" hidden>${edition.id}</span>
                                            <a class="myEditionName">${edition.name}, ${edition.author}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <input type="hidden" name="selEditionId" id="selEditionId" value="">
                            </div>
                       </div>

                        <div class="form-group">
                            <label for="duration">Длительность подписки</label>
                            <input type="number" name="duration" min="0" max="365" class="form-control"
                                   id="duration" placeholder="Длительность подписки">
                        </div>

                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Подписаться</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
