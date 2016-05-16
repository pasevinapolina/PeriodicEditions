<%@ page import="by.pasevinapolina.models.ClientType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/payments.js"></script>

    <link href="../../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/custom_styles.css" rel="stylesheet">

    <title>Платежи</title>
</head>
<body>

<c:import url="header.jsp" />

<div class="container">
    <div class="jumbotron">
        <h2><strong>Платежи</strong></h2>
        <p><button type="button" class="btn btn-lg btn-primary" data-toggle="modal" data-target="#payModal">
            <a class="glyphicon glyphicon-credit-card"></a>Оплатить подписку</button></p>
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

    <div class="panel panel-default">

        <div class="panel panel-heading">Недавние платежи</div>

        <% ClientType userType = (ClientType) request.getSession().getAttribute("userType"); %>

        <!-- Table -->
        <table class="table table-responsive table-hover">
            <thead><tr>
                <th>Номер подписки</th>
                <th>Издание</th>
                <th>Читатель</th>
                <th>Дата</th>
                <th>Сумма</th>
                <c:if test="${userType == ClientType.ADMIN}">
                    <th>Действия</th>
                </c:if>
            </tr></thead>

            <tbody>
            <c:forEach var="payment" items="${payments}" varStatus="status" >
                <tr id="${payment.id}">
                    <td>
                        ${payment.subscription.id}
                    </td>
                    <td>"${payment.subscription.edition.name}", ${payment.subscription.edition.author}</td>
                    <td>${payment.subscription.reader.name}</td>
                    <td><fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${payment.payDate}"/></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2" pattern="#.##" value="${payment.paySum}" />
                        <span> руб.</span></td>

                    <ctg:tb-action deleteModalId="deletePayModal" editModalId="editModal"
                                   delClass="pay-del" editClass="pay-edit"/>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<c:import url="pay_modals.jsp" />

<c:import url="footer.jsp" />

</body>
</html>
