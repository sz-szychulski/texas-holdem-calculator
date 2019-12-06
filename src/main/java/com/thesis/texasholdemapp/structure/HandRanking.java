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

        ArrayList<Card> clubCards 		= new ArrayList<Card>();
        ArrayList<Card> diamondCards 	= new ArrayList<Card>();
        ArrayList<Card> heartCards 		= new ArrayList<Card>();
        ArrayList<Card> spadeCards 		= new ArrayList<Card>();

        for (Card card : cards) {
            if (card.getSuit() == Card.CardSuit.CLUB)
                clubCards.add(card);
            else if (card.getSuit() == Card.CardSuit.DIAMOND)
                diamondCards.add(card);
            else if (card.getSuit() == Card.CardSuit.HEART)
                heartCards.add(card);
            else if (card.getSuit() == Card.CardSuit.SPADE)
                spadeCards.add(card);
        }

        boolean clubFlush               = false;
        boolean diamondFlush            = false;
        boolean heartFlush              = false;
        boolean spadeFlush              = false;
        ArrayList<Card> flushCards      = null;

        int inputCardsCount = inputCards.length;

        //for 5 and more cards
        if(inputCardsCount >= 5) {
            if (clubCards.size() >= 5) {
                clubFlush = true;
                flushCards = clubCards;
            }
            else if (diamondCards.size() >= 5) {
                diamondFlush = true;
                flushCards = diamondCards;
            }
            else if (heartCards.size() >= 5) {
                heartFlush = true;
                flushCards = heartCards;
            }
            else if (spadeCards.size() >= 5) {
                spadeFlush = true;
                flushCards = spadeCards;
            }

            if(flushCards != null || flushCards.isEmpty()) {
                ArrayList<CardRanking> straightCards = new ArrayList<CardRanking>();

                CardRanking cardRanking = flushCards.get(0).getRank();
                int straightCount = 1;
                straightCards.add(cardRanking);

                for(int cardIndex = 1; cardIndex < flushCards.size(); cardIndex++) {
                    int compareRanks = cardRanking.compareTo(flushCards.get().getRank());
                    if(straightCount == 3) {
                        Card lowestCard = flushCards.get(i);
                        Card highestCard = flushCards.get(0);

                        if(highestCard.getRank().ordinal() == CardRank.ACE && lowestCard.getRank().ordinal() == CardRank.TWO) {
                            ranking = new HandRanking(Ranking.STRAIGHT_FLUSH);
                            for(CardRank cr : straightCards) {
                                ranking.addHighCard(cr);
                            }
                            ranking.addHighCard(lowestCard.getRank());
                            ranking.addHighCard(highestCard.getRank());
                            return ranking;
                        }
                    }
                    if(cmp != 1) {
                        straightCards = new ArrayList<CardRank>();
                        straightCount = 0;
                    }

                    r = flushCards.get(i).getRank();
                    straightCount++;
                    straightCards.add(r);


                    if(straightCount == 5) {
                        ranking = new HandRanking(Ranking.STRAIGHT_FLUSH);
                        for(CardRank cr : straightCards) {
                            ranking.addHighCard(cr);
                        }
                        return ranking;
                    }
                }
            }
        }


        if(cardsCount >= 4) {
            // Look for four of a kind
            HashMap<Integer, Integer> rankCounts = new HashMap<Integer, Integer>();

            for(int i = 0; i < cardsCount; i++) {
                Integer cardRankInt = new Integer(pCards[i].getRank().value());
                Integer count = rankCounts.get(cardRankInt);

                if(count == null)
                    count = new Integer(1);
                else
                    count += 1;

                rankCounts.put(cardRankInt, count);
            }



            for (Map.Entry<Integer, Integer> entry : rankCounts.entrySet())
            {
                if(entry.getValue() == 4) {
                    CardRank quadsRank = new CardRank(entry.getKey());

                    ranking = new HandRanking(Ranking.QUADS);
                    ranking.addHighCard(quadsRank);
                    for(Card c : cards) {
                        CardRank cr = c.getRank();
                        if(!cr.equals(quadsRank) && !cr.equals(quadsRank)) {
                            ranking.addHighCard(cr);
                            break;
                        }
                    }

                    return ranking;
                }
            }
        }

        if(cardsCount >= 5) {
            // Look for full house
            ArrayList<CardRank> pairRanks 		= new ArrayList<CardRank>();
            ArrayList<CardRank> tripRanks 		= new ArrayList<CardRank>();


            for(int i = 0; i < cardsCount; i++) {
                for(int j = i + 1; j < cardsCount; j++) {
                    boolean foundTrips = false;
                    for(int k = j + 1; k < cardsCount; k++) {
                        if(pCards[i].getRank().equals(pCards[j].getRank()) &&
                                pCards[i].getRank().equals(pCards[k].getRank()) &&
                                !tripRanks.contains(pCards[i].getRank())) {

                            tripRanks.add(pCards[i].getRank());
                            foundTrips = true;
                        }
                    }


                    if(!foundTrips && pCards[i].getRank().equals(pCards[j].getRank()) && !pairRanks.contains(pCards[i].getRank()) && !tripRanks.contains(pCards[i].getRank())) {
                        pairRanks.add(pCards[i].getRank());
                    }
                }
            }

            if(pairRanks.size() > 0 && tripRanks.size() > 0) {
                Collections.sort(pairRanks);

                ranking = new HandRanking(Ranking.FULL_HOUSE);
                ranking.addHighCard(tripRanks.get(0));
                ranking.addHighCard(pairRanks.get(0));
                return ranking;
            }




            // Look for flush
            if (clubFlush) 			flushCards = clubCards;
            else if (diamondFlush) 	flushCards = diamondCards;
            else if (heartFlush) 	flushCards = heartCards;
            else if (spadeFlush) 	flushCards = spadeCards;

            if(flushCards != null) {
                ranking = new HandRanking(Ranking.FLUSH);
                for (int flush = 0; flush < 5; flush++)
                    ranking.addHighCard(flushCards.get(flush).getRank());
                return ranking;
            }



            // Look for straight
            for(int i = 0; i < cardsCount - 3; i++) {
                CardRank r = cards.get(i).getRank();
                int straightCount = 0;

                ArrayList<CardRank> straightCards = new ArrayList<CardRank>();
                straightCards.add(r);

                for(int j = i + 1; j < cards.size(); j++) {
                    CardRank r2 = cards.get(j).getRank();
                    int cmp = r.compareTo(r2);

                    if(cmp == 0)
                        continue;
                    else if(cmp != 1)
                        break;


                    r = cards.get(j).getRank();
                    straightCards.add(r);


                    straightCount++;
                    if(straightCount == 4) {
                        ranking = new HandRanking(Ranking.STRAIGHT);
                        for(CardRank cr : straightCards) {
                            ranking.addHighCard(cr);
                        }
                        return ranking;
                    }
                }

                if(straightCount == 3 && i+1 == cardsCount - 3) {
                    Card lowestCard = cards.get(i+3);
                    Card highestCard = cards.get(0);
                    if(highestCard.getRank().ordinal() == CardRank.ACE && lowestCard.getRank().ordinal() == CardRank.TWO) {
                        ranking = new HandRanking(Ranking.STRAIGHT);
                        for(CardRank cr : straightCards) {
                            ranking.addHighCard(cr);
                        }
                        ranking.addHighCard(highestCard.getRank());
                        return ranking;
                    }
                }
            }
        }



        if(cardsCount >= 3) {
            // Look for three of a kind
            ArrayList<CardRank> tripRanks 		= new ArrayList<CardRank>();


            for(int i = 0; i < cardsCount; i++) {
                for(int j = i + 1; j < cardsCount; j++) {
                    for(int k = j + 1; k < cardsCount; k++) {
                        if(pCards[i].getRank().equals(pCards[j].getRank()) &&
                                pCards[i].getRank().equals(pCards[k].getRank()) &&
                                !tripRanks.contains(pCards[i].getRank())) {

                            tripRanks.add(pCards[i].getRank());
                            i = j = k = cardsCount;
                        }
                    }
                }
            }

            if(tripRanks.size() > 0) {
                ranking = new HandRanking(Ranking.TRIPS);
                ranking.addHighCard(tripRanks.get(0));

                int maxCnt = 2;
                for(Card c : cards) {
                    CardRank cr = c.getRank();
                    if(!cr.equals(tripRanks.get(0))) {
                        ranking.addHighCard(cr);
                        maxCnt--;

                        if(maxCnt == 0)		break;
                    }
                }


                return ranking;
            }
        }



        if(cardsCount >= 4) {
            // Look for two pairs
            ArrayList<CardRank> pairRanks 		= new ArrayList<CardRank>();

            for(int i = 0; i < cardsCount; i++) {
                for(int j = i + 1; j < cardsCount; j++) {
                    if(pCards[i].getRank().equals(pCards[j].getRank()) && !pairRanks.contains(pCards[i].getRank())) {
                        pairRanks.add(pCards[i].getRank());
                    }
                }
            }

            if(pairRanks.size() == 2) {
                Collections.sort(pairRanks);

                ranking = new HandRanking(Ranking.TWO_PAIRS);
                ranking.addHighCard(pairRanks.get(1));
                ranking.addHighCard(pairRanks.get(0));
                for(Card c : cards) {
                    CardRank cr = c.getRank();
                    if(!cr.equals(pairRanks.get(0)) && !cr.equals(pairRanks.get(1))) {
                        ranking.addHighCard(cr);
                        break;
                    }
                }
                return ranking;
            }
        }


        // Look for a pair
        for(int i = 0; i < cardsCount; i++) {
            for(int j = i + 1; j < cardsCount; j++) {
                if(pCards[i].getRank().equals(pCards[j].getRank())) {
                    ranking = new HandRanking(Ranking.PAIR);
                    ranking.addHighCard(pCards[i].getRank());

                    int maxCnt = 3;
                    for(Card c : cards) {
                        CardRank cr = c.getRank();
                        if(!cr.equals(pCards[i].getRank())) {
                            ranking.addHighCard(cr);
                            maxCnt--;

                            if(maxCnt == 0)		break;
                        }
                    }


                    return ranking;
                }
            }
        }



        // Highcard
        ranking = new HandRanking(Ranking.HIGH_CARD);
        for(int i = 0; i < cardsCount && i < 5; i++) {
            ranking.addHighCard(cards.get(i).getRank());
        }

        return ranking;


    }

    public enum Ranking {
        HIGH_CARD, PAIR, TWO_PAIR, TRIPS, STRAIGHT, FLUSH, FULL_HOUSE, QUADS, STRAIGHT_FLUSH;
    }
}
