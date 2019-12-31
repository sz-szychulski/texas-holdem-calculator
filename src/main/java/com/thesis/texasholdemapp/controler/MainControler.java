package com.thesis.texasholdemapp.controler;

import com.thesis.texasholdemapp.handler.EquityHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainControler {

    @GetMapping("/")
    public String welcome(Model model) {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam(value = "player1", required = false) String player_1,
                            @RequestParam(value = "player2", required = false) String player_2,
                            @RequestParam(value = "player3", required = false) String player_3,
                            @RequestParam(value = "player4", required = false) String player_4,
                            @RequestParam(value = "player5", required = false) String player_5,
                            @RequestParam(value = "player6", required = false) String player_6,
                            @RequestParam(value = "player7", required = false) String player_7,
                            @RequestParam(value = "player8", required = false) String player_8,
                            @RequestParam(value = "player9", required = false) String player_9,
                            @RequestParam(value = "player10", required = false) String player_10,
                            @RequestParam(value = "board", required = false) String board,
                            Model model) throws Exception{

        EquityHandler equityHandler = new EquityHandler();
        ArrayList<String> hands = new ArrayList<>();

        if (!player_1.equals(""))        hands.add(player_1);
        if (!player_2.equals(""))        hands.add(player_2);
        if (!player_3.equals(""))        hands.add(player_3);
        if (!player_4.equals(""))        hands.add(player_4);
        if (!player_5.equals(""))        hands.add(player_5);
        if (!player_6.equals(""))        hands.add(player_6);
        if (!player_7.equals(""))        hands.add(player_7);
        if (!player_8.equals(""))        hands.add(player_8);
        if (!player_9.equals(""))        hands.add(player_9);
        if (!player_10.equals(""))       hands.add(player_10);

        if (!board.isEmpty()) {
            equityHandler.setBoard(true);
            equityHandler.setBoardCards(board);
        } else {
            equityHandler.setBoard(false);
        }

        equityHandler.setHandsString(hands);
        equityHandler.calculateEquity();

        return "redirect:/";
    }
}
