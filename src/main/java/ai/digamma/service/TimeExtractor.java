package ai.digamma.service;

import ai.digamma.entities.Settings;
import ai.digamma.entities.TemporalExtraction;

import java.util.TreeSet;

public class TimeExtractor {

    public static TreeSet<TemporalExtraction> extract(String text, Settings settings) {
        TemporalExtractionService service = new TemporalExtractionService();
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText(text, settings);
        return extracted;
    }
}
