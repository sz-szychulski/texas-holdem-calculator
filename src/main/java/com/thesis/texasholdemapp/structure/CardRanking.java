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

    @Override
    public String toString() {
        String ranking;

        if(cardRanking == 10)				ranking = "T";
        else if(cardRanking == 11)		    ranking = "J";
        else if(cardRanking == 12)		    ranking = "Q";
        else if(cardRanking == 13)		    ranking = "K";
        else if(cardRanking == 14)		    ranking = "A";
        else							    ranking = String.valueOf(cardRanking);

        return ranking;
    }

    public String toStringPlural() {
        if(cardRanking == 2)				return "twos";
        else if(cardRanking == 3)			return "threes";
        else if(cardRanking == 4)			return "fours";
        else if(cardRanking == 5)			return "fives";
        else if(cardRanking == 6)			return "sixes";
        else if(cardRanking == 7)			return "sevens";
        else if(cardRanking == 8)			return "eights";
        else if(cardRanking == 9)			return "nines";
        else if(cardRanking == 10)		    return "tens";
        else if(cardRanking == 11)		    return "jacks";
        else if(cardRanking == 12)		    return "queens";
        else if(cardRanking == 13)		    return "kings";
        else 							    return "aces";
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

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof CardRanking)) return false;

        CardRanking otherRank = (CardRanking)obj;
        return (otherRank.getCardRanking() == this.getCardRanking());
    }
}
