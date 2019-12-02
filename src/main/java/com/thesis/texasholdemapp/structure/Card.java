package com.thesis.texasholdemapp.structure;

public class Card {
    private CardRanking ranking;
    private CardSuit suit;

    public enum CardSuit {
        CLUB, DIAMOND, HEART, SPADE
    }

    public Card(CardSuit suit, CardRanking ranking) {
        this.suit = suit;
        this.ranking = ranking;
    }

    public CardRanking getRank() {
        return ranking;
    }

    public void setRank(CardRanking ranking) {
        this.ranking = ranking;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    public static Card fromString(String value) throws Exception{
        if(value.length() != 2) {
            throw new Exception(String.format("Invalid card value: %s", value));
        }

        String stringRank = value.substring(0, 1).toLowerCase();
        String stringSuit = value.substring(1, 2).toLowerCase();

        CardSuit cardSuit;
        switch (stringSuit) {
            case ("c"):
                cardSuit = CardSuit.CLUB;
                break;
            case ("d"):
                cardSuit = CardSuit.DIAMOND;
                break;
            case ("h"):
                cardSuit = CardSuit.HEART;
                break;
            case ("s"):
                cardSuit = CardSuit.SPADE;
                break;
            default:
                throw new Exception(String.format("Wrong card suit: %s", stringSuit));
        }

        CardRanking cardRanking;
        switch (stringRank) {
            case ("t"):
                cardRanking = new CardRanking(CardRanking.TEN);
                break;
            case ("j"):
                cardRanking = new CardRanking(CardRanking.JACK);
                break;
            case ("q"):
                cardRanking = new CardRanking(CardRanking.QUEEN);
                break;
            case ("k"):
                cardRanking = new CardRanking(CardRanking.KING);
                break;
            case ("a"):
                cardRanking = new CardRanking(CardRanking.ACE);
                break;
            default:
                try {
                    int numRanking = Integer.parseInt(stringRank);
                    if (numRanking >= 2 && numRanking <= 9) {
                        cardRanking = new CardRanking(numRanking);
                    } else {
                        throw new Exception(String.format("Wrong card rank: %s", stringRank));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new Exception(String.format("Wrong card rank: %s", stringRank));
                }
        }
        return new Card(cardSuit, cardRanking);
    }
}
