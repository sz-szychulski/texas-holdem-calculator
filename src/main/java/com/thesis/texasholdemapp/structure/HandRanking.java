package com.thesis.texasholdemapp.structure;

import java.util.ArrayList;
import java.util.Collections;

public class HandRanking {

    private HandRanking.Ranking handRanking;
    private ArrayList<CardRanking> highCardRanking;

    public HandRanking(Ranking handRanking, ArrayList<CardRanking> highCardRanking) {
        this.handRanking = handRanking;
        this.highCardRanking = highCardRanking;
    }

    public Ranking getHandRanking() {
        return handRanking;
    }

    public void setHandRanking(Ranking handRanking) {
        this.handRanking = handRanking;
    }

    public ArrayList<CardRanking> getCardRankings() {
        return highCardRanking;
    }

    public void setCardRankings(ArrayList<CardRanking> cardRankings) {
        this.highCardRanking = cardRankings;
    }

    public void addHighCard(CardRanking cardRanking) {
        highCardRanking.add(cardRanking);
    }

    public static HandRanking evaluate(Card... inputCards) {
        HandRanking handRanking;

        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card card : inputCards) {
            cards.add(card);
        }

        Collections.sort(cards);


    }

    public enum Ranking {
        HIGH_CARD, PAIR, TWO_PAIR, TRIPS, STRAIGHT, FLUSH, FULL_HOUSE, QUADS, STRAIGHT_FLUSH;
    }
}
