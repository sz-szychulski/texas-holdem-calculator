package com.thesis.texasholdemapp.structure;

import java.util.ArrayList;

public class HandRanking {

    private HandRanking.Ranking handRanking;
    private ArrayList<CardRanking> cardRankings;

    public HandRanking(Ranking handRanking, ArrayList<CardRanking> cardRankings) {
        this.handRanking = handRanking;
        this.cardRankings = cardRankings;
    }

    public Ranking getHandRanking() {
        return handRanking;
    }

    public void setHandRanking(Ranking handRanking) {
        this.handRanking = handRanking;
    }

    public ArrayList<CardRanking> getCardRankings() {
        return cardRankings;
    }

    public void setCardRankings(ArrayList<CardRanking> cardRankings) {
        this.cardRankings = cardRankings;
    }



    public enum Ranking {
        HIGH_CARD, PAIR, TWO_PAIR, TRIPS, STRAIGHT, FLUSH, FULL_HOUSE, QUADS, STRAIGHT_FLUSH;
    }
}
