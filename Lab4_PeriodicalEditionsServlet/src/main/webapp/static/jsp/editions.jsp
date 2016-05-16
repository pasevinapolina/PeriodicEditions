<%@ page import="by.pasevinapolina.models.ClientType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/editions.js"></script>

    <link href="../../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/custom_styles.css" rel="stylesheet">

    <title>Издания</title>
</head>
<body>
<c:import url="header.jsp" />

<div class="container">
    <div class="jumbotron">

        <div class="row">
            <h2><strong>Список периодических изданий</strong></h2>
        </div>

        <% ClientType userType = (ClientType) request.getSession().getAttribute("userType"); %>

        <c:if test="${userType == ClientType.ADMIN}">
            <div class="row">
                <button type="button" class="btn btn-primary" data-target="#myModal" data-toggle="modal">
                    <a class="glyphicon glyphicon-circle-arrow-down"></a>
                    Зарегистрировать издание</button>
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

    <hr>
    <div>
        <form name="editionForm" method="GET" action="PeriodicEdition" role="form">
            <input type="hidden" name="command" value="editions">
        </form>

        <div class="panel panel-default">

            <div class="panel panel-heading">Сейчас доступны</div>
            <!-- Table -->
            <table class="table table-responsive table-hover">
                <thead><tr>
                    <th>Номер</th>
                    <th>Название</th>
                    <th>Издательство</th>
                    <th>Частота выхода в днях</th>
                    <c:if test="${userType == ClientType.ADMIN}">
                        <th>Действия</th>
                    </c:if>
                </tr></thead>

                <tbody>
                <c:forEach var="edition" items="${editions}" varStatus="status" >
                    <tr>
                        <td>${edition.id}</td>
                        <td>${edition.name}</td>
                        <td>${edition.author}</td>
                        <td>${edition.outputFrequency}</td>

                        <ctg:tb-action deleteModalId="delEditionModal" editModalId="editEditionModal"
                                delClass="ed-del" editClass="ed-edit"/>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div> <!-- container -->

<c:import url="edition_modals.jsp" />

<c:import url="footer.jsp" />

</body>
</html>
