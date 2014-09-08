package com.codeminders.labs.timeextractor.service;

import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

// chain filter
public class Filter {

    public void filter(TemporalExtraction extraction) {
        if (extraction != null && extraction.getTemporalExpression() != null) {
            filterLastCharacter(extraction);
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
}
