package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.codeminders.labs.timeextractor.entities.AnnotationInterval;
import com.codeminders.labs.timeextractor.entities.AnnotationIntervalHtml;
import com.codeminders.labs.timeextractor.entities.BaseText;
import com.codeminders.labs.timeextractor.entities.HtmlElement;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtractionAnnotation;
import com.codeminders.labs.timeextractor.utils.PipelineGenerator;
import com.codeminders.labs.timeextractor.utils.TextCleaner;
import com.codeminders.labs.timeextractor.utils.Utils;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;

/* Service class to extract temporal information from texts */

public class SUTimeService {

	private static TextCleaner textCleaner = new TextCleaner();
	private static AnnotationPipeline pipeline = PipelineGenerator
			.getPipeline();
	private static GetHtmlText htmlService = new GetHtmlText();

	/**
	 * Method returns List of annotated objects
	 * 
	 * @param text
	 *            String object - text to be annotated
	 * @param date
	 *            - date of text creation
	 * @return List<CoreMap> - List of annotated objects
	 */

	public List<TemporalExtraction> extractDatesAndTimeFromText(String text,
			String date) {

		// clean text from special symbols
		text = textCleaner.cleanText(text);

		// in case date wasn't set
		if (date == null) {
			date = Utils.currentLocalDate();
		}

		// annotators & rules included
		List<TemporalExtraction> allAnnotations = new ArrayList<TemporalExtraction>();
		Annotation annotation = new Annotation(text);
		annotation.set(CoreAnnotations.DocDateAnnotation.class, date);
		pipeline.annotate(annotation);
		List<TemporalExtraction> timexAnnsAll = annotation
				.get(TemporalExtractionAnnotation.class);
		allAnnotations.addAll(timexAnnsAll);
		return allAnnotations;
	}

	/**
	 * Method transforms List of annotated objects into List of
	 * AnnotaionInterval objects (objects, that contain information about start
	 * and end points of annotation, locale and level of confidence )
	 * 
	 * @param List
	 *            <CoreMap> list of extracted temporal information
	 * @return List<AnnotationInterval>
	 */

	public List<AnnotationInterval> getAllAnotations(
			List<TemporalExtraction> annotations) {
		List<AnnotationInterval> intervals = new ArrayList<AnnotationInterval>();
		if (annotations == null) {
			return intervals;
		}
		for (TemporalExtraction cm : annotations) {
			AnnotationInterval interval = new AnnotationInterval();
			int from = cm.getFromPosition();
			int to = cm.getToPosition();
			interval.setFrom(from);
			interval.setTo(to);
			Locale locale = new Locale.Builder().setLanguage("en")
					.setRegion("US").build();
			interval.setLocale(locale);
			intervals.add(interval);
		}
		return intervals;
	}

	/**
	 * Method extracts temporal information for multiple texts
	 * 
	 * @param List
	 *            <BaseText> baseTexts (BaseText object contains String id of
	 *            text, String text object itself and String of date of text
	 *            creation)
	 * @return Map<String, List<CoreMap>>
	 */

	public Map<String, List<TemporalExtraction>> extractDatesAndTimeFromText(
			List<BaseText> baseTexts) {
		Map<String, List<TemporalExtraction>> map = new HashMap<String, List<TemporalExtraction>>();
		for (BaseText baseText : baseTexts) {
			List<TemporalExtraction> results = extractDatesAndTimeFromText(
					baseText.getText(), baseText.getDate());
			map.put(baseText.getId(), results);
		}
		return map;
	}

	/**
	 * Method extracts temporal information for multiple texts
	 * 
	 * @param List
	 *            <BaseText> baseTexts (BaseText object contains String id of
	 *            text, String text object itself and String of date of text
	 *            creation)
	 * @return Map<String, List<CoreMap>>
	 */

	public Map<String, List<AnnotationIntervalHtml>> extractDatesAndTimeFromHtml(
			String html) {
		List<HtmlElement> htmlElements = htmlService.getElements(html);
		Map<HtmlElement, List<TemporalExtraction>> map = new HashMap<HtmlElement, List<TemporalExtraction>>();
		for (HtmlElement htmlElement : htmlElements) {
			List<TemporalExtraction> results = extractDatesAndTimeFromText(
					htmlElement.getExtractedText(), null);
			if (results.size() > 0) {
				map.put(htmlElement, results);
			}

		}
		Map<String, List<AnnotationIntervalHtml>> result = getAnnotationIntervalsForHtml(map);
		System.out.println(map.size());

		return result;
	}

	/**
	 * Method transfers Map<HtmlElement, List<CoreMap>> map of annotations into
	 * List<AnnotationIntervalHtml>
	 * 
	 * @return List<AnnotationIntervalHtml>
	 */
	private Map<String, List<AnnotationIntervalHtml>> getAnnotationIntervalsForHtml(
			Map<HtmlElement, List<TemporalExtraction>> map) {
		Map<String, List<AnnotationIntervalHtml>> resultMap = new HashMap<String, List<AnnotationIntervalHtml>>();
		int count = 1;
		for (Map.Entry<HtmlElement, List<TemporalExtraction>> entry : map
				.entrySet()) {
			List<AnnotationIntervalHtml> list = new ArrayList<AnnotationIntervalHtml>();
			HtmlElement element = entry.getKey();
			List<TemporalExtraction> annotations = entry.getValue();

			for (TemporalExtraction annotation : annotations) {

				AnnotationIntervalHtml interval = new AnnotationIntervalHtml();
				int from = element.getExtractedText().indexOf(
						annotation.toString());
				int to = from + annotation.toString().length();
				interval.setFrom(from);
				interval.setTo(to);
				interval.setExtractedText(annotation.toString());
				interval.setHtmlTagFrom(element.getTextFrom());
				interval.setHtmlTagTo(element.getTextTo());
				interval.setTag(element.getTag());
				list.add(interval);
			}

			resultMap.put(Integer.valueOf(count).toString(), list);
			count++;
		}
		return resultMap;
	}

	/**
	 * Method transforms Map of annotated objects into Map of AnnotaionInterval
	 * objects (by text id) (objects, that contain information about start and
	 * end points of annotation, locale and level of confidence )
	 * 
	 * @param Map
	 *            <String, List<CoreMap>> extractedTemporal map of extracted
	 *            temporal information by text ids
	 * @return Map<String, List<AnnotationInterval>> - map of id, annotated
	 *         information
	 */

	public Map<String, List<AnnotationInterval>> getAllAnnotations(
			Map<String, List<TemporalExtraction>> extractedTemporal) {
		Map<String, List<AnnotationInterval>> result = new HashMap<String, List<AnnotationInterval>>();
		for (String key : extractedTemporal.keySet()) {
			List<TemporalExtraction> annotated = extractedTemporal.get(key);
			List<AnnotationInterval> annotations = getAllAnotations(annotated);
			result.put(key, annotations);
		}
		return result;
	}

}
