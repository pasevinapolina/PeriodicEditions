<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>

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
        <!-- Table -->
        <table class="table table-responsive table-hover">
            <thead><tr>
                <th>Номер подписки</th>
                <th>Издание</th>
                <th>Читатель</th>
                <th>Дата</th>
                <th>Сумма</th>
                <th>Действия</th>
            </tr></thead>

            <tbody>
            <c:forEach var="payment" items="${payments}" varStatus="status" >
                <tr>
                    <td>${payment.subscription.id}</td>
                    <td>"${payment.subscription.edition.name}", ${payment.subscription.edition.author}</td>
                    <td>${payment.subscription.reader.name}</td>
                    <td><fmt:formatDate type="date" value="${payment.payDate}"/></td>
                    <td><fmt:formatNumber type="currency" value="${payment.paySum}" /></td>
                    <td><a class="glyphicon glyphicon-edit" >Редактировать</a> |
                        <a class="glyphicon glyphicon-trash">Удалить</a></td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
        </div>
    </div>
</div>

<div class="container modal-container">
    <!-- Modal -->
    <div class="modal fade" id="payModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <span class="modal-title">Оплата</span>
                </div>

                <div class="modal-body">
                    <form role="form" method="POST" action="PeriodicEdition" name="payForm" id="payForm">
                        <input type="hidden" name="command" value="add_payment">

                        <div class="form-group">
                            <label for="subscr_id">Номер подписки</label>
                            <p>№<input type="text" class="form-control" id="subscr_id" name="subscr_id"
                                       placeholder="Введите номер"></p>
                        </div>

                        <div class="form-group">
                            <label for="duration">Количество дней </label>
                            <input type="number" name="duration" min="0" max="365" class="form-control"
                                   id="duration" placeholder="Введите количество дней">
                        </div>

                        <div class="form-group">
                            <label for="paySum">Сумма для оплаты</label>
                            <input type="text" class="form-control" name="paySum" disabled id="paySum" placeholder="0">
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">
                            <span class="glyphicon glyphicon-credit-card"></span>Оплатить</button>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                </div>

            </div>
        </div>
    </div>
</div>

<c:import url="footer.jsp" />

</body>
</html>
