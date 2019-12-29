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
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
    <body>
        <h1>Gracze:</h1>
        <form method="post" action="/calculate">
            <table>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <tr>
                    <td style="font-weight: bold">Gracz1: </td>
                    <td><input type="text" name="player1" /></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Gracz2: </td>
                    <td><input type="text" name="player2" /></td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Board: </td>
                    <td><input type="text" name="board" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Wyślij" /></td>
                </tr>
            </table>
        </form>
        <hr />
        <h1>Wynik:</h1>

        </body>
</html>