package com.thesis.texasholdemapp.handler;

import com.thesis.texasholdemapp.structure.*;

import java.util.ArrayList;

public class EquityHandler {
    private boolean isBoard = false;
    private String boardCards = "";

    private ArrayList<String> handsString = new ArrayList<>();
    private ArrayList<Hand> hands = new ArrayList<>();

    private ArrayList<HandRanking> handRankings = new ArrayList<>();
    private ArrayList<HandEquity> handEquities = new ArrayList<>();

    private ArrayList<Double> totalEquitiesList = new ArrayList<>();
    private ArrayList<Double> totalWinEquitiesList = new ArrayList<>();
    private ArrayList<Double> totalSplitEquitiesList = new ArrayList<>();

    private float elapsedSeconds;

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

    public ArrayList<Double> getTotalEquitiesList() {
        return totalEquitiesList;
    }

    public void setTotalEquitiesList(ArrayList<Double> totalEquitiesList) {
        this.totalEquitiesList = totalEquitiesList;
    }

    public ArrayList<Double> getTotalWinEquitiesList() {
        return totalWinEquitiesList;
    }

    public void setTotalWinEquitiesList(ArrayList<Double> totalWinEquitiesList) {
        this.totalWinEquitiesList = totalWinEquitiesList;
    }

    public ArrayList<Double> getTotalSplitEquitiesList() {
        return totalSplitEquitiesList;
    }

    public void setTotalSplitEquitiesList(ArrayList<Double> totalSplitEquitiesList) {
        this.totalSplitEquitiesList = totalSplitEquitiesList;
    }

    public float getElapsedSeconds() {
        return elapsedSeconds;
    }

    public void setElapsedSeconds(float elapsedSeconds) {
        this.elapsedSeconds = elapsedSeconds;
    }

    public ArrayList<Card> getBoardCardsList() {
        return equityCalculator.getBoardCards();
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

            float minSpliters = hands.size();
            for(int index = 0; index < hands.size(); index++) {
                HandRanking handRanking = equityCalculator.getHandRanking(index);
                HandEquity handEquity = equityCalculator.getHandEquity(index);

                float splitCounter = equityCalculator.getSplitCounter();
                float averageSpliters = hands.size();

                if (splitCounter != 0) {
                    averageSpliters = (splitCounter / handEquity.getSplitHandsCount());
                }

                if(averageSpliters < minSpliters) {
                    minSpliters = averageSpliters;
                }

                handRankings.add(handRanking);
                handEquities.add(handEquity);

                double totalEquity = handEquity.getEquity() + (handEquity.getSplitEquity() / minSpliters);
                double winEquity = handEquity.getEquity();
                double splitEquity = handEquity.getSplitEquity() / minSpliters;

                totalEquitiesList.add(totalEquity);
                totalWinEquitiesList.add(winEquity);
                totalSplitEquitiesList.add(splitEquity);

                System.out.println(String.format("Gracz %d: %s - %s --- ~%.2f %%", 1 + index, hands.get(index), handRanking, totalEquity));
                System.out.println(String.format("Wygrana: ~%.2f %%", winEquity));
                System.out.println(String.format("Split pot: ~%.2f %%", splitEquity));
            }

            elapsedSeconds = elapsedTime / 1000.0f;
            System.out.println(String.format("Zasymulowane %d boardow w %.1f sekund", equityCalculator.getMaxIterations(), elapsedSeconds));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
