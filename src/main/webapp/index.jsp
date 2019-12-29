<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Praca inżynierska - Sebastian Szychulski</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="style.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
    <div class="container">
        <h1>Aplikacja wspierająca podejmowanie decyzji w grze Texas Hold'em</h1>
        <h2>Gracze:</h2>
        <form method="post" action="/calculate">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div>
                <label for="player1">Gracz 1: </label>
                <input type="text" name="player1" id="player1"/>
            </div>
            <div>
                <label for="player2">Gracz 2: </label>
                <input type="text" name="player2" id="player2"/>
            </div>
            <div>
                <label for="player3">Gracz 3: </label>
                <input type="text" name="player3" id="player3"/>
            </div>
            <div>
                <label for="player4">Gracz 4: </label>
                <input type="text" name="player4" id="player4"/>
            </div>
            <div>
                <label for="player5">Gracz 5: </label>
                <input type="text" name="player5" id="player5"/>
            </div>
            <div>
                <label for="player6">Gracz 6: </label>
                <input type="text" name="player6" id="player6"/>
            </div>
            <div>
                <label for="board">Karty wspólne: </label>
                <input type="text" name="board" id="board"/>
            </div>
            <div>
                <input type="submit" value="Oblicz" />
            </div>
        </form>
        <hr />
        <h1>Wynik:</h1>

    </div>
</body>
</html>