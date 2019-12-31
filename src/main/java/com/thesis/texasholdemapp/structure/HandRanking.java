package com.thesis.texasholdemapp.structure;

import java.util.*;

public class HandRanking implements Comparable<HandRanking>{
    private HandRanking.Ranking handRanking;
    private ArrayList<CardRanking> highCardRanking = new ArrayList<>();

    public HandRanking(Ranking handRanking) {
        this.handRanking = handRanking;
    }

    public Ranking getHandRanking() {
        return handRanking;
    }

    public void setHandRanking(Ranking handRanking) {
        this.handRanking = handRanking;
    }

    public ArrayList<CardRanking> getHighCardRanking() {
        return highCardRanking;
    }

    public void setCardRankings(ArrayList<CardRanking> cardRankings) {
        this.highCardRanking = cardRankings;
    }

    public void addHighCard(CardRanking cardRanking) {
        highCardRanking.add(cardRanking);
    }

    public static HandRanking evaluate(Card... inputCards) {
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(inputCards));
        Collections.sort(cards);

        ArrayList<Card> clubCards 		= new ArrayList<>();
        ArrayList<Card> diamondCards 	= new ArrayList<>();
        ArrayList<Card> heartCards 		= new ArrayList<>();
        ArrayList<Card> spadeCards 		= new ArrayList<>();

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
        HandRanking handRanking;

        //for 5 and more cards [STRAIGHT_FLUSH]
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

            if(flushCards != null) {
                ArrayList<CardRanking> straightCards = new ArrayList<>();

                CardRanking cardRanking = flushCards.get(0).getRank();
                int straightCount = 1;
                straightCards.add(cardRanking);

                for(int index = 1; index < flushCards.size(); index++) {
                    int compareRanks = cardRanking.compareTo(flushCards.get(index).getRank());

                    if(straightCount == 3) {
                        Card lowestCard = flushCards.get(index);
                        Card highestCard = flushCards.get(0);

                        if(highestCard.getRank().getCardRanking() == CardRanking.ACE &&
                                lowestCard.getRank().getCardRanking() == CardRanking.TWO) {

                            handRanking = new HandRanking(Ranking.STRAIGHT_FLUSH);

                            for(CardRanking currentCardRanking : straightCards) {
                                handRanking.addHighCard(currentCardRanking);
                            }

                            handRanking.addHighCard(lowestCard.getRank());
                            handRanking.addHighCard(highestCard.getRank());
                            return handRanking;
                        }
                    }

                    if(compareRanks != 1) {
                        straightCards = new ArrayList<>();
                        straightCount = 0;
                    }

                    cardRanking = flushCards.get(index).getRank();
                    straightCount++;
                    straightCards.add(cardRanking);

                    if(straightCount == 5) {
                        handRanking = new HandRanking(Ranking.STRAIGHT_FLUSH);
                        for(CardRanking currentCardRanking : straightCards) {
                            handRanking.addHighCard(currentCardRanking);
                        }
                        return handRanking;
                    }
                }
            }
        }

        // for 4 and more cards [QUADS]
        if(inputCardsCount >= 4) {
            HashMap<Integer, Integer> rankCounts = new HashMap<>();

            for (Card inputCard : inputCards) {
                Integer cardRankInt = inputCard.getRank().getCardRanking();
                Integer count = rankCounts.get(cardRankInt);

                if (count == null) {
                    count = 1;
                } else {
                    count += 1;
                }

                rankCounts.put(cardRankInt, count);
            }

            for (Map.Entry<Integer, Integer> entry : rankCounts.entrySet()) {
                if(entry.getValue() == 4) {
                    CardRanking quadRank = new CardRanking(entry.getKey());

                    handRanking = new HandRanking(Ranking.QUADS);
                    handRanking.addHighCard(quadRank);
                    for(Card card : cards) {
                        CardRanking cardRanking = card.getRank();
                        if(!cardRanking.equals(quadRank) && !cardRanking.equals(quadRank)) {
                            handRanking.addHighCard(cardRanking);
                            break;
                        }
                    }

                    return handRanking;
                }
            }
        }

        // for 5 and more cards [FULL_HOUSE], [FLUSH] and [STRAIGHT]
        if(inputCardsCount >= 5) {
            // [FULL_HOUSE]
            ArrayList<CardRanking> pairRanks = new ArrayList<>();
            ArrayList<CardRanking> tripRanks = new ArrayList<>();


            for(int cardIndex = 0; cardIndex < inputCardsCount; cardIndex++) {
                for(int secondCardIndex = cardIndex + 1; secondCardIndex < inputCardsCount; secondCardIndex++) {
                    boolean isTrips = false;
                    for(int thirdCardIndex = secondCardIndex + 1; thirdCardIndex < inputCardsCount; thirdCardIndex++) {
                        if(inputCards[cardIndex].getRank().equals(inputCards[secondCardIndex].getRank()) &&
                                inputCards[cardIndex].getRank().equals(inputCards[thirdCardIndex].getRank()) &&
                                !tripRanks.contains(inputCards[cardIndex].getRank())) {

                            tripRanks.add(inputCards[cardIndex].getRank());
                            isTrips = true;
                        }
                    }


                    if(!isTrips && inputCards[cardIndex].getRank().equals(inputCards[secondCardIndex].getRank()) &&
                            !pairRanks.contains(inputCards[cardIndex].getRank()) &&
                            !tripRanks.contains(inputCards[cardIndex].getRank())) {

                        pairRanks.add(inputCards[cardIndex].getRank());
                    }
                }
            }

            if(pairRanks.size() > 0 && tripRanks.size() > 0) {
                Collections.sort(pairRanks);

                handRanking = new HandRanking(Ranking.FULL_HOUSE);
                handRanking.addHighCard(tripRanks.get(0));
                handRanking.addHighCard(pairRanks.get(0));

                return handRanking;
            }

            // [FLUSH]
            if (clubFlush) {
                flushCards = clubCards;
            } else if (diamondFlush) {
                flushCards = diamondCards;
            } else if (heartFlush) {
                flushCards = heartCards;
            } else if (spadeFlush) {
                flushCards = spadeCards;
            }

            if(flushCards != null) {
                handRanking = new HandRanking(Ranking.FLUSH);

                for (int cardIndex = 0; cardIndex < 5; cardIndex++) {
                    handRanking.addHighCard(flushCards.get(cardIndex).getRank());
                }

                return handRanking;
            }

            // [STRAIGHT]
            for(int cardIndex = 0; cardIndex < inputCardsCount - 3; cardIndex++) {
                CardRanking cardRanking = cards.get(cardIndex).getRank();
                int straightCount = 0;

                ArrayList<CardRanking> straightCards = new ArrayList<>();
                straightCards.add(cardRanking);

                for(int secondCardIndex = cardIndex + 1; secondCardIndex < cards.size(); secondCardIndex++) {
                    CardRanking secondCardRanking = cards.get(secondCardIndex).getRank();
                    int compareTo = cardRanking.compareTo(secondCardRanking);

                    if(compareTo == 0) {
                        continue;
                    } else if(compareTo != 1) {
                        break;
                    }

                    cardRanking = cards.get(secondCardIndex).getRank();
                    straightCards.add(cardRanking);

                    straightCount++;
                    if(straightCount == 4) {
                        handRanking = new HandRanking(Ranking.STRAIGHT);
                        for(CardRanking currentCardRanking : straightCards) {
                            handRanking.addHighCard(currentCardRanking);
                        }
                        return handRanking;
                    }
                }

                if(straightCount == 3 && (cardIndex + 1) == (inputCardsCount - 3)) {
                    Card lowestCard = cards.get(cardIndex + 3);
                    Card highestCard = cards.get(0);

                    if(highestCard.getRank().getCardRanking() == CardRanking.ACE &&
                            lowestCard.getRank().getCardRanking() == CardRanking.TWO) {

                        handRanking = new HandRanking(Ranking.STRAIGHT);
                        for(CardRanking currentCardRanking : straightCards) {
                            handRanking.addHighCard(currentCardRanking);
                        }
                        handRanking.addHighCard(highestCard.getRank());
                        return handRanking;
                    }
                }
            }
        }

        // for 3 and more cards [TRIPS]
        if(inputCardsCount >= 3) {
            ArrayList<CardRanking> tripRanks = new ArrayList<>();

            for(int cardIndex = 0; cardIndex < inputCardsCount; cardIndex++) {
                for(int secondCardIndex = cardIndex + 1; secondCardIndex < inputCardsCount; secondCardIndex++) {
                    for(int thirdCardIndex = secondCardIndex + 1; thirdCardIndex < inputCardsCount; thirdCardIndex++) {
                        if(inputCards[cardIndex].getRank().equals(inputCards[secondCardIndex].getRank()) &&
                                inputCards[cardIndex].getRank().equals(inputCards[thirdCardIndex].getRank()) &&
                                !tripRanks.contains(inputCards[cardIndex].getRank())) {

                            tripRanks.add(inputCards[cardIndex].getRank());
                            cardIndex = secondCardIndex = thirdCardIndex = inputCardsCount;
                        }
                    }
                }
            }

            if(tripRanks.size() > 0) {
                handRanking = new HandRanking(Ranking.TRIPS);
                handRanking.addHighCard(tripRanks.get(0));

                int maxCards = 2;
                for(Card card : cards) {
                    CardRanking cardRanking = card.getRank();
                    if(!cardRanking.equals(tripRanks.get(0))) {
                        handRanking.addHighCard(cardRanking);
                        maxCards--;
                        if(maxCards == 0) { break; }
                    }
                }

                return handRanking;
            }
        }

        // for 4 and more cards [TWO_PAIRS]
        if(inputCardsCount >= 4) {
            ArrayList<CardRanking> pairRanks = new ArrayList<>();

            for(int cardIndex = 0; cardIndex < inputCardsCount; cardIndex++) {
                for(int secondCardIndex = cardIndex + 1; secondCardIndex < inputCardsCount; secondCardIndex++) {
                    if(inputCards[cardIndex].getRank().equals(inputCards[secondCardIndex].getRank()) &&
                            !pairRanks.contains(inputCards[cardIndex].getRank())) {

                        pairRanks.add(inputCards[cardIndex].getRank());
                    }
                }
            }

            if(pairRanks.size() == 2) {
                Collections.sort(pairRanks);

                handRanking = new HandRanking(Ranking.TWO_PAIRS);
                handRanking.addHighCard(pairRanks.get(1));
                handRanking.addHighCard(pairRanks.get(0));
                for(Card card : cards) {
                    CardRanking cardRanking = card.getRank();
                    if(!cardRanking.equals(pairRanks.get(0)) && !cardRanking.equals(pairRanks.get(1))) {
                        handRanking.addHighCard(cardRanking);
                        break;
                    }
                }
                return handRanking;
            }
        }

        // single [PAIR]
        for(int cardIndex = 0; cardIndex < inputCardsCount; cardIndex++) {
            for(int secondCardIndex = cardIndex + 1; secondCardIndex < inputCardsCount; secondCardIndex++) {
                if(inputCards[cardIndex].getRank().equals(inputCards[secondCardIndex].getRank())) {
                    handRanking = new HandRanking(Ranking.PAIR);
                    handRanking.addHighCard(inputCards[cardIndex].getRank());

                    int maxCards = 3;
                    for(Card card : cards) {
                        CardRanking cardRanking = card.getRank();
                        if(!cardRanking.equals(inputCards[cardIndex].getRank())) {
                            handRanking.addHighCard(cardRanking);
                            maxCards--;
                            if(maxCards == 0) { break; }
                        }
                    }

                    return handRanking;
                }
            }
        }

        // [HIGH_CARD]
        handRanking = new HandRanking(Ranking.HIGH_CARD);
        for(int cardIndex = 0; cardIndex < inputCardsCount && cardIndex < 5; cardIndex++) {
            handRanking.addHighCard(cards.get(cardIndex).getRank());
        }

        return handRanking;
    }

    @Override
    public int compareTo(HandRanking inputHandRanking) {
        int compare = this.handRanking.compareTo(inputHandRanking.getHandRanking());
        if(compare == 0) {
            ArrayList<CardRanking> highCards = inputHandRanking.getHighCardRanking();
            int highCardSize = this.highCardRanking.size();
            int highCardArraySize = highCards.size();

            if (highCardSize == highCardArraySize) {
                for(int index = 0; index < highCardSize; index++) {
                    int compareHighCards = this.highCardRanking.get(index).compareTo(highCards.get(index));
                    if(compareHighCards != 0) {
                        return compareHighCards;
                    }
                }
            } else {
                return highCardSize - highCardArraySize;
            }
        }
        return compare;
    }

    //Need to convert to upper cases before
    @Override
    public String toString() {
        String ranking = "";
        StringBuilder cards = new StringBuilder();

        if(handRanking == Ranking.HIGH_CARD)            ranking = "Wysoka karta";
        else if(handRanking == Ranking.PAIR)            ranking = "Para";
        else if(handRanking == Ranking.TWO_PAIRS)       ranking = "Dwie pary";
        else if(handRanking == Ranking.TRIPS)           ranking = "Trójka";
        else if(handRanking == Ranking.STRAIGHT)        ranking = "Strit";
        else if(handRanking == Ranking.FLUSH)           ranking = "Kolor";
        else if(handRanking == Ranking.FULL_HOUSE)      ranking = "Full";
        else if(handRanking == Ranking.QUADS)           ranking = "Kareta";
        else if (handRanking == Ranking.STRAIGHT_FLUSH) ranking = "Poker";

        if(handRanking == Ranking.HIGH_CARD || handRanking == Ranking.STRAIGHT ||
           handRanking == Ranking.FLUSH || handRanking == Ranking.STRAIGHT_FLUSH) {
            for(CardRanking cardRanking : highCardRanking) {
                if(cards.length() > 0)
                    cards.append(", ");

                cards.append(cardRanking.toString());
            }
        } else if(handRanking == Ranking.PAIR) {
            cards.append(highCardRanking.get(0).toString());

            StringBuilder strHighSingleCards = new StringBuilder();
            for(int index = 1; index < highCardRanking.size(); index++) {
                if(strHighSingleCards.length() > 0)
                    strHighSingleCards.append(",");

                strHighSingleCards.append(highCardRanking.get(index));
            }

            if(strHighSingleCards.length() > 0)
                cards.append(" - ").append(strHighSingleCards);

        } else if(handRanking == Ranking.TWO_PAIRS) {
            cards.append(highCardRanking.get(0).toString()).append(" i ").append(highCardRanking.get(1).toString());

            if(highCardRanking.size() > 2)		cards.append(" - ").append(highCardRanking.get(2));

        }
        else if(handRanking == Ranking.TRIPS || handRanking == Ranking.QUADS) {
            cards.append(highCardRanking.get(0).toString());

            if(handRanking == Ranking.QUADS) {
                if(highCardRanking.size() > 1)		cards.append(" - ").append(highCardRanking.get(1));
            }
            else if(handRanking == Ranking.TRIPS) {
                if(highCardRanking.size() == 2) {
                    cards.append(" - ").append(highCardRanking.get(1));
                }
                else if(highCardRanking.size() > 2) {
                    cards.append(" - ").append(highCardRanking.get(1)).append(", ").append(highCardRanking.get(2));
                }
            }
        }
        else if(handRanking == Ranking.FULL_HOUSE) {
            if(highCardRanking.size() == 2) {
                cards.append(highCardRanking.get(0).toString()).append(" full ").append(highCardRanking.get(1).toString());
            }
        }

        if(cards.length() != 0)
            ranking = String.format("%s (%s)", ranking, cards);

        return ranking;
    }

    public enum Ranking {
        HIGH_CARD, PAIR, TWO_PAIRS, TRIPS, STRAIGHT, FLUSH, FULL_HOUSE, QUADS, STRAIGHT_FLUSH;
    }
}
