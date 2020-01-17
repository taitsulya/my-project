<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="main.css" />
    <title>Вход</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="mainForm">
    <spring:form method="post"  modelAttribute="user" action="login">

        <spring:input class="loginInput" type="text" path="login" placeholder="Логин"/>
        <br/>
        <spring:errors cssClass="error" path="login" />
        <spring:input class="passwordInput" type="password" path="password" placeholder="Пароль"/>
        <br/>
        <spring:errors cssClass="error" path="password" />
        <spring:button class="loginFormButton">Войти</spring:button>

    </spring:form>
</div>
</body>
</html>