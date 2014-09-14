package com.codeminders.labs.timeextractor.utils;

import java.util.Properties;

import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.service.TemporalAnnotator;

import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.PTBTokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;

public class PipelineGenerator {

	private static final String BASE_RULES = "/base.rules.txt";
	private static final String MAIN_RULES = "/additional.rules.txt";
	private static final String HOLIDAY_RULES = "/holidays.txt";
	private static final String DURATION_RULES = "/duration.txt";
	private static final String DEFS = "/defs.sutime.txt";

	private static AnnotationPipeline pipeline = new AnnotationPipeline();

	static {

		pipeline.addAnnotator(new PTBTokenizerAnnotator(false));
		pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
		pipeline.addAnnotator(new POSTaggerAnnotator(false));
		Properties props = getProperties();
		pipeline.addAnnotator(new TemporalAnnotator("sutime", props));
	}

	public static AnnotationPipeline getPipeline() {
		return pipeline;
	}

	private static Properties getProperties() {

		// String customRules =
		// SUTimeService.class.getResource(MAIN_RULES).getPath();
		String baseRules = SUTimeService.class.getResource(BASE_RULES)
				.getPath();
		String defs = SUTimeService.class.getResource(DEFS).getPath();
		String holidays = SUTimeService.class.getResource(HOLIDAY_RULES)
				.getPath();
		String durationRules = SUTimeService.class.getResource(DURATION_RULES)
				.getPath();

		String allRules = StringUnion.sutimeMainRules(defs, baseRules,
				durationRules, holidays);

		Properties props = new Properties();
		props.setProperty("sutime.rules", allRules);
		props.setProperty("annotators", "lemma, pos, tokenize");
		return props;
	}

}
