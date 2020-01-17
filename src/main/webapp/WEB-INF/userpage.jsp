<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<header>
    <title>${user.name}</title>
    <link rel="stylesheet" type="text/css" href="main.css" />
</header>

<body>

<div>

    <div class="sidebar">
        <div class="barelem">
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
        <div class="user">
            <div class="profileImage">
                <img class="profileImage" src="${user.getImage()}" width="300" height="300" alt="Фотография пользователя">
            </div>
            <div class="info">
                <div class="profileName">
                    ${user.name}
                </div>
                <br>
                <div class="profileNotName">
                    <div class="profileGender">
                        <span>Пол: ${user.gender == 1 ? "Женский" : "Мужской"}</span>
                    </div>
                    <div class="profileAges">
                        <span>Возраст: ${ages}</span>
                    </div>
                    <div class="profileFirst">
                        <span>Родной язык: ${firstLanguage}</span>
                    </div>
                    <div class="profileLearning">
                        <span>Изучаемый язык: ${learningLanguage}</span>
                    </div>
                    <div class="profileLevel">
                        <span>Уровень владения языком: ${languageLevel}</span>
                    </div>
                    <div class="profileDescription">
                        <c:choose>
                            <c:when test="${user.description != null && user.description != \"\"}">
                                <div>О себе:</div>
                                <div class="profileDescription">${user.description}</div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
                <br>
                <div class="startChat">
                    <a class="startChat" href="/chat${user.id}">Написать сообщение</a>
                </div>
                <div class="deleteUser">
                    <c:choose>
                        <c:when test="${currentUser.role == 1}">
                            <a class="deleteUser" href="/delete${user.id}">Удалить пользователя</a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>