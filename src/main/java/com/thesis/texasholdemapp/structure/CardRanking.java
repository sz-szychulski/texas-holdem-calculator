package com.thesis.texasholdemapp.structure;

public class CardRanking implements Comparable<CardRanking> {
    private int cardRanking;

    public final static int TWO     = 2;
    public final static int THREE   = 3;
    public final static int FOUR    = 4;
    public final static int FIVE    = 5;
    public final static int SIX     = 6;
    public final static int SEVEN   = 7;
    public final static int EIGHT   = 8;
    public final static int NINE    = 9;
    public final static int TEN     = 10;
    public final static int JACK    = 11;
    public final static int QUEEN   = 12;
    public final static int KING    = 13;
    public final static int ACE     = 14;

    public int getCardRanking() {
        return cardRanking;
    }

    public CardRanking(int cardRanking) {
        this.cardRanking = cardRanking;
    }

    public String changeToString() {
        String stringCardRanking;
        switch (cardRanking) {
            case 10:
                stringCardRanking = "T";
                break;
            case 11:
                stringCardRanking = "J";
                break;
            case 12:
                stringCardRanking = "Q";
                break;
            case 13:
                stringCardRanking = "K";
                break;
            case 14:
                stringCardRanking = "A";
                break;
            default:
                stringCardRanking = String.valueOf(cardRanking);
        }
        return stringCardRanking;
    }

    public static CardRanking[] values() {
        CardRanking[] allRankings = new CardRanking[13];

        allRankings[0]   = new CardRanking(TWO);
        allRankings[1]   = new CardRanking(THREE);
        allRankings[2]   = new CardRanking(FOUR);
        allRankings[3]   = new CardRanking(FIVE);
        allRankings[4]   = new CardRanking(SIX);
        allRankings[5]   = new CardRanking(SEVEN);
        allRankings[6]   = new CardRanking(EIGHT);
        allRankings[7]   = new CardRanking(NINE);
        allRankings[8]   = new CardRanking(TEN);
        allRankings[9]   = new CardRanking(JACK);
        allRankings[10]  = new CardRanking(QUEEN);
        allRankings[11]  = new CardRanking(KING);
        allRankings[12]  = new CardRanking(ACE);

        return allRankings;
    }

    @Override
    public int compareTo(CardRanking cardRanking) {
        return this.getCardRanking() - cardRanking.getCardRanking();
    }
}
