<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Praca inżynierska - Sebastian Szychulski</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Praca inżynierska</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Strona główna<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/reports">Raporty</a>
                </li>
            </ul>
            <span class="navbar-text">
              <h5>Witaj ${pageContext.request.userPrincipal.name} <button onclick="document.forms['logoutForm'].submit()">Wyloguj</button></h5>
            </span>
        </div>
    </nav>

    <br/>

    <div class="content-container">
        <h1>Aplikacja wspierająca podejmowanie decyzji w grze Texas Hold'em</h1>

        <hr/>

        <div class="board-results">
            <h2>Karty wspólne</h2>
            <c:forEach items="${board_cards}" var="card">
                <img src="/images/${card}.png" />
            </c:forEach>
        </div>

        <hr/>

        <div class="players-results">
        <c:forEach var = "index" begin = "0" end = "${players}">
            <div>
                <h5>Gracz <c:out value = "${index + 1}"/></h5>
                <img src="/images/<c:out value = "${hands_string[index][0]}"/>.png" />
                <img src="/images/<c:out value = "${hands_string[index][1]}"/>.png" />
                <br/><br/>
                <p>Układ: <c:out value = "${hand_rankings[index]}"/></p>
                <p>Całkowite szanse: <fmt:formatNumber type="number" maxFractionDigits="2" value="${total_equity[index]}"/>%</p>
                <p>Szanse wygranej: <fmt:formatNumber type="number" maxFractionDigits="2" value="${total_win[index]}"/>%</p>
                <p>Podział puli: <fmt:formatNumber type="number" maxFractionDigits="2" value="${total_split[index]}"/>%</p>
            </div>
            <hr />
        </c:forEach>
        </div>


        <h3>Zapis symulacji do bazy danych</h3>
        <form action="/save" method="post" class="general">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div>
                <label for="simulationName">Nazwa symulacji: </label>
                <input type="text" id="simulationName" name="simulationName">
            </div>
            <div style="float: left">
                <input type="submit" value="Zapisz do bazy"/>
            </div>
        </form>
        <div style="float: left">
            <a href="/" ><button>Wróć</button></a>
        </div>
    </div>

</body>
</html>