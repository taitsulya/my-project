<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<header>
    <title>Мой профиль</title>
    <link rel="stylesheet" type="text/css" href="main.css" />
</header>

<body>

<div>
    <h1>${ user.name }</h1>

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
        <form method="POST" action="upload" enctype="multipart/form-data">
            <input class="uploadFile" type="file" name="file"/><br/>
            <input class="uploadButton" type="submit" value="Загрузить фотографию"/>
        </form>
    </div>
</div>
</body>

</html>
