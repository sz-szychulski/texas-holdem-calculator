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
                    <a class="nav-link" href="/">Strona główna</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/reports">Raporty<span class="sr-only">(current)</span></a>
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

        <h3>Raport został pomyślnie wygenerowany</h3>
        <h5>Ścieżka do pliku: ${raport_path}</h5>


        <div style="float: left">
            <a href="/" ><button>Wróć</button></a>
        </div>

    </div>

</body>
</html>