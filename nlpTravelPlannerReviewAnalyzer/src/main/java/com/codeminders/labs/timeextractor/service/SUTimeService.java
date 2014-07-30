package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.codeminders.labs.timeextractor.utilities.StringUnion;

import edu.stanford.nlp.ling.CoreAnnotations;
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

    /* Method returns annotated temporal expressions */

    public List<CoreMap> extractDatesAndTimeFromText(String[] text, String date) {

        AnnotationPipeline pipeline = new AnnotationPipeline();
        pipeline.addAnnotator(new PTBTokenizerAnnotator(false));
        pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
        pipeline.addAnnotator(new POSTaggerAnnotator(false));

        String customRules = SUTimeService.class.getResource(MAIN_RULES).getPath();
        String sutimeRules1 = SUTimeService.class.getResource(SUTIME1_RULES).getPath();
        String sutimeRules2 = SUTimeService.class.getResource(SUTIME2_RULES).getPath();
        String defs = SUTimeService.class.getResource(DEFS).getPath();

        // defs ,customRules,sutimeRules1,sutimeRules2
        // customRules

        String allRules = StringUnion
                .sutimeMainRules(defs, customRules, sutimeRules1, sutimeRules2);

        Properties props = new Properties();
        props.setProperty("sutime.markTimeRanges", "true");
        props.setProperty("sutime.includeRange", "true");
        props.setProperty("restrictToTimex3", "false");
        props.setProperty("sutime.rules", allRules);

        props.setProperty("annotators", "tokenize,ssplit,pos,sutime");
        pipeline.addAnnotator(new TimeAnnotator("sutime", props));
        List<CoreMap> allAnnotations = new ArrayList<CoreMap>();
        for (String line : text) {
            Annotation annotation = new Annotation(line);
            annotation.set(CoreAnnotations.DocDateAnnotation.class, date);
            pipeline.annotate(annotation);
            List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);
            allAnnotations.addAll(timexAnnsAll);
        }

        return allAnnotations;
    }

}
