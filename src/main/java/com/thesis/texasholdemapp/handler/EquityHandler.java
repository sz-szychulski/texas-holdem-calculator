package com.thesis.texasholdemapp.handler;

import com.thesis.texasholdemapp.structure.EquityCalculator;
import com.thesis.texasholdemapp.structure.Hand;
import com.thesis.texasholdemapp.structure.HandEquity;
import com.thesis.texasholdemapp.structure.HandRanking;

import java.util.ArrayList;

public class EquityHandler {
    private boolean isBoard;
    private String boardCards;

    private ArrayList<String> handsString;
    private ArrayList<Hand> hands;

    EquityCalculator equityCalculator = new EquityCalculator();

    public EquityHandler(boolean isBoard, String boardCards, ArrayList<String> handsString, ArrayList<Hand> hands) {
        this.isBoard = isBoard;
        this.boardCards = boardCards;
        this.handsString = handsString;
        this.hands = hands;
    }


    public void calculateEquity() throws Exception {
        if (!boardCards.isEmpty()) {
            equityCalculator.setBoardFromString(boardCards);
        }

        for (String handString : handsString) {
            Hand hand = Hand.singleHandFromString(handString);
            hands.add(hand);
            equityCalculator.addHand(hand);
        }

        long startTime = System.currentTimeMillis();
        equityCalculator.calculateEquity();
        long elapsedTime = System.currentTimeMillis() - startTime;


        equityCalculator.printBoardCards();

        for(int index = 0; index < hands.size(); index++) {
            HandRanking handRanking = equityCalculator.getHandRanking(index);
            HandEquity handEquity = equityCalculator.getHandEquity(index);

            String preprend = equityCalculator.isBoardEmpty() ? "~" : "";

            System.out.println(String.format("Gracz %d: %s - %s --- %s%s", 1 + index, hands.get(index), handRanking, preprend, handEquity));
        }

        if (equityCalculator.isBoardEmpty()) {
            float elapsedSeconds = elapsedTime / 1000.0f;

            System.out.println(String.format("Zasymulowane %d boardow w %.1f sekund", equityCalculator.getMaxIterations(), elapsedSeconds));
        }

    }


}
