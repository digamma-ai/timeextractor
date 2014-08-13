package com.codeminders.labs.timeextractor.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.entities.Confidence;

import edu.stanford.nlp.util.CoreMap;

public class ConfidenceLevelService {

    private static String NUMERIC = "/d";
    private static String MONTH_OF_YEAR = "(january|february|march|april|may|june|july|august|september|october|november|december)";
    private static String MONTH_OF_YEAR_EASY = "/d";
    private static String TIME_OF_DAY = "/d";
    private static String SEASON = "/d";
    private static String DAY_OF_WEEK = "/d";
    private static String DAY_OF_WEEK_EASY = "/d";
    private static String SERIAL = "/d";
    private static String NUMBER = "/d";
    private static String TIME_ZONE = "/d";

    public List<CoreMap> getConfidenceLevel(List<CoreMap> annotations) {
        for (CoreMap annotation : annotations) {
            setConfLevelOfAnotation(annotation);
        }
        return annotations;
    }

    private CoreMap setConfLevelOfAnotation(CoreMap annotation) {
        System.out.println(annotation);
        annotation.set(Confidence.class, new Long(1234));
        return annotation;
    }

    public static void main(String[] args) {
        String text1 = "january 2013";
        String[] words = text1.split("\\s+");
        String text = null;

        for (String word : words) {
            if (MONTH_OF_YEAR.toLowerCase().contains(word.toLowerCase())) {
                text = text1.replaceAll(word, MONTH_OF_YEAR);
            }
            if (MONTH_OF_YEAR_EASY.toLowerCase().contains(word.toLowerCase())) {

            }
            if(word.contains("[0-9]")){
                text.replaceAll("[0-9]", "/d");
            }
        }
        System.out.println(text);
        Pattern p = Pattern.compile(text);
        Matcher m = p.matcher(text1);

        System.out.println(m.find());
    }

}
