package com.codeminders.labs.timeextractor.utils;

import java.util.Properties;

import com.codeminders.labs.timeextractor.service.TemporalExtractionService;
import com.codeminders.labs.timeextractor.service.TemporalAnnotator;

import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.PTBTokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;

public class PipelineGenerator {

    private static final String BASE_RULES = "/base.rules.txt";
    private static final String HOLIDAY_RULES = "/holidays.txt";
    private static final String DURATION_RULES = "/duration.txt";
    private static final String DEFS = "/defs.sutime.txt";

    private static AnnotationPipeline pipeline = new AnnotationPipeline();
    private static Properties props = new Properties();

    static {

        pipeline.addAnnotator(new PTBTokenizerAnnotator(false));
        pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
        Properties props = getProperties();
        pipeline.addAnnotator(new TemporalAnnotator("sutime", props));
    }

    public static AnnotationPipeline getPipeline() {
        return pipeline;
    }

    private static Properties getProperties() {
        String baseRules = TemporalExtractionService.class.getResource(BASE_RULES).getPath();
        String defs = TemporalExtractionService.class.getResource(DEFS).getPath();
        String holidays = TemporalExtractionService.class.getResource(HOLIDAY_RULES).getPath();
        String durationRules = TemporalExtractionService.class.getResource(DURATION_RULES).getPath();
        String allRules = StringUnion.sutimeMainRules(defs, baseRules, durationRules, holidays);
        props.setProperty("sutime.rules", allRules);
        props.setProperty("annotators", "lemma,  tokenize");
        props.setProperty("annotators", "lemma,  tokenize");

        return props;
    }

}
