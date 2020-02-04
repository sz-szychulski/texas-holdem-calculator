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
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/modal_style.css" />
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

        <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        </c:if>

        <form method="post" class="general" action="/calculate">
            <div>
                <label for="isMonteCarlo">Algorytm Monte Carlo: </label>
                <input type="checkbox" id="isMonteCarlo" name="isMonteCarlo" />
            </div>
            <div>
                <label for="iterations">Liczba iteracji: </label>
                <input type="number" id="iterations" name="iterations" min="1" disabled/>
            </div>
            <hr/>
            <div class="poker-table">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="board-cards">
                    <div class="flop">
                        <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="flop-1">
                            <img class="card-back" id="flop-1" src="images/card-back.png" />
                        </button>
                        <input type="text" id="flop-1-field" name="flop" hidden/>
                        <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="flop-2">
                            <img class="card-back" id="flop-2" src="images/card-back.png" />
                        </button>
                        <input type="text" id="flop-2-field" name="flop" hidden/>
                        <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="flop-3">
                            <img class="card-back" id="flop-3" src="images/card-back.png" />
                        </button>
                        <input type="text" id="flop-3-field" name="flop" hidden/>
                    </div>

                    <div class="turn">
                        <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="turn">
                            <img class="card-back" id="turn" src="images/card-back.png" />
                        </button>
                        <input type="text" id="turn-field" name="turn" hidden/>
                    </div>

                    <div class="river">
                        <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="river">
                            <img class="card-back" id="river" src="images/card-back.png" />
                        </button>
                        <input type="text" id="river-field" name="river" hidden/>
                    </div>
                </div>

                <div class="player1">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player1-1">
                        <img class="card-back" id="player1-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player1-1-field" name="player1" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player1-2">
                        <img class="card-back" id="player1-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player1-2-field" name="player1" hidden/>

                </div>
                <div class="player2">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player2-1">
                        <img class="card-back" id="player2-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player2-1-field" name="player2" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player2-2">
                        <img class="card-back" id="player2-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player2-2-field" name="player2" hidden/>
                </div>
                <div class="player3">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player3-1">
                        <img class="card-back" id="player3-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player3-1-field" name="player3" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player3-2">
                        <img class="card-back" id="player3-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player3-2-field" name="player3" hidden/>
                </div>
                <div class="player4">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player4-1">
                        <img class="card-back" id="player4-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player4-1-field" name="player4" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player4-2">
                        <img class="card-back" id="player4-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player4-2-field" name="player4" hidden/>
                </div>
                <div class="player5">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player5-1">
                        <img class="card-back" id="player5-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player5-1-field" name="player5" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player5-2">
                        <img class="card-back" id="player5-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player5-2-field" name="player5" hidden/>
                </div>
                <div class="player6">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player6-1">
                        <img class="card-back" id="player6-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player6-1-field" name="player6" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player6-2">
                        <img class="card-back" id="player6-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player6-2-field" name="player6" hidden/>
                </div>
                <div class="player7">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player7-1">
                        <img class="card-back" id="player7-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player7-1-field" name="player7" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player7-2">
                        <img class="card-back" id="player7-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player7-2-field" name="player7" hidden/>
                </div>
                <div class="player8">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player8-1">
                        <img class="card-back" id="player8-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player8-1-field" name="player8" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player8-2">
                        <img class="card-back" id="player8-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player8-2-field" name="player8" hidden/>
                </div>
                <div class="player9">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player9-1">
                        <img class="card-back" id="player9-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player9-1-field" name="player9" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player9-2">
                        <img class="card-back" id="player9-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player9-2-field" name="player9" hidden/>
                </div>
                <div class="player10">
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player10-1">
                        <img class="card-back" id="player10-1" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player10-1-field" name="player10" hidden/>
                    <button type="button" class="card" data-toggle="modal" data-target="#cardModal" data-card="player10-2">
                        <img class="card-back" id="player10-2" src="images/card-back.png" />
                    </button>
                    <input type="text" id="player10-2-field" name="player10" hidden/>
                </div>
                <div class="calculate-button">
                    <input type="submit" value="Oblicz" data-toggle="modal" data-target="#loadingModal"/>
                </div>
            </div>
        </form>

        <%-- Modal --%>
        <div class="modal fade" id="cardModal" tabindex="-1" role="dialog" aria-labelledby="cardModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form class="card-choose" id="card-pick-form">
                        <div class="modal-header">
                            <h5 class="modal-title" id="cardModalLabel">Choose card: </h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="2c"  value="2c" onclick="changeCard()" />
                                    <img src="images/2c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="2d" value="2d" onclick="changeCard()" />
                                    <img src="images/2d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="2h" value="2h" onclick="changeCard()" />
                                    <img src="images/2h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="2s" value="2s" onclick="changeCard()" />
                                    <img src="images/2s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="3c" value="3c" onclick="changeCard()" />
                                    <img src="images/3c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="3d" value="3d" onclick="changeCard()" />
                                    <img src="images/3d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="3h" value="3h" onclick="changeCard()" />
                                    <img src="images/3h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="3s" value="3s" onclick="changeCard()" />
                                    <img src="images/3s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="4c" value="4c" onclick="changeCard()" />
                                    <img src="images/4c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="4d" value="4d" onclick="changeCard()" />
                                    <img src="images/4d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="4h" value="4h" onclick="changeCard()"/>
                                    <img src="images/4h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="4s" value="4s" onclick="changeCard()" />
                                    <img src="images/4s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="5c" value="5c" onclick="changeCard()" />
                                    <img src="images/5c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="5d" value="5d" onclick="changeCard()"/>
                                    <img src="images/5d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="5h" value="5h" onclick="changeCard()"/>
                                    <img src="images/5h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="5s" value="5s" onclick="changeCard()"/>
                                    <img src="images/5s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="6c" value="6c" onclick="changeCard()"/>
                                    <img src="images/6c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="6d" value="6d" onclick="changeCard()" />
                                    <img src="images/6d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="6h" value="6h" onclick="changeCard()" />
                                    <img src="images/6h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="6s" value="6s" onclick="changeCard()" />
                                    <img src="images/6s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="7c" value="7c" onclick="changeCard()" />
                                    <img src="images/7c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="7d" value="7d" onclick="changeCard()" />
                                    <img src="images/7d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="7h" value="7h" onclick="changeCard()" />
                                    <img src="images/7h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="7s" value="7s" onclick="changeCard()" />
                                    <img src="images/7s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="8c" value="8c" onclick="changeCard()" />
                                    <img src="images/8c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="8d" value="8d" onclick="changeCard()" />
                                    <img src="images/8d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="8h" value="8h" onclick="changeCard()" />
                                    <img src="images/8h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="8s" value="8s" onclick="changeCard()" />
                                    <img src="images/8s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="9c" value="9c" onclick="changeCard()" />
                                    <img src="images/9c.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="9d" value="9d" onclick="changeCard()" />
                                    <img src="images/9d.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="9h" value="9h" onclick="changeCard()" />
                                    <img src="images/9h.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="9s" value="9s" onclick="changeCard()" />
                                    <img src="images/9s.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="Tc" value="Tc" onclick="changeCard()" />
                                    <img src="images/Tc.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Td" value="Td" onclick="changeCard()" />
                                    <img src="images/Td.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Th" value="Th" onclick="changeCard()" />
                                    <img src="images/Th.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Ts" value="Ts" onclick="changeCard()"/>
                                    <img src="images/Ts.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="Jc" value="Jc" onclick="changeCard()" />
                                    <img src="images/Jc.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Jd" value="Jd" onclick="changeCard()" />
                                    <img src="images/Jd.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Jh" value="Jh" onclick="changeCard()" />
                                    <img src="images/Jh.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Js" value="Js" onclick="changeCard()" />
                                    <img src="images/Js.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="Qc" value="Qc" onclick="changeCard()" />
                                    <img src="images/Qc.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Qd" value="Qd" onclick="changeCard()" />
                                    <img src="images/Qd.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Qh" value="Qh" onclick="changeCard()" />
                                    <img src="images/Qh.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Qs" value="Qs" onclick="changeCard()" />
                                    <img src="images/Qs.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="Kc" value="Kc" onclick="changeCard()" />
                                    <img src="images/Kc.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Kd" value="Kd" onclick="changeCard()" />
                                    <img src="images/Kd.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Kh" value="Kh" onclick="changeCard()" />
                                    <img src="images/Kh.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Ks" value="Ks" onclick="changeCard()" />
                                    <img src="images/Ks.png" />
                                </label>
                            </div>
                            <div class="form-group text-center">
                                <label>
                                    <input type="radio" name='card-pick' id="Ac" value="Ac" onclick="changeCard()" />
                                    <img src="images/Ac.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Aced" value="Aced" onclick="changeCard()" />
                                    <img src="images/Aced.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="Ah" value="Ah" onclick="changeCard()" />
                                    <img src="images/Ah.png" />
                                </label>
                                <label>
                                    <input type="radio" name='card-pick' id="As" value="As" onclick="changeCard()" />
                                    <img src="images/As.png" />
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="loadingModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                        <div class="modal-body text-center">
                            <h2>Obliczanie...</h2>
                            <div class="spinner-border" role="status">
                                <span class="sr-only">Obliczanie...</span>
                            </div>
                        </div>
                </div>
            </div>
        </div>

    </div>

    <script>
        var cardPos;

        //parsing data for modal
        $('#cardModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);

            cardPos = button.data('card');
            var modal = $(this);
            modal.find('.modal-title').text('Wybierz karte dla: ' + cardPos);
        });

        //changing card function
        function changeCard() {
            var pickCard = document.querySelector('input[name="card-pick"]:checked').value;
            var prevCard = $('#' + cardPos + '-field').val();
            if(prevCard !== "") {
                $('#' +  prevCard).removeAttr('disabled');
            }

            if(pickCard === "Aced") {
                $('#' + cardPos + '-field').val("Ad");
            } else {
                $('#' + cardPos + '-field').val(pickCard);
            }

            $('#' + cardPos).attr('src', 'images/' + pickCard + '.png');
            $('#' + pickCard).attr('disabled', true);
            $('#cardModal').modal('hide');
        }

        //toggle 'iterations' input
        document.getElementById('isMonteCarlo').onchange = function() {
            document.getElementById('iterations').disabled = !this.checked;
        };
    </script>
</body>
</html>