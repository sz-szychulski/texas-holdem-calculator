package com.thesis.texasholdemapp.structure;

public class Card implements Comparable<Card> {
    private CardRanking ranking;
    private CardSuit suit;

    public enum CardSuit {
        CLUB, DIAMOND, HEART, SPADE
    }

    public Card(CardRanking ranking, CardSuit suit) {
        this.ranking = ranking;
        this.suit = suit;
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
            throw new Exception(String.format("Niepoprawna karta: %s", value));
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
                throw new Exception(String.format("Zły format koloru karty: %s", stringSuit));
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
                        throw new Exception(String.format("Zły format karty: %s", stringRank));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new Exception(String.format("Zły format karty: %s", stringRank));
                }
        }
        return new Card(cardRanking, cardSuit);
    }

    @Override
    public int compareTo(Card card) {
        return card.getRank().getCardRanking() - this.getRank().getCardRanking();
    }

    @Override
    public String toString() {
        String output = "";
        if (suit == CardSuit.CLUB)          output = "c";
        else if (suit == CardSuit.HEART)    output = "h";
        else if (suit == CardSuit.DIAMOND)  output = "d";
        else if (suit == CardSuit.SPADE)    output = "s";

        return this.getRank().toString() + output;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) return false;

        Card card = (Card)obj;
        return (card.getSuit().ordinal() == this.getSuit().ordinal() && card.getRank().equals(this.getRank()));
    }
}
