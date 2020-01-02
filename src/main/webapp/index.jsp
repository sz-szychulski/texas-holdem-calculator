<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="modal_style.css" />
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
    <div class="content-container">
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
                <label for="player7">Gracz 7: </label>
                <input type="text" name="player7" id="player7"/>
            </div>
            <div>
                <label for="player8">Gracz 8: </label>
                <input type="text" name="player8" id="player8"/>
            </div>
            <div>
                <label for="player9">Gracz 9: </label>
                <input type="text" name="player9" id="player9"/>
            </div>
            <div>
                <label for="player10">Gracz 10: </label>
                <input type="text" name="player10" id="player10"/>
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

        <c:forEach items="${hands}" var="hand">
            <c:out value="${hand}" />
        </c:forEach>

        <div class="poker-table">
            <form method="post" action="/calculate">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="board-cards">
                <div class="flop">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="flop-1">
                        <img class="card-back" src="images/card-back.png" />
                    </button>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="flop-2">
                        <img class="card-back" src="images/card-back.png" />
                    </button>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="flop-3">
                        <img class="card-back" src="images/card-back.png" />
                    </button>
                </div>

                <div class="turn">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="turn">
                        <img class="card-back" src="images/card-back.png" />
                    </button>
                </div>

                <div class="river">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="river">
                        <img class="card-back" src="images/card-back.png" />
                    </button>
                </div>
            </div>

            <div class="player1">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player1-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player1-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player2">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player2-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player2-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player3">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player3-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player3-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player4">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player4-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player4-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player5">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player5-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player5-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player6">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player6-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player6-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player7">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player7-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player7-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player8">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player8-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player8-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player9">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player9-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player9-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>
            <div class="player10">
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player10-1">
                    <img class="card-back" src="images/card-back.png" />
                </button>
                <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-whatever="player10-2">
                    <img class="card-back" src="images/card-back.png" />
                </button>
            </div>

              </form>
        </div>

        <div class="modal fade" id="cardModal" tabindex="-1" role="dialog" aria-labelledby="cardModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cardModalLabel">Choose card: </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="card-choose">
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="2c" value="2c"/><img src="images/2c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="2d" value="2d"/><img src="images/2d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="2h" value="2h"/><img src="images/2h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="2s" value="2s"/><img src="images/2s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="3c" value="3c"/><img src="images/3c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="3d" value="3d"/><img src="images/3d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="3h" value="3h"/><img src="images/3h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="3s" value="3s"/><img src="images/3s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="4c" value="4c"/><img src="images/4c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="4d" value="4d"/><img src="images/4d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="4h" value="4h"/><img src="images/4h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="4s" value="4s"/><img src="images/4s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="5c" value="5c"/><img src="images/5c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="5d" value="5d"/><img src="images/5d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="5h" value="5h"/><img src="images/5h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="5s" value="5s"/><img src="images/5s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center text-center">
                                <label>
                                    <input type="radio" name='card' id="6c" value="6c"/><img src="images/6c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="6d" value="6d"/><img src="images/6d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="6h" value="6h"/><img src="images/6h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="6s" value="6s"/><img src="images/6s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="7c" value="7c"/><img src="images/7c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="7d" value="7d"/><img src="images/7d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="7h" value="7h"/><img src="images/7h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="7s" value="7s"/><img src="images/7s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="8c" value="8c"/><img src="images/8c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="8d" value="8d"/><img src="images/8d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="8h" value="8h"/><img src="images/8h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="8s" value="8s"/><img src="images/8s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="9c" value="9c"/><img src="images/9c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="9d" value="9d"/><img src="images/9d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="9h" value="9h"/><img src="images/9h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="9s" value="9s"/><img src="images/9s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="Tc" value="Tc"/><img src="images/Tc.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Td" value="Td"/><img src="images/Td.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Th" value="Th"/><img src="images/Th.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Ts" value="Ts"/><img src="images/Ts.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="Jc" value="Jc"/><img src="images/Jc.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Jd" value="Jd"/><img src="images/Jd.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Jh" value="Jh"/><img src="images/Jh.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Js" value="Js"/><img src="images/Js.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="Qc" value="Qc"/><img src="images/Qc.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Qd" value="Qd"/><img src="images/Qd.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Qh" value="Qh"/><img src="images/Qh.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Qs" value="Qs"/><img src="images/Qs.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="Kc" value="Kc"/><img src="images/Kc.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Kd" value="Kd"/><img src="images/Kd.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Kh" value="Kh"/><img src="images/Kh.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Ks" value="Ks"/><img src="images/Ks.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card' id="Ac" value="Ac"/><img src="images/Ac.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Aced" value="Aced"/><img src="images/Aced.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="Ah" value="Ah"/><img src="images/Ah.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card' id="As" value="As"/><img src="images/As.png" />
                                </label>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Wybierz</button>
                    </div>
                </div>
            </div>
        </div>


    </div>

    <script>
        $('#cardModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget) // Button that triggered the modal
            var recipient = button.data('whatever') // Extract info from data-* attributes
            // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
            // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            var modal = $(this)
            modal.find('.modal-title').text('Wybierz karte dla: ' + recipient)
            modal.find('.modal-body input').val(recipient)
        })

    </script>
</body>
</html>