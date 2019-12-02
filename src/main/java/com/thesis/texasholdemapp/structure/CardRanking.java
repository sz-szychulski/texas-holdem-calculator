package com.thesis.texasholdemapp.structure;

public class CardRanking {
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

    public void setCardRanking(int cardRanking) {
        this.cardRanking = cardRanking;
    }

    public CardRanking(int cardRanking) {
        this.cardRanking = cardRanking;
    }

    @Override
    public String toString() {
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
        CardRanking[] allRanking = new CardRanking[13];

        allRanking[0] = new CardRanking(TWO);
        allRanking[1] = new CardRanking(THREE);
        allRanking[2] = new CardRanking(FOUR);
        allRanking[3] = new CardRanking(FIVE);
        allRanking[4] = new CardRanking(SIX);
        allRanking[5] = new CardRanking(SEVEN);
        allRanking[6] = new CardRanking(EIGHT);
        allRanking[7] = new CardRanking(NINE);
        allRanking[8] = new CardRanking(TEN);
        allRanking[9] = new CardRanking(JACK);
        allRanking[10] = new CardRanking(QUEEN);
        allRanking[11] = new CardRanking(KING);
        allRanking[12] = new CardRanking(ACE);

        return allRanking;
    }
}
