package ai.digamma.service;

import ai.digamma.business.CsvReader;
import ai.digamma.business.CsvWriter;
import ai.digamma.entities.Settings;
import ai.digamma.entities.TemporalExtraction;
import ai.digamma.entities.Tip;
import ai.digamma.exceptions.ExceptionMessages;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DateTimeExtractor {

    public static TreeSet<TemporalExtraction> extract(String text, Settings settings){
        TemporalExtractionService service = new TemporalExtractionService();
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText(text, settings);
        return extracted;
    }

    public static TreeSet<TemporalExtraction> extract(String text){
        Settings settings = new Settings();
        TemporalExtractionService service = new TemporalExtractionService();
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText(text, settings);
        return extracted;
    }

    public static String extractJSON(String text){
        Settings settings = new Settings();
        TemporalExtractionService service = new TemporalExtractionService();
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText(text, settings);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(extracted);
        } catch (JsonProcessingException e) {
            json = e.getMessage();
            e.printStackTrace();
        }
        return json;
    }

    public static String extractJSON(String text, Settings settings){
        TemporalExtractionService service = new TemporalExtractionService();
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText(text, settings);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(extracted);
        } catch (JsonProcessingException e) {
            json = e.getMessage();
            e.printStackTrace();
        }
        return json;
    }

    public static TreeSet<TemporalExtraction> extractFromCsv(String csvPath, String separator, String outputPath, Settings settings)
            throws Exception {
        CsvReader reader = new CsvReader();
        CsvWriter writer = new CsvWriter();
        List<Tip> tips;
        TemporalExtractionService service = new TemporalExtractionService();
        TreeSet<TemporalExtraction> extracted = new TreeSet<>();
        try {
            tips = reader.getTipsFromFile(csvPath, separator);
        }
        catch(IOException e){ throw new Exception(ExceptionMessages.INPUT_FILE_NOT_FOUND);}
        for (Tip tip : tips) {
            String text = tip.getTipText();
            TreeSet<TemporalExtraction> curr_extracted = service.extractDatesAndTimeFromText(text,settings);
            for(TemporalExtraction temp : curr_extracted) {
                extracted.add(temp);
                writer.writeToFile(outputPath, extracted.toString());
            }
        }
        return extracted;
    }

    public static void main(String[] args) {
        String text = "from summer to winter";
        String result = DateTimeExtractor.extractJSON(text);
        System.out.println(result);
    }
}