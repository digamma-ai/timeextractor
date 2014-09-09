package com.codeminders.labs.timeextractor.service;

import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

// chain filter
public class Filter {

    public void filter(TemporalExtraction extraction) {
        if (extraction != null && extraction.getTemporalExpression() != null) {
            filterLastCharacter(extraction);
            filterFirstCharacter(extraction);
        }
    }

    // clear last character in case of , . ; or space
    private void filterLastCharacter(TemporalExtraction extraction) {
        char lastCharacter = extraction.getTemporalExpression().charAt(extraction.getTemporalExpression().length() - 1);
        if (lastCharacter == '.' || lastCharacter == ',' || lastCharacter == ' ' || lastCharacter == ';') {
            int to = extraction.getToPosition() - 1;
            String finalString = extraction.getTemporalExpression().substring(0, extraction.getTemporalExpression().length() - 1);
            extraction.setTemporalExpression(finalString);
            extraction.setToPosition(to);

        }
    }

    // clear first character in case of , . ; or space
    private void filterFirstCharacter(TemporalExtraction extraction) {
        char lastCharacter = extraction.getTemporalExpression().charAt(0);
        if (lastCharacter == ' ') {
            int from = extraction.getFromPosition() + 1;
            String finalString = extraction.getTemporalExpression().substring(1, extraction.getTemporalExpression().length());
            extraction.setTemporalExpression(finalString);
            extraction.setFromPosition(from);
        }
    }
}