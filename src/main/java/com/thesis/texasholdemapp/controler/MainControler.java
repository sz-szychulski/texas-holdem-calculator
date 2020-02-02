package com.thesis.texasholdemapp.controler;

import com.thesis.texasholdemapp.builder.OutputBuilder;
import com.thesis.texasholdemapp.handler.EquityHandler;
import com.thesis.texasholdemapp.handler.ErrorHandler;
import com.thesis.texasholdemapp.model.Player;
import com.thesis.texasholdemapp.model.Simulation;
import com.thesis.texasholdemapp.model.User;
import com.thesis.texasholdemapp.service.*;
import com.thesis.texasholdemapp.structure.Card;
import com.thesis.texasholdemapp.structure.Hand;
import com.thesis.texasholdemapp.structure.HandRanking;
import com.thesis.texasholdemapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
public class MainControler {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ReportService reportService;

    private EquityHandler equityHandler = new EquityHandler();
    private OutputBuilder outputBuilder = new OutputBuilder();

    private ArrayList<String> hands                     = new ArrayList<>();
    private ArrayList<ArrayList<String>> stringHands    = new ArrayList<>();
    private ArrayList<Card> boardCards                  = new ArrayList<>();

    private String usedMethod = "";

    @GetMapping("/")
    public String welcome(Model model) {
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Niepoprawny login lub hasło.");

        if (logout != null)
            model.addAttribute("message", "Pomyślnie wylogowano.");

        return "login";
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

        equityHandler   = new EquityHandler();
        hands           = new ArrayList<>();
        stringHands     = new ArrayList<>();
        boardCards      = new ArrayList<>();

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
            equityHandler.getEquityCalculator().setMonteCarlo(true);
            equityHandler.getEquityCalculator().setMaxIterations(iterations);
            usedMethod = "Algorytm Monte Carlo";
        } else {
            equityHandler.getEquityCalculator().setMonteCarlo(false);
            usedMethod = "Algorytm iteracyjny";
        }

        if (outputBuilder.isNotEmpty(flop)) {
            equityHandler.setBoard(true);
            equityHandler.setBoardCards(outputBuilder.buildBoard(flop, turn, river));
        } else {
            equityHandler.setBoard(false);
        }

        equityHandler.setHandsString(hands);
        equityHandler.calculateEquity();

        if(equityHandler.isBoard()) {
            boardCards = equityHandler.getEquityCalculator().getBoardCards();
            model.addAttribute("board_cards", boardCards);
        } else {
            model.addAttribute("board_cards", "");
        }

        model.addAttribute("hands", equityHandler.getHands());
        model.addAttribute("hands_string", stringHands);
        model.addAttribute("players",  equityHandler.getHands().size() - 1);
        model.addAttribute("hand_rankings", equityHandler.getHandRankings());
        model.addAttribute("total_equity", equityHandler.getTotalEquitiesList());
        model.addAttribute("total_win", equityHandler.getTotalWinEquitiesList());
        model.addAttribute("total_split", equityHandler.getTotalSplitEquitiesList());

        return "results";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "simulationName") String simulationName) throws Exception {
        HashSet<Player> playersSet = new HashSet<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        StringBuilder boardCardsString = new StringBuilder();
        for (Card card : boardCards) {
            boardCardsString.append(card.toString());
        }

        Simulation currentSimulation = new Simulation(
                simulationName,
                username,
                boardCardsString.toString(),
                equityHandler.getEquityCalculator().isMonteCarlo(),
                equityHandler.getEquityCalculator().getMaxIterations(),
                date
                );

        simulationService.createSimulation(currentSimulation);

        for (int index = 0; index < equityHandler.getHands().size(); index++) {
            String cards = stringHands.get(index).get(0) + stringHands.get(index).get(1);
            Player currentPlayer = new Player(
                    cards,
                    equityHandler.getHandEquities().get(index).getBestHandsCount(),
                    equityHandler.getHandEquities().get(index).getSplitHandsCount(),
                    currentSimulation);
            playersSet.add(currentPlayer);
            playerService.createPlayer(currentPlayer);
        }

        currentSimulation.setPlayers(playersSet);
        simulationService.createSimulation(currentSimulation);

        return "redirect:/";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        List<Simulation> simulations = simulationService.getAllSimulations();
        ArrayList<Simulation> userSimulations = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        for (Simulation simulation : simulations) {
            if (simulation.getUserName().equals(username)) {
                userSimulations.add(simulation);
            }
        }

        model.addAttribute("simulations", userSimulations);

        return "reports";
    }

    @PostMapping("/generate_raport")
    public String generate(@RequestParam(value = "selected_simulation") Long simulationId, Model model) throws Exception {
        reportService.exportReport(simulationId);

        String path = System.getProperty("user.dir") + File.separator + "raport" + File.separator + "output" + File.separator +
                simulationService.getSimulation(simulationId).getSimulationName() + ".pdf";

        model.addAttribute("raport_path", path);

        return "generated";
    }

}
