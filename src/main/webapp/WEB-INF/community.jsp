<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Period" %>
<%@ page import="com.julat.myproject.Service" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<header>
    <title>Сообщество</title>
    <link rel="stylesheet" type="text/css" href="main.css" />
</header>

<body>

<div>

    <div class="sidebar">
        <div class="barelem">
            <a class="barlink" href="/profile">Профиль</a>
        </div>
        <div class="barelem selected">
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
        <div class="community">
            <div class="searching">
                <a class="searchingLink">Критерии поиска</a>
                <div class="searchingForm">
                    <spring:form method="post"  modelAttribute="user" action="community${page}">
                        <div class="searchingName">
                            <label class="searchingNameLabel" for="name">Имя</label>
                            <br>
                            <spring:input class="searchingName" type="text" path="name" id="name" placeholder="Введите имя"/>
                        </div>
                        <div class="searchingGender">
                            <span>Пол</span>
                            <br>
                            <label class="searchingGenderRadio"><form:radiobutton path="gender" value="1"/>Женский</label>
                            <label class="searchingGenderRadio"><form:radiobutton path="gender" value="2"/>Мужской</label>
                        </div>
                        <div>
                            <label class="searchingAgesLabel" for="ages">Возраст</label>
                            <br>
                            <div class="searchingAges" id="ages">
                                <label class="searchingAgesFromLabel" for="from">От</label>
                                <select class="searchingAgesFrom" name="from" id="from">
                                    <option value=""></option>
                                    <c:forEach var="number" begin="12" end="99" step="1">
                                        <option value="${number}">${number}</option>
                                    </c:forEach>
                                </select>
                                <label class="searchingAgesToLabel" for="to">До</label>
                                <select class="searchingAgesTo" name="to" id="to">
                                    <option value=""></option>
                                    <c:forEach var="number" begin="12" end="99" step="1">
                                        <option value="${number}">${number}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div>
                            <label for="first">Родной язык</label>
                            <br>
                            <form:select class="searchingFirst" path="firstLanguage" id="first" autocomplete="off">
                                <form:option value="${null}" label="  Выберите родной язык  "/>
                                <form:option value="0" label="Английский"/>
                                <form:option value="1" label="Русский"/>
                                <form:option value="2" label="Испанский"/>
                                <form:option value="3" label="Немецкий"/>
                                <form:option value="4" label="Французский"/>
                            </form:select>
                        </div>
                        <div>
                            <label for="learning">Изучаемый язык</label>
                            <br>
                            <form:select class="searchingLearning" path="learningLanguage" id="learning" autocomplete="off">
                                <form:option value="${null}" label="  Выберите изучаемый язык  "/>
                                <form:option value="0" label="Английский"/>
                                <form:option value="1" label="Русский"/>
                                <form:option value="2" label="Испанский"/>
                                <form:option value="3" label="Немецкий"/>
                                <form:option value="4" label="Французский"/>
                            </form:select>
                        </div>
                        <div>
                            <label for="level">Уровень владения языком</label>
                            <br>
                            <form:select class="searchingLevel" path="languageLevel" id="level" autocomplete="off">
                                <form:option value="${null}" label="  Выберите уровень владения  "/>
                                <form:option value="0" label="А1 - элементарный"/>
                                <form:option value="1" label="А2 - базовый"/>
                                <form:option value="2" label="B1 - пороговый"/>
                                <form:option value="3" label="B2 - продвинутый"/>
                                <form:option value="4" label="C1 - профессиональный"/>
                                <form:option value="5" label="C2 - в совершенстве"/>
                            </form:select>
                        </div>

                        <spring:button class="searchingButton">Искать</spring:button>

                    </spring:form>
                </div>
            </div>
            <c:forEach var="user" items="${userList}">
                <div class="communityProfile">
                    <div class="communityProfileImage">
                        <img src="${user.image}" width="200" height="200" alt="Фотография пользователя">
                    </div>
                    <div class="communityProfileInfo">
                        <div class="communityProfileName">
                            <a class="communityProfileName" href="/user${user.id}">${user.name}</a>
                        </div>
                        <div class="communityProfileNotName">
                            <div class="communityProfileGender">
                                <span>Пол: ${user.gender == 1 ? "Женский" : "Мужской"}</span>
                            </div>
                            <div class="communityProfileAges">
                                <span>Возраст: ${Period.between(user.birthdate, LocalDate.now()).getYears()}</span>
                            </div>
                            <div class="communityProfileFirst">
                                <span>Родной язык: ${Service.getLanguageById(user.firstLanguage)}</span>
                            </div>
                            <div class="communityProfileLearning">
                                <span>Изучаемый язык: ${Service.getLanguageById(user.learningLanguage)}</span>
                            </div>
                            <div class="communityProfileLevel">
                                <span>Уровень владения языком: ${Service.getLanguageLevelById(user.languageLevel)}</span>
                            </div>
                            <div class="communityStartChat">
                                <a class="communityStartChat" href="/chat${user.id}">Написать сообщение</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="pages">
            <c:choose>
                <c:when test="${page > 1}">
                    <a class="previous" href="/community${page - 1}">Предыдущая страница</a>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${nextPageIsExist}">
                    <a class="next" href="/community${page + 1}">Следующая страница</a>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>

</html>