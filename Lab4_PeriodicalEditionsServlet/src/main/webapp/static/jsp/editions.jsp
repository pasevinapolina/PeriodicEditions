<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=windows-1251" pageEncoding="windows-1251" language="java" %>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="../../static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>

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

        <div class="row">
            <button type="button" class="btn btn-primary" data-target="#myModal" data-toggle="modal">
                <a class="glyphicon glyphicon-circle-arrow-down"></a>
                Зарегистрировать издание</button>
        </div>
    </div>

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
                </tr></thead>

                <tbody>
                <c:forEach var="edition" items="${editions}" varStatus="status" >
                    <tr>
                        <td>${edition.id}</td>
                        <td>${edition.name}</td>
                        <td>${edition.author}</td>
                        <td>${edition.outputFrequency}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div> <!-- container -->

<div class="container modal-container">
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <span class="modal-title">Регистрация издания</span>
                </div>

                <div class="modal-body">
                    <form role="form" method="POST" action="PeriodicEdition" name="registerEditionForm">
                        <input type="hidden" name="command" value="register_edition">

                        <div class="form-group">
                            <label for="author">Издательство</label>
                            <input type="text" name="author" class="form-control" id="author" placeholder="Введите издательство">
                        </div>
                        <div class="form-group">
                            <label for="name">Название</label>
                            <input type="text" name="name" class="form-control" id="name" placeholder="Введите название">
                        </div>
                        <div class="form-group">
                            <label for="outFreq">Выходит 1 раз в </label>
                            <input type="number" name="outFreq" min="0" max="365" class="form-control"
                                   id="outFreq" placeholder="Частота выхода в днях">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">
                            <span class="glyphicon glyphicon-ok"></span>Готово</button>
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
