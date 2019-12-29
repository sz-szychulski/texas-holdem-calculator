package com.thesis.texasholdemapp.controler;

import com.thesis.texasholdemapp.handler.EquityHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainControler {

    @GetMapping("/")
    public String welcome(Model model) {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("player1") String player_1,
                            @RequestParam("player2") String player_2,
                            @RequestParam("board") String board,
                            Model model) throws Exception {
        EquityHandler equityHandler = new EquityHandler();

        ArrayList<String> hands = new ArrayList<>();

        hands.add(player_1);
        hands.add(player_2);

        equityHandler.setHandsString(hands);
        equityHandler.setBoard(true);
        equityHandler.setBoardCards(board);
        equityHandler.calculateEquity();

        return "redirect:/";
    }
}
