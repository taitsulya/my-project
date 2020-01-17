<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<header>
    <title>Мой профиль</title>
    <link rel="stylesheet" type="text/css" href="main.css" />
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</header>

<body>

<div>
    <div class="sidebar">
        <div class="barelem selected">
            <a class="barlink" href="/profile">Профиль</a>
        </div>
        <div class="barelem">
            <a  class="barlink" href="/community1" >Сообщество</a>
        </div>
        <div class="barelem">
            <a  class="barlink" href="/messages">Сообщения</a>
        </div>
        <br>
        <div class="barelem">
            <a class="barlink" href="/logout">Выйти</a>
        </div>
    </div>
    <div class="content">
        <div>
            <h1>Редактирование профиля</h1>
        </div>
        <spring:form method="post"  modelAttribute="user" action="edit">

            <div>
                <label class="editFormLoginLabel" for="login">Логин</label>
                <br>
                <spring:input class="editFormLogin" type="text" path="login" id="login" placeholder="Введите логин"/>
                <br>
                <spring:errors cssClass="error" path="login" />
            </div>
            <div>
                <label class="editFormPasswordLabel" for="password">Пароль</label>
                <br>
                <spring:input class="editFormPassword" type="password" path="password" id="password" placeholder="Введите пароль"/>
                <br>
                <spring:errors cssClass="error" path="password" />
            </div>
            <div>
                <label class="editFormNameLabel" for="name">Имя</label>
                <br>
                <spring:input class="editFormName" type="text" path="name" id="name" placeholder="Введите имя"/>
                <br>
                <spring:errors cssClass="error" path="name" />
            </div>
            <div class="editFormGender">
                <span>Пол</span>
                <br>
                <label><form:radiobutton path="gender" value="1"/>Женский</label>
                <label><form:radiobutton path="gender" value="2"/>Мужской</label>
                <br>
                <spring:errors cssClass="error" path="gender" />
            </div>
            <div>
                <label class="editFormDateLabel" for="date">Дата рождения</label>
                <br>
                <spring:input class="editFormDate" type="date" path="birthdate" id="date"/>
                <br>
                <spring:errors cssClass="error" path="birthdate" />
            </div>
            <div>
                <label class="editFormFirstLabel" for="first">Родной язык</label>
                <br>
                <form:select class="editFormFirst" path="firstLanguage" id="first" autocomplete="off">
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
                <label class="editFormLearningLabel" for="learning">Изучаемый язык</label>
                <br>
                <form:select class="editFormLearning" path="learningLanguage" id="learning" autocomplete="off">
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
                <label class="editFormLevelLabel" for="level">Уровень владения языком</label>
                <br>
                <form:select class="editFormLevel" path="languageLevel" id="level" autocomplete="off">
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
            <div>
                <label class="editFormDescriptionLabel" for="description">О себе</label>
                <br>
                <form:textarea rows="10" class="editFormDescription" path="description" id="description" placeholder="Введите описание"/>
            </div>

            <spring:button class="editFormButton">Сохранить изменения</spring:button>

        </spring:form>
    </div>
</div>
</body>

</html>