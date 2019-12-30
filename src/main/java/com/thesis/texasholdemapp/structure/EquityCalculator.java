package com.thesis.texasholdemapp.structure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;

public class EquityCalculator {
    private ArrayList<Card> boardCards;
    private ArrayList<Hand> hands;

    private ArrayList<HandRanking> rankings;
    private ArrayList<HandEquity> equities;

    private long seed;
    private long maxIterations = 200000;

    private boolean isMonteCarlo = true;

    public EquityCalculator() {
        this.reset();
    }

    public EquityCalculator reset() {
        this.boardCards     = new ArrayList<>();
        this.hands          = new ArrayList<>();

        this.rankings       = new ArrayList<>();
        this.equities       = new ArrayList<>();

        seed = System.currentTimeMillis();

        return this;
    }

    public ArrayList<Card> getBoardCards() {
        return boardCards;
    }

    public void setBoardCards(ArrayList<Card> boardCards) {
        this.boardCards = boardCards;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public void setHands(ArrayList<Hand> hands) {
        this.hands = hands;
    }

    public ArrayList<HandRanking> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<HandRanking> rankings) {
        this.rankings = rankings;
    }

    public ArrayList<HandEquity> getEquities() {
        return equities;
    }

    public void setEquities(ArrayList<HandEquity> equities) {
        this.equities = equities;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(long maxIterations) {
        this.maxIterations = maxIterations;
    }

    public boolean isBoardEmpty() {
        return boardCards.isEmpty();
    }

    public HandRanking getHandRanking(int handIndex) {
        return rankings.get(handIndex);
    }

    public HandEquity getHandEquity(int handIndex) {
        return equities.get(handIndex);
    }

    public EquityCalculator addHand(Hand hand) {
        hands.add(hand);
        return this;
    }

    public void verifyDuplicates() throws Exception {
        for(int handIndex = 0; handIndex < hands.size(); handIndex++) {
            Card card1 = hands.get(handIndex).getCard(0);
            Card card2 = hands.get(handIndex).getCard(1);

            if (card1.equals(card2)) {
                throw new Exception("Duplikacja kart");
            }

            for(int secondHandIndex = handIndex + 1; secondHandIndex < hands.size(); secondHandIndex++) {
                Card card3 = hands.get(secondHandIndex).getCard(0);
                Card card4 = hands.get(secondHandIndex).getCard(1);

                if (card1.equals(card3) || card1.equals(card4) || card2.equals(card3) || card2.equals(card4)) {
                    throw new Exception("Duplicattt");
                }
            }

            for(Card boardCard : boardCards) {
                if (card1.equals(boardCard) || card2.equals(boardCard)) {
                    throw new Exception("Duplikate na boardziate");
                }
            }
        }
    }

    public EquityCalculator setBoardCards(Card... cards) throws Exception {
        boardCards.clear();

        if (cards.length != 3 && cards.length != 4 && cards.length != 5) {
            throw new Exception("Bord ma miec 3, 4 albo 5 kart");
        }

        boardCards.addAll(Arrays.asList(cards));

        return this;
    }

    public EquityCalculator setBoardFromString(String stringCards) throws Exception {
        boardCards.clear();

        stringCards.replaceAll("\\s", "").replaceAll(",","");
        for(int index = 0; index + 1 < stringCards.length(); index += 2) {
            boardCards.add(Card.fromString(stringCards.substring(index, index + 2)));
        }

        if (boardCards.size() != 3 && boardCards.size() != 4 && boardCards.size() != 5) {
            throw new Exception("Stol ma miec 3,4 albo 5 kart, mowilem");
        }

        return this;
    }


    //testing method
    public String printBoardCards() {
        if(isBoardEmpty()) {
            System.out.println("Board jest pusty");
            return "Board jest pusty";
        } else {
            StringBuilder boardString = new StringBuilder();

            for(Card card : boardCards) {
                if(boardString.length() > 0) {
                    boardString.append(" - ");
                }
                boardString.append(String.format("%s", card));
            }
            System.out.println(String.format("Bordzik: %s", boardString.toString()));
            return String.format("Bordzik: %s", boardString.toString());
        }
    }

    public void calculateEquity() throws Exception {
        verifyDuplicates();
        rankings.clear();
        equities.clear();

        ArrayList<Card> remainingCards = new ArrayList<>();
        for(Card.CardSuit suit : Card.CardSuit.values()) {
            for(CardRanking ranking : CardRanking.values()) {
                Card newCard = new Card(ranking, suit);
                boolean isUsed = false;

                for(Card card : boardCards) {
                    if(card.equals(newCard)) {
                        isUsed = true;
                        break;
                    }
                }

                if (isUsed) continue;

                for (Hand hand : hands) {
                    if(hand.getCard(0).equals(newCard) || hand.getCard(1).equals(newCard)) {
                        isUsed = true;
                        break;
                    }
                }

                if (isUsed) continue;

                remainingCards.add(newCard);
            }
        }

        int boardSize = boardCards.size();
        int handsCount = hands.size();
        int remainingCount = remainingCards.size();

        HandRanking highestRanking;
        int highestRankingIndex;

        LinkedHashSet<Integer> splitIndexes = new LinkedHashSet<>();

        //[PREFLOP]
        if(boardSize == 0) {
            for (Hand currentHand : hands) {
                HandEquity handEquity = new HandEquity();
                equities.add(handEquity);

                HandRanking handRanking = HandRanking.evaluate(currentHand.getCard(0), currentHand.getCard(1));
                rankings.add(handRanking);
            }

            boardCards.add(null);
            boardCards.add(null);
            boardCards.add(null);
            boardCards.add(null);
            boardCards.add(null);

            Random random = new Random(seed);
            for(long iterations = maxIterations; iterations > 0; iterations--) {
                int card1Index = random.nextInt(remainingCount);
                int card2Index, card3Index, card4Index, card5Index;

                do {
                    card2Index = random.nextInt(remainingCount);
                } while(card2Index == card1Index);

                do {
                    card3Index = random.nextInt(remainingCount);
                } while (card3Index == card2Index || card3Index == card1Index);

                do {
                    card4Index = random.nextInt(remainingCount);
                } while (card4Index == card3Index || card4Index == card2Index || card4Index == card1Index);

                do {
                    card5Index = random.nextInt(remainingCount);
                } while (card5Index == card4Index || card5Index == card3Index || card5Index == card2Index || card5Index == card1Index);

                boardCards.set(0, remainingCards.get(card1Index));
                boardCards.set(1, remainingCards.get(card2Index));
                boardCards.set(2, remainingCards.get(card3Index));
                boardCards.set(3, remainingCards.get(card4Index));
                boardCards.set(4, remainingCards.get(card5Index));

                highestRanking = null;
                highestRankingIndex = -1;
                splitIndexes.clear();

                for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                    Hand hand = hands.get(handIndex);
                    HandRanking handRanking = HandRanking.evaluate(
                            hand.getCard(0),
                            hand.getCard(1),
                            boardCards.get(0),
                            boardCards.get(1),
                            boardCards.get(2),
                            boardCards.get(3),
                            boardCards.get(4));

                    if(highestRanking == null || handRanking.compareTo(highestRanking) > 0) {
                        highestRankingIndex = handIndex;
                        highestRanking = handRanking;
                    } else if(handRanking.compareTo(highestRanking) == 0) {
                        splitIndexes.add(highestRankingIndex);
                        splitIndexes.add(handIndex);
                    }
                }

                for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                    if (splitIndexes.isEmpty()) {
                        equities.get(handIndex).addPossibleHand(handIndex == highestRankingIndex);
                    } else {
                        for (Integer index : splitIndexes) {
                            equities.get(index).addSplitHands(true);
                        }
                        splitIndexes.clear();
                        handIndex = handsCount + 1;
                    }
                }
            }

            boardCards.clear();

        //[FLOP]
        } else if(boardSize == 3) {
            for (Hand currentHand : hands) {
                HandEquity handEquity = new HandEquity();
                equities.add(handEquity);

                HandRanking handRanking = HandRanking.evaluate(
                        currentHand.getCard(0),
                        currentHand.getCard(1),
                        boardCards.get(0),
                        boardCards.get(1),
                        boardCards.get(2));
                rankings.add(handRanking);
            }

            boardCards.add(null);
            boardCards.add(null);

            if(isMonteCarlo) {
                //Monte Carlo
                Random random = new Random(seed);
                for(long iterations = maxIterations; iterations > 0; iterations--) {
                    int card3Index = random.nextInt(remainingCount);
                    int card4Index;

                    do {
                        card4Index = random.nextInt(remainingCount);
                    } while(card4Index == card3Index);

                    boardCards.set(3, remainingCards.get(card3Index));
                    boardCards.set(4, remainingCards.get(card4Index));

                    highestRanking = null;
                    highestRankingIndex = -1;
                    splitIndexes.clear();

                    for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                        Hand hand = hands.get(handIndex);
                        HandRanking handRanking = HandRanking.evaluate(
                                hand.getCard(0),
                                hand.getCard(1),
                                boardCards.get(0),
                                boardCards.get(1),
                                boardCards.get(2),
                                boardCards.get(3),
                                boardCards.get(4));

                        if(highestRanking == null || handRanking.compareTo(highestRanking) > 0) {
                            highestRankingIndex = handIndex;
                            highestRanking = handRanking;
                        } else if(handRanking.compareTo(highestRanking) == 0) {
                            splitIndexes.add(highestRankingIndex);
                            splitIndexes.add(handIndex);
                        }
                    }

                    for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                        if (splitIndexes.isEmpty()) {
                            equities.get(handIndex).addPossibleHand(handIndex == highestRankingIndex);
                        } else {
                            for (Integer index : splitIndexes) {
                                equities.get(index).addSplitHands(true);
                            }
                            splitIndexes.clear();
                            handIndex = handsCount + 1;
                        }
                    }
                }
            } else {
                //Normal method
                for(int card1Index = 0; card1Index < remainingCount; card1Index++) {
                    Card card1 = remainingCards.get(card1Index);

                    for(int card2Index = card1Index + 1; card2Index < remainingCount; card2Index++) {
                        Card card2 = remainingCards.get(card2Index);

                        boardCards.set(3, card1);
                        boardCards.set(4, card2);

                        highestRanking = null;
                        highestRankingIndex = -1;
                        splitIndexes.clear();

                        for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                            Hand hand = hands.get(handIndex);
                            HandRanking handRanking = HandRanking.evaluate(
                                    hand.getCard(0),
                                    hand.getCard(1),
                                    boardCards.get(0),
                                    boardCards.get(1),
                                    boardCards.get(2),
                                    boardCards.get(3),
                                    boardCards.get(4));

                            if(highestRanking == null || handRanking.compareTo(highestRanking) > 0) {
                                highestRankingIndex = handIndex;
                                highestRanking = handRanking;
                            } else if(handRanking.compareTo(highestRanking) == 0) {
                                splitIndexes.add(highestRankingIndex);
                                splitIndexes.add(handIndex);
                            }
                        }

                        for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                            if (splitIndexes.isEmpty()) {
                                equities.get(handIndex).addPossibleHand(handIndex == highestRankingIndex);
                            } else {
                                for (Integer index : splitIndexes) {
                                    equities.get(index).addSplitHands(true);
                                }
                                splitIndexes.clear();
                                handIndex = handsCount + 1;
                            }
                        }
                    }
                }
            }

            boardCards.remove(4);
            boardCards.remove(3);

        //[TURN]
        } else if(boardSize == 4) {
            for (Hand currentHand : hands) {
                HandEquity handEquity = new HandEquity();
                equities.add(handEquity);

                HandRanking handRanking = HandRanking.evaluate(
                        currentHand.getCard(0),
                        currentHand.getCard(1),
                        boardCards.get(0),
                        boardCards.get(1),
                        boardCards.get(2),
                        boardCards.get(3));
                rankings.add(handRanking);
            }

            boardCards.add(null);

            if (isMonteCarlo) {
                Random random = new Random(seed);
                for(long iterations = maxIterations; iterations > 0; iterations--) {
                    int card4Index = random.nextInt(remainingCount);

                    boardCards.set(4, remainingCards.get(card4Index));

                    highestRanking = null;
                    highestRankingIndex = -1;
                    splitIndexes.clear();

                    for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                        Hand hand = hands.get(handIndex);
                        HandRanking handRanking = HandRanking.evaluate(
                                hand.getCard(0),
                                hand.getCard(1),
                                boardCards.get(0),
                                boardCards.get(1),
                                boardCards.get(2),
                                boardCards.get(3),
                                boardCards.get(4));

                        if(highestRanking == null || handRanking.compareTo(highestRanking) > 0) {
                            highestRankingIndex = handIndex;
                            highestRanking = handRanking;
                        } else if(handRanking.compareTo(highestRanking) == 0) {
                            splitIndexes.add(highestRankingIndex);
                            splitIndexes.add(handIndex);
                        }
                    }

                    for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                        if (splitIndexes.isEmpty()) {
                            equities.get(handIndex).addPossibleHand(handIndex == highestRankingIndex);
                        } else {
                            for (Integer index : splitIndexes) {
                                equities.get(index).addSplitHands(true);
                            }
                            splitIndexes.clear();
                            handIndex = handsCount + 1;
                        }
                    }
                }
            } else {
                for(Card card : remainingCards) {
                    boardCards.set(4, card);

                    highestRanking = null;
                    highestRankingIndex = -1;
                    splitIndexes.clear();

                    for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                        Hand hand = hands.get(handIndex);
                        HandRanking handRanking = HandRanking.evaluate(
                                hand.getCard(0),
                                hand.getCard(1),
                                boardCards.get(0),
                                boardCards.get(1),
                                boardCards.get(2),
                                boardCards.get(3),
                                boardCards.get(4));

                        if(highestRanking == null || handRanking.compareTo(highestRanking) > 0) {
                            highestRankingIndex = handIndex;
                            highestRanking = handRanking;
                        } else if(handRanking.compareTo(highestRanking) == 0) {
                            splitIndexes.add(highestRankingIndex);
                            splitIndexes.add(handIndex);
                        }
                    }

                    for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                        if (splitIndexes.isEmpty()) {
                            equities.get(handIndex).addPossibleHand(handIndex == highestRankingIndex);
                        } else {
                            for (Integer index : splitIndexes) {
                                equities.get(index).addSplitHands(true);
                            }
                            splitIndexes.clear();
                            handIndex = handsCount + 1;
                        }
                    }
                }
            }

            boardCards.remove(4);

        //[RIVER]
        } else if(boardSize == 5) {
            highestRanking = null;
            highestRankingIndex = -1;
            splitIndexes.clear();

            for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                Hand hand = hands.get(handIndex);
                HandRanking handRanking = HandRanking.evaluate(
                        hand.getCard(0),
                        hand.getCard(1),
                        boardCards.get(0),
                        boardCards.get(1),
                        boardCards.get(2),
                        boardCards.get(3),
                        boardCards.get(4));

                if(highestRanking == null || handRanking.compareTo(highestRanking) > 0) {
                    highestRankingIndex = handIndex;
                    highestRanking = handRanking;
                } else if(handRanking.compareTo(highestRanking) == 0) {
                    splitIndexes.add(highestRankingIndex);
                    splitIndexes.add(handIndex);
                }

                rankings.add(handRanking);
            }


            for(int handIndex = 0; handIndex < handsCount; handIndex++) {
                HandEquity handEquity = new HandEquity();

                if (splitIndexes.isEmpty()) {
                    equities.get(handIndex).addPossibleHand(handIndex == highestRankingIndex);
                } else {
                    for (Integer index : splitIndexes) {
                        equities.get(index).addSplitHands(true);
                    }
                    splitIndexes.clear();
                    handIndex = handsCount + 1;
                }

                equities.add(handEquity);
            }
        }
    }
}
