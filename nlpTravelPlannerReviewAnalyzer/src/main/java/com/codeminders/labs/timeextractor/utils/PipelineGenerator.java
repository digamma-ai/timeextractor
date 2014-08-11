package com.codeminders.labs.timeextractor.utils;

import java.util.Properties;

import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.utilities.StringUnion;

import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.PTBTokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.time.TimeAnnotator;

public class PipelineGenerator {

    private static final String MAIN_RULES = "/additional.rules.txt";
    private static final String SUTIME1_RULES = "/english.sutime.txt";
    private static final String SUTIME2_RULES = "/english.sutime2.txt";
    private static final String DEFS = "/defs.sutime.txt";

    private static AnnotationPipeline pipeline = new AnnotationPipeline();

    static {
        pipeline.addAnnotator(new PTBTokenizerAnnotator(false));
        pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
        pipeline.addAnnotator(new POSTaggerAnnotator(false));
        Properties props = getProperties();
        pipeline.addAnnotator(new TimeAnnotator("sutime", props));
    }
    
    public static AnnotationPipeline getPipeline() {
        return pipeline;
    }

    private static Properties getProperties() {

        String customRules = SUTimeService.class.getResource(MAIN_RULES).getPath();
        String sutimeRules1 = SUTimeService.class.getResource(SUTIME1_RULES).getPath();
        String sutimeRules2 = SUTimeService.class.getResource(SUTIME2_RULES).getPath();
        String defs = SUTimeService.class.getResource(DEFS).getPath();

        // defs, customRules,sutimeRules1,sutimeRules2
        // customRules

        String allRules = StringUnion.sutimeMainRules(defs, customRules,sutimeRules1,sutimeRules2);

        Properties props = new Properties();
        props.setProperty("sutime.markTimeRanges", "true");
        props.setProperty("sutime.includeRange", "true");
        props.setProperty("restrictToTimex3", "false");
        props.setProperty("sutime.rules", allRules);
        props.setProperty("annotators", "tokenize,ssplit,pos,sutime");

        return props;
    }

}
