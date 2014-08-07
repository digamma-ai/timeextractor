package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.codeminders.labs.timeextractor.entities.AnnotationInterval;
import com.codeminders.labs.timeextractor.entities.BaseTexts;
import com.codeminders.labs.timeextractor.utilities.StringUnion;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.PTBTokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.time.TimeAnnotator;
import edu.stanford.nlp.util.CoreMap;

/* Service class to extract temporal information from texts */

public class SUTimeService {

    public static String MAIN_RULES = "/additional.rules.txt";
    public static String SUTIME1_RULES = "/english.sutime.txt";
    public static String SUTIME2_RULES = "/english.sutime2.txt";
    public static String DEFS = "/defs.sutime.txt";
    public static String TEST = "/text.txt";
    /* Method returns annotated temporal expressions */

    public List<CoreMap> extractDatesAndTimeFromText(String text, String date) {

        // clear text
        text = text.replace("–", "-");

        if (date == null) {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
            date = localDate.toString(fmt);
            System.out.println(date);
        }
        // annotators & rules included

        AnnotationPipeline pipeline = new AnnotationPipeline();
        pipeline.addAnnotator(new PTBTokenizerAnnotator(false));
        pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
        pipeline.addAnnotator(new POSTaggerAnnotator(false));

        String customRules = SUTimeService.class.getResource(MAIN_RULES).getPath();
        String sutimeRules1 = SUTimeService.class.getResource(SUTIME1_RULES).getPath();
        String sutimeRules2 = SUTimeService.class.getResource(SUTIME2_RULES).getPath();
        String defs = SUTimeService.class.getResource(DEFS).getPath();

        // defs, customRules,sutimeRules1,sutimeRules2
        // customRules

        String allRules = StringUnion
                .sutimeMainRules(defs, customRules,sutimeRules1,sutimeRules2);
        Properties props = new Properties();
        props.setProperty("sutime.markTimeRanges", "true");
        props.setProperty("sutime.includeRange", "true");
        props.setProperty("restrictToTimex3", "false");
        props.setProperty("sutime.rules", allRules);

        props.setProperty("annotators", "tokenize,ssplit,pos,sutime");
        pipeline.addAnnotator(new TimeAnnotator("sutime", props));
        List<CoreMap> allAnnotations = new ArrayList<CoreMap>();

        Annotation annotation = new Annotation(text);
        annotation.set(CoreAnnotations.DocDateAnnotation.class, date);
        pipeline.annotate(annotation);
        List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);
        allAnnotations.addAll(timexAnnsAll);
        return allAnnotations;
    }

    public List<AnnotationInterval> getAllAnotations(List<CoreMap> annotations) {
        List<AnnotationInterval> intervals = new ArrayList<AnnotationInterval>();
        for (CoreMap cm : annotations) {
            List<CoreLabel> tokens = cm.get(CoreAnnotations.TokensAnnotation.class);
            AnnotationInterval interval = new AnnotationInterval();
            int from = (tokens.get(0)).get(CoreAnnotations.CharacterOffsetBeginAnnotation.class);
            int to = tokens.get(tokens.size() - 1).get(
                    CoreAnnotations.CharacterOffsetEndAnnotation.class);
            interval.setFrom(from);
            interval.setTo(to);
            interval.setValue(cm.toString());
            intervals.add(interval);
        }
        return intervals;
    }

    public Map<String, List<CoreMap>> extractDatesAndTimeFromText(List<BaseTexts> baseTexts) {
        Map<String, List<CoreMap>> map = new HashMap<String, List<CoreMap>>();
        for (BaseTexts baseText : baseTexts) {
            List<CoreMap> resuts = extractDatesAndTimeFromText(baseText.getText(),
                    baseText.getDate());
            map.put(baseText.getId(), resuts);
        }
        return map;
    }

    public Map<String, List<AnnotationInterval>> getAllAnnotations(
            Map<String, List<CoreMap>> extractedTemporal) {
        Map<String, List<AnnotationInterval>> result = new HashMap<String, List<AnnotationInterval>>();
        for (String key : extractedTemporal.keySet()) {
            List<CoreMap> annotated = extractedTemporal.get(key);
            List<AnnotationInterval> annotations = getAllAnotations(annotated);
            result.put(key, annotations);
        }
        return result;
    }
}
