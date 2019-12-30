package com.thesis.texasholdemapp.handler;

import com.thesis.texasholdemapp.structure.EquityCalculator;
import com.thesis.texasholdemapp.structure.Hand;
import com.thesis.texasholdemapp.structure.HandEquity;
import com.thesis.texasholdemapp.structure.HandRanking;

import java.util.ArrayList;

public class EquityHandler {
    private boolean isBoard = false;
    private String boardCards = "";

    private ArrayList<String> handsString = new ArrayList<>();
    private ArrayList<Hand> hands = new ArrayList<>();

    private ArrayList<HandRanking> handRankings = new ArrayList<>();
    private ArrayList<HandEquity> handEquities = new ArrayList<>();
    private ArrayList<HandEquity> splitEquities = new ArrayList<>();

    private EquityCalculator equityCalculator = new EquityCalculator();

    public EquityHandler() {
    }

    public EquityHandler(boolean isBoard, String boardCards, ArrayList<String> handsString, ArrayList<Hand> hands) {
        this.isBoard = isBoard;
        this.boardCards = boardCards;
        this.handsString = handsString;
        this.hands = hands;
    }

    public boolean isBoard() {
        return isBoard;
    }

    public void setBoard(boolean board) {
        isBoard = board;
    }

    public String getBoardCards() {
        return boardCards;
    }

    public void setBoardCards(String boardCards) {
        this.boardCards = boardCards;
    }

    public ArrayList<String> getHandsString() {
        return handsString;
    }

    public void setHandsString(ArrayList<String> handsString) {
        this.handsString = handsString;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public void setHands(ArrayList<Hand> hands) {
        this.hands = hands;
    }

    public ArrayList<HandRanking> getHandRankings() {
        return handRankings;
    }

    public void setHandRankings(ArrayList<HandRanking> handRankings) {
        this.handRankings = handRankings;
    }

    public ArrayList<HandEquity> getHandEquities() {
        return handEquities;
    }

    public void setHandEquities(ArrayList<HandEquity> handEquities) {
        this.handEquities = handEquities;
    }

    public void calculateEquity() {
        try {
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

                handRankings.add(handRanking);
                handEquities.add(handEquity);

                double totalEquity = handEquity.getEquity() + (handEquity.getSplitEquity() / hands.size());
                double winEquity = handEquity.getEquity();
                double splitEquity = handEquity.getSplitEquity() / hands.size();

                System.out.println(String.format("Gracz %d: %s - %s --- ~%.2f %%", 1 + index, hands.get(index), handRanking, totalEquity));
                System.out.println(String.format("Wygrana: ~%.2f %%", winEquity));
                System.out.println(String.format("Split pot: ~%.2f %%", splitEquity));
            }
            float elapsedSeconds = elapsedTime / 1000.0f;

            System.out.println(String.format("Zasymulowane %d boardow w %.1f sekund", equityCalculator.getMaxIterations(), elapsedSeconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
