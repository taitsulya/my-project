<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>

<header>
    <title>Мои сообщения</title>
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
        <div class="barelem selected">
            <a  class="barlink" href="/messages">Сообщения</a>
        </div>
        <br>
        <div class="barelem">
            <a class="barlink" href="/logout">Выйти</a>
        </div>
    </div>
    <div class="content">
        <div class="messages">
            <div class="messagesBody">
                <c:forEach var="message" items="${messageList}">
                <div class="singleMessage">
                    <a class="singleMessageLink" href="/chat${(message.sender == currentUser.id) ? message.receiver : message.sender}">
                        <div class="singleUserImage">
                            <img src="${message.image}" width="100" height="100">
                        </div>
                        <div class="nextToSingleUserImage">
                            <div class="singleUserName">
                                    ${message.name}
                            </div>
                            <div class="singleMessageText">
                                <c:choose>
                                    <c:when test="${message.sender == currentUser.id}">
                                        <span>Вы:</span>
                                    </c:when>
                                </c:choose>
                                    ${message.text}
                            </div>
                            <div class="singleMessageTime">
                                    ${message.time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}
                            </div>
                        </div>
                    </a>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</div>
</body>

</html>