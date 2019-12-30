package com.thesis.texasholdemapp.structure;

public class HandEquity {
    private int possibleHandsCount;
    private int bestHandsCount;
    private int splitHandsCount;

    public int getPossibleHandsCount() {
        return possibleHandsCount;
    }

    public void setPossibleHandsCount(int possibleHandsCount) {
        this.possibleHandsCount = possibleHandsCount;
    }

    public int getBestHandsCount() {
        return bestHandsCount;
    }

    public void setBestHandsCount(int bestHandsCount) {
        this.bestHandsCount = bestHandsCount;
    }

    public void addPossibleHand(boolean isBestHand) {
        possibleHandsCount++;

        if (isBestHand) {
            bestHandsCount++;
        }
    }

    public void addSplitHands(boolean isSplit) {
        possibleHandsCount++;

        if (isSplit) {
            splitHandsCount++;
        }
    }

    public double getEquity() {
        if (possibleHandsCount == 0) {
            return 0;
        }

        return bestHandsCount * 100.0 / possibleHandsCount;
    }

    public double getSplitEquity() {
        if (possibleHandsCount == 0) {
            return 0;
        }

        return splitHandsCount * 100.0 / possibleHandsCount;
    }
}
