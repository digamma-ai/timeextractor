package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.codeminders.labs.timeextractor.entities.AnnotationInterval;
import com.codeminders.labs.timeextractor.entities.BaseTexts;
import com.codeminders.labs.timeextractor.utils.PipelineGenerator;
import com.codeminders.labs.timeextractor.utils.TextCleaner;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.util.CoreMap;

/* Service class to extract temporal information from texts */

public class SUTimeService {

	private TextCleaner textCleaner;
	private AnnotationPipeline pipeline;

	public SUTimeService() {
		textCleaner = new TextCleaner();
		pipeline = PipelineGenerator.getPipeline();
	}

	/* Method returns annotated temporal expressions */

	public List<CoreMap> extractDatesAndTimeFromText(String text, String date) {

		// clear text
		text = textCleaner.cleanText(text);

		if (date == null) {
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
			date = localDate.toString(fmt);
		}

		// annotators & rules included

		List<CoreMap> allAnnotations = new ArrayList<CoreMap>();
		Annotation annotation = new Annotation(text);
		annotation.set(CoreAnnotations.DocDateAnnotation.class, date);
		pipeline.annotate(annotation);
		List<CoreMap> timexAnnsAll = annotation
				.get(TimeAnnotations.TimexAnnotations.class);
		allAnnotations.addAll(timexAnnsAll);
		return allAnnotations;
	}

	public List<AnnotationInterval> getAllAnotations(List<CoreMap> annotations) {
		List<AnnotationInterval> intervals = new ArrayList<AnnotationInterval>();
		for (CoreMap cm : annotations) {
			List<CoreLabel> tokens = cm
					.get(CoreAnnotations.TokensAnnotation.class);
			AnnotationInterval interval = new AnnotationInterval();
			int from = (tokens.get(0))
					.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class);
			int to = tokens.get(tokens.size() - 1).get(
					CoreAnnotations.CharacterOffsetEndAnnotation.class);
			interval.setFrom(from);
			interval.setTo(to);
			Locale locale = new Locale.Builder().setLanguage("en")
					.setRegion("US").build();
			interval.setLocale(locale);
			intervals.add(interval);
		}
		return intervals;
	}

	public Map<String, List<CoreMap>> extractDatesAndTimeFromText(
			List<BaseTexts> baseTexts) {
		Map<String, List<CoreMap>> map = new HashMap<String, List<CoreMap>>();
		for (BaseTexts baseText : baseTexts) {
			List<CoreMap> resuts = extractDatesAndTimeFromText(
					baseText.getText(), baseText.getDate());
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
