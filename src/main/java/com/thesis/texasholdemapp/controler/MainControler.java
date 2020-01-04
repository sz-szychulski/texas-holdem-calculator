package com.thesis.texasholdemapp.controler;

import com.thesis.texasholdemapp.handler.EquityHandler;
import com.thesis.texasholdemapp.handler.ErrorHandler;
import com.thesis.texasholdemapp.structure.Card;
import com.thesis.texasholdemapp.structure.Hand;
import com.thesis.texasholdemapp.structure.HandRanking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainControler {

    private EquityHandler equityHandler;
    private ErrorHandler errorHandler;

    @GetMapping("/")
    public String welcome(Model model) {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam(value = "isMonteCarlo", required = false) String isMonteCarlo,
                            @RequestParam(value = "iterations", required = false)   Integer iterations,
                            @RequestParam(value = "flop", required = false)         String[] flop,
                            @RequestParam(value = "turn", required = false)         String turn,
                            @RequestParam(value = "river", required = false)        String river,
                            @RequestParam(value = "player1", required = false)      String[] player_1,
                            @RequestParam(value = "player2", required = false)      String[] player_2,
                            @RequestParam(value = "player3", required = false)      String[] player_3,
                            @RequestParam(value = "player4", required = false)      String[] player_4,
                            @RequestParam(value = "player5", required = false)      String[] player_5,
                            @RequestParam(value = "player6", required = false)      String[] player_6,
                            @RequestParam(value = "player7", required = false)      String[] player_7,
                            @RequestParam(value = "player8", required = false)      String[] player_8,
                            @RequestParam(value = "player9", required = false)      String[] player_9,
                            @RequestParam(value = "player10", required = false)     String[] player_10,
                            Model model) throws Exception{

        equityHandler = new EquityHandler();
        ArrayList<String> hands = new ArrayList<>();

        if (isNotEmpty(player_1))  hands.add(handFromArray(player_1));
        if (isNotEmpty(player_2))  hands.add(handFromArray(player_2));
        if (isNotEmpty(player_3))  hands.add(handFromArray(player_3));
        if (isNotEmpty(player_4))  hands.add(handFromArray(player_4));
        if (isNotEmpty(player_5))  hands.add(handFromArray(player_5));
        if (isNotEmpty(player_6))  hands.add(handFromArray(player_6));
        if (isNotEmpty(player_7))  hands.add(handFromArray(player_7));
        if (isNotEmpty(player_8))  hands.add(handFromArray(player_8));
        if (isNotEmpty(player_9))  hands.add(handFromArray(player_9));
        if (isNotEmpty(player_10)) hands.add(handFromArray(player_10));


        if (isNotEmpty(flop)) {
            equityHandler.setBoard(true);
            equityHandler.setBoardCards(buildBoard(flop, turn, river));
        } else {
            equityHandler.setBoard(false);
        }

        equityHandler.setHandsString(hands);
        equityHandler.calculateEquity();

        if (equityHandler != null) {
            ArrayList<Hand> handsList = equityHandler.getHands();
            ArrayList<HandRanking> handRankings = equityHandler.getHandRankings();

            ArrayList<Double> totalEquitiesList = equityHandler.getTotalEquitiesList();
            ArrayList<Double> totalWinEquitiesList = equityHandler.getTotalWinEquitiesList();
            ArrayList<Double> totalSplitEquitiesList= equityHandler.getTotalSplitEquitiesList();

            float elapsedSeconds = equityHandler.getElapsedSeconds();

            ArrayList<Card> boardCards;

            if(equityHandler.isBoard()) {
                boardCards = equityHandler.getBoardCardsList();
                model.addAttribute("board_cards", boardCards);
            }

            model.addAttribute("hands", handsList);
            model.addAttribute("players", handsList.size() - 1);
            model.addAttribute("hand_rankings", handRankings);
            model.addAttribute("total_equity", totalEquitiesList);
            model.addAttribute("total_win", totalWinEquitiesList);
            model.addAttribute("total_split", totalSplitEquitiesList);
            model.addAttribute("elapsed_seconds", elapsedSeconds);
        }

        return "results";
    }

    private boolean isNotEmpty(String[] array) {
        for (String value : array) {
            if (value.equals("")) {
                return false;
            }
        }
        return true;
    }

    private String handFromArray(String[] array) {
        StringBuilder hand = new StringBuilder();
        for (String card : array) {
            hand.append(card);
        }
        return hand.toString();
    }

    private String buildBoard(String[] flop, String turn, String river) {
        StringBuilder board = new StringBuilder();

        if (isNotEmpty(flop)) {
            for (String card : flop) {
                board.append(card);
            }
            if (!turn.equals("")) {
                board.append(turn);
                if (!river.equals("")) board.append(river);
            }
        }

        return board.toString();
    }
}
