package com.thesis.texasholdemapp.structure;

public class Card implements Comparable<Card> {
    private Suit suit;
    private CardRanking ranking;

    public Card(Suit suit, CardRanking ranking) {
        this.suit = suit;
        this.ranking = ranking;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public CardRanking getRank() {
        return ranking;
    }

    public void setRank(CardRanking ranking) {
        this.ranking = ranking;
    }

    public static Card fromString(String value) throws Exception {
        if(value.length() != 2) {
            throw new Exception(String.format("Invalid card value: %s", value));
        }

        String stringRank = value.substring(0, 1).toLowerCase();
        String stringSuit = value.substring(1, 2).toLowerCase();

        Suit suit;
        switch (stringSuit) {
            case ("c"):
                suit = Suit.CLUB;
                break;
            case ("d"):
                suit = Suit.DIAMOND;
                break;
            case ("h"):
                suit = Suit.HEART;
                break;
            case ("s"):
                suit = Suit.SPADE;
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

        return new Card(suit, cardRanking);
    }

    @Override
    public int compareTo(Card card) {
        return card.getRank().getCardRanking() - this.getRank().getCardRanking();
    }

    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;
    }
}
