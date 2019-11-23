<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Holdem equity calculator</title>
    <link rel="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
    <body>
        <h1>Gracze:</h1>
        <form action="post">
            <label for="player1">Gracz 1:</label>
            <input type="text" id="player1" /> <br/>
            <label for="player2">Gracz 2:</label>
            <input type="text" id="player2" /> <br/>
            <input type="submit">
        </form>
        <hr />
        <h1>Wynik:</h1>

        <script src="webjars/jquery/3.2.1/jquery.min.js"></script>
        </body>
</html>