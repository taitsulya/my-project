<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="main.css" />
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="mainForm">
    <spring:form method="post"  modelAttribute="user" action="registration">

        <div>
            <label class="regFormLoginLabel" for="login">Логин</label>
            <br>
            <spring:input class="regFormLogin" type="text" path="login" id="login" placeholder="Введите логин"/>
            <br>
            <spring:errors cssClass="error" path="login" />
        </div>
        <div>
            <label class="regFormPasswordLabel" for="password">Пароль</label>
            <br>
            <spring:input class="regFormPassword" type="password" path="password" id="password" placeholder="Введите пароль"/>
            <br>
            <spring:errors cssClass="error" path="password" />
        </div>
        <div>
            <label class="regFormNameLabel" for="name">Имя</label>
            <br>
            <spring:input class="regFormName" type="text" path="name" id="name" placeholder="Введите имя"/>
            <br>
            <spring:errors cssClass="error" path="name" />
        </div>
        <div class="regFormGender">
            <span>Пол</span>
            <br>
            <label><form:radiobutton path="gender" value="1"/>Женский</label>
            <label><form:radiobutton path="gender" value="2"/>Мужской</label>
            <br>
            <spring:errors cssClass="error" path="gender" />
        </div>
        <div>
            <label class="regFormDateLabel" for="date">Дата рождения</label>
            <br>
            <spring:input class="regFormDate" type="date" path="birthdate" id="date"/>
            <br>
            <spring:errors cssClass="error" path="birthdate" />
        </div>
        <div>
            <label class="regFormFirstLabel" for="first">Родной язык</label>
            <br>
            <form:select class="regFormFirst" path="firstLanguage" id="first" autocomplete="off">
                <form:option value="${null}" label="  Выберите родной язык  "/>
                <form:option value="0" label="Английский"/>
                <form:option value="1" label="Русский"/>
                <form:option value="2" label="Испанский"/>
                <form:option value="3" label="Немецкий"/>
                <form:option value="4" label="Французский"/>
            </form:select>
            <br>
            <spring:errors cssClass="error" path="firstLanguage" />
        </div>
        <div>
            <label class="regFormLearningLabel" for="learning">Изучаемый язык</label>
            <br>
            <form:select class="regFormLearning" path="learningLanguage" id="learning" autocomplete="off">
                <form:option value="${null}" label="  Выберите изучаемый язык  "/>
                <form:option value="0" label="Английский"/>
                <form:option value="1" label="Русский"/>
                <form:option value="2" label="Испанский"/>
                <form:option value="3" label="Немецкий"/>
                <form:option value="4" label="Французский"/>
            </form:select>
            <br>
            <spring:errors cssClass="error" path="learningLanguage" />
        </div>
        <div>
            <label class="regFormLevelLabel" for="level">Уровень владения языком</label>
            <br>
            <form:select class="regFormLevel" path="languageLevel" id="level" autocomplete="off">
                <form:option value="${null}" label="  Выберите уровень владения языком  "/>
                <form:option value="0" label="А1 - элементарный"/>
                <form:option value="1" label="А2 - базовый"/>
                <form:option value="2" label="B1 - пороговый"/>
                <form:option value="3" label="B2 - продвинутый"/>
                <form:option value="4" label="C1 - профессиональный"/>
                <form:option value="5" label="C2 - в совершенстве"/>
            </form:select>
            <br>
            <spring:errors cssClass="error" path="languageLevel" />
        </div>

        <spring:button class="regFormButton">Зарегистрироваться</spring:button>

    </spring:form>
</div>
</body>
</html>


<%--
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="main.css" />
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="mainForm">
    <form method="post"  modelAttribute="user" action="registration">

        <div>
            <label class="regFormLoginLabel" for="login">Логин</label>
            <br>
            <input class="regFormLogin" type="text" path="login" id="login" placeholder="Введите логин"/>
        </div>
        <div>
            <label class="regFormPasswordLabel" for="password">Пароль</label>
            <br>
            <input class="regFormPassword" type="password" path="password" id="password" placeholder="Введите пароль"/>
        </div>
        <div>
            <label class="regFormNameLabel" for="name">Имя</label>
            <br>
            <input class="regFormName" type="text" path="name" id="name" placeholder="Введите имя"/>
        </div>
        <div class="regFormGender">
            <span>Пол</span>
            <br>
            <label><input type="radio" path="gender" value="1"/>Женский</label>
            <label><input type="radio" path="gender" value="2"/>Мужской</label>
        </div>
        <div>
            <label class="regFormDateLabel" for="date">Дата рождения</label>
            <br>
            <input class="regFormDate" type="date" path="birthdate" id="date"/>
        </div>
        <div>
            <label for="first">Родной язык</label>
            <br>
            <select class="regFormFirst" path="firstLanguage" id="first" autocomplete="off">
                <option value="${null}" label="  Выберите родной язык  "/>
                <option value="0" label="Английский"/>
                <option value="1" label="Русский"/>
                <option value="2" label="Испанский"/>
                <option value="3" label="Немецкий"/>
                <option value="4" label="Французский"/>
            </select>
        </div>
        <div>
            <label for="learning">Изучаемый язык</label>
            <br>
            <select class="regFormLearning" path="learningLanguage" id="learning" autocomplete="off">
                <option value="${null}" label="  Выберите изучаемый язык  "/>
                <option value="0" label="Английский"/>
                <option value="1" label="Русский"/>
                <option value="2" label="Испанский"/>
                <option value="3" label="Немецкий"/>
                <option value="4" label="Французский"/>
            </select>
        </div>
        <div>
            <label for="level">Уровень владения языком</label>
            <br>
            <select class="regFormLevel" path="languageLevel" id="level" autocomplete="off">
                <option value="${null}" label="  Выберите уровень владения языком  "/>
                <option value="0" label="А1 - элементарный"/>
                <option value="1" label="А2 - базовый"/>
                <option value="2" label="B1 - пороговый"/>
                <option value="3" label="B2 - продвинутый"/>
                <option value="4" label="C1 - профессиональный"/>
                <option value="5" label="C2 - в совершенстве"/>
            </select>
        </div>

        <button class="regFormButton">Зарегистрироваться</spring:button>

    </form>
</div>
</body>
</html>--%>
