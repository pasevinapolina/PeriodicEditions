<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="main.jsp">Периодические издания</a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a onclick="document.getElementById('emptyForm').submit()" class="submitLink">Главная</a>
                        <form name="emptyForm" method="POST" action="PeriodicEdition" role="form" id="emptyForm">
                            <input type="hidden" name="command" value="" />
                        </form>
                    </li>
                    <li><a onclick="document.getElementById('editionForm').submit()" class="submitLink">Издания</a>
                        <form name="editionForm" method="POST" action="PeriodicEdition" role="form" id="editionForm">
                            <input type="hidden" name="command" value="editions" />
                        </form>
                    </li>
                    <li><a onclick="document.getElementById('subscrForm').submit()" class="submitLink">Подписки</a>
                        <form name="subscrForm" method="POST" action="PeriodicEdition" role="form" id="subscrForm">
                            <input type="hidden" name="command" value="subscriptions" />
                        </form>
                    </li>
                    <li><a onclick="document.getElementById('paymentForm').submit()" class="submitLink">Платежи</a>
                        <form name="paymentForm" method="POST" action="PeriodicEdition" role="form" id="paymentForm">
                            <input type="hidden" name="command" value="payments" />
                        </form>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <li><a href="#" class="glyphicon glyphicon-user">${user.login}</a> </li>
                            <li>
                                <form name="logoutForm" method="POST" action="PeriodicEdition" role="form">
                                    <input type="hidden" name="command" value="logout" />
                                    <a class="btn"><input type="submit" class="btn btn-primary " value="Выйти" /></a>
                                </form>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="login.jsp"><input type="button" class="btn btn-primary" value="Войти" /></a>
                            </li>
                            <li>
                                <a href="register.jsp"><input type="button" class="btn btn-primary" value="Зарегистрироваться" /></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>

            </div><!--/.nav-collapse -->
        </div>
    </div>
</header>

