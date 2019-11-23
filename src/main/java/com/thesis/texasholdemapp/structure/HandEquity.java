package com.thesis.texasholdemapp.structure;

public class HandEquity {
    private int possibleHandsCount;
    private int bestHandsCount;

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

    public int getEquity() {
        if (possibleHandsCount == 0) {
            return 0;
        }

        return (int)Math.round(bestHandsCount * 100.0 / possibleHandsCount);
    }
}
