package com.codeminders.labs.timeextractor.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.business.CsvReader;
import com.codeminders.labs.timeextractor.entities.Tip;
import com.codeminders.labs.timeextractor.service.TemporalExtractionService;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class ConfidenceGenerator {
    private TemporalExtractionService service = new TemporalExtractionService();

    public double getConfidenceFromFile(String file) throws IOException {
        CsvReader reader = new CsvReader();
        List<Tip> texts = reader.getTipsFromFile(file, ",");
        double predictedTrue = 0;
        double total = 0;
        for (Tip tip : texts) {
            String patternString = "<text>(.*?)</text>";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(tip.getTipText());
            HashSet<String> annotated = new HashSet<String>();
            while (matcher.find()) {
                String result = matcher.group().replace("<text>", "").replace("</text>", "").replace("?", "-").replace("–", "-");
                annotated.add(result.trim());
            }

            String text = tip.getTipText().replace("<text>", "").replace("</text>", "").replace("?", "-").replace("–", "-").trim();
            List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(text, null);
            if (annotated.size() == predicted.size()) {
                total++;
                predictedTrue++;
            } else {
                total++;
            }
        }

        return predictedTrue / total;
    }

    public static void main(String[] args) throws IOException {
        String file = "C:/Users/User/git/timeextractor/nlpTravelPlannerReviewAnalyzer/confidence/DayOfWeekRule1.csv";
        ConfidenceGenerator generator = new ConfidenceGenerator();
        double confidence = generator.getConfidenceFromFile(file);
        System.out.println(confidence);
    }
}
