<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>

<header>
    <title>Чат</title>
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
        <div class="chat">
            <div class="chatHeader">
                <div class="chatImage">
                    <img src="${user.image}" width="150" height="150" alt="Фотография пользователя">
                </div>
                <a class="chatUserName" href="/user${user.id}">${user.name}</a>
            </div>
            <div class="chatBody">
                <c:forEach var="message" items="${messageList}">
                    <div class="message${(message.sender == user.id) ? "2" : "1"} message">
                        <div class="messageText">
                            ${message.text}
                        </div>
                        <div class="messageTime">
                            ${message.time.format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="chatInput">
                <form method="POST"  action="chat${user.id}">
                    <label>
                        <textarea class="chatTextInput" name="message" placeholder="Введите сообщение..."></textarea>
                    </label>
                    <input class="chatSubmitInput" type="submit" value="Отправить"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>