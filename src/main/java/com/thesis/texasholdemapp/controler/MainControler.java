package com.thesis.texasholdemapp.controler;

import com.thesis.texasholdemapp.builder.OutputBuilder;
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

    private OutputBuilder outputBuilder = new OutputBuilder();
    private ErrorHandler errorHandler;

    @GetMapping("/")
    public String welcome(Model model) {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam(value = "isMonteCarlo", required = false) boolean isMonteCarlo,
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

        EquityHandler equityHandler = new EquityHandler();
        ArrayList<String> hands = new ArrayList<>();
        ArrayList<ArrayList<String>> stringHands = new ArrayList<>();

        if (outputBuilder.isNotEmpty(player_1)) {
            hands.add(outputBuilder.handFromArray(player_1));
            stringHands.add(outputBuilder.buildHandArray(player_1));
        }
        if (outputBuilder.isNotEmpty(player_2)) {
            hands.add(outputBuilder.handFromArray(player_2));
            stringHands.add(outputBuilder.buildHandArray(player_2));
        }
        if (outputBuilder.isNotEmpty(player_3)) {
            hands.add(outputBuilder.handFromArray(player_3));
            stringHands.add(outputBuilder.buildHandArray(player_3));
        }
        if (outputBuilder.isNotEmpty(player_4)) {
            hands.add(outputBuilder.handFromArray(player_4));
            stringHands.add(outputBuilder.buildHandArray(player_4));
        }
        if (outputBuilder.isNotEmpty(player_5)) {
            hands.add(outputBuilder.handFromArray(player_5));
            stringHands.add(outputBuilder.buildHandArray(player_5));
        }
        if (outputBuilder.isNotEmpty(player_6)) {
            hands.add(outputBuilder.handFromArray(player_6));
            stringHands.add(outputBuilder.buildHandArray(player_6));
        }
        if (outputBuilder.isNotEmpty(player_7)) {
            hands.add(outputBuilder.handFromArray(player_7));
            stringHands.add(outputBuilder.buildHandArray(player_7));
        }
        if (outputBuilder.isNotEmpty(player_8)) {
            hands.add(outputBuilder.handFromArray(player_8));
            stringHands.add(outputBuilder.buildHandArray(player_8));
        }
        if (outputBuilder.isNotEmpty(player_9)) {
            hands.add(outputBuilder.handFromArray(player_9));
            stringHands.add(outputBuilder.buildHandArray(player_9));
        }
        if (outputBuilder.isNotEmpty(player_10)) {
            hands.add(outputBuilder.handFromArray(player_10));
            stringHands.add(outputBuilder.buildHandArray(player_10));
        }

        if (isMonteCarlo) {
            equityHandler.isMonteCarlo(true);
            equityHandler.setIterations(iterations);
        } else equityHandler.isMonteCarlo(false);

        if (outputBuilder.isNotEmpty(flop)) {
            equityHandler.setBoard(true);
            equityHandler.setBoardCards(outputBuilder.buildBoard(flop, turn, river));
        } else {
            equityHandler.setBoard(false);
        }

        equityHandler.setHandsString(hands);
        equityHandler.calculateEquity();

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
        } else {
            model.addAttribute("board_cards", "");
        }

        model.addAttribute("hands", handsList);
        model.addAttribute("hands_string", stringHands);
        model.addAttribute("players", handsList.size() - 1);
        model.addAttribute("hand_rankings", handRankings);
        model.addAttribute("total_equity", totalEquitiesList);
        model.addAttribute("total_win", totalWinEquitiesList);
        model.addAttribute("total_split", totalSplitEquitiesList);
        model.addAttribute("elapsed_seconds", elapsedSeconds);

        return "results";
    }
}
