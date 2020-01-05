package com.thesis.texasholdemapp.builder;

import java.util.ArrayList;

public class OutputBuilder {
    public boolean isNotEmpty(String[] array) {
        for (String value : array) {
            if (value.equals("")) {
                return false;
            }
        }
        return true;
    }

    public String handFromArray(String[] array) {
        StringBuilder hand = new StringBuilder();
        for (String card : array) {
            hand.append(card);
        }
        return hand.toString();
    }

    public String buildBoard(String[] flop, String turn, String river) {
        StringBuilder board = new StringBuilder();

        if (isNotEmpty(flop)) {
            for (String card : flop) {
                board.append(card);
            }
            if (!turn.equals("")) {
                board.append(turn);
                if (!river.equals("")) board.append(river);
            }
        }

        return board.toString();
    }

    public ArrayList<String> buildHandArray(String[] array) {
        ArrayList<String> handArray = new ArrayList<>();
        for(String card : array) {
            if(card.equals("Ad")) {
                handArray.add("Aced");
            } else {
                handArray.add(card);
            }
        }
        return handArray;
    }
}
