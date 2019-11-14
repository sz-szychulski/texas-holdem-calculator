package com.thesis.texasholdemapp.structure;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private HandRanking handRanking;

    public Hand(Card card1, Card card2) {
        this.cards.add(card1);
        this.cards.add(card2);
        this.handRanking = null;
    }

    public Card getCard(int cardIndex) {
        return (Card)this.cards.get(cardIndex);
    }

    public static Hand singleHandFromString(String value) throws Exception {
        int length = value.length();
        if (length < 4) {
            throw new Exception(String.format("Wrong card value: %s", value));
        } else {
            String stringCard1 = value.substring(0, 2);
            String stringCard2 = value.substring(length - 2);
            return new Hand(Card.fromString(stringCard1), Card.fromString(stringCard2));
        }
    }

    //TODO
    //prepare multi hand builder from string method
}
