package com.codeminders.labs.timeextractor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.codeminders.labs.timeextractor.dto.Annotation2DTOTemporalConversion;
import com.codeminders.labs.timeextractor.dto.DTOTemporal;
import com.codeminders.labs.timeextractor.entities.AnnotationInterval;
import com.codeminders.labs.timeextractor.entities.AnnotationIntervalHtml;
import com.codeminders.labs.timeextractor.entities.BaseText;
import com.codeminders.labs.timeextractor.entities.HtmlElement;
import com.codeminders.labs.timeextractor.entities.RegexResult;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.combine.CombineRules;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class TemporalExtractionService {

    private GetHtmlText htmlService = new GetHtmlText();
    private Annotation2DTOTemporalConversion converter = new Annotation2DTOTemporalConversion();
    private CombineRules combineRulesService = new CombineRules();
    private static MultipleExtractionService service = new MultipleExtractionService(null);
    private static final Logger logger = Logger.getLogger(TemporalExtractionService.class);

    public Map<String, TreeSet<AnnotationIntervalHtml>> extractDatesAndTimeFromHtml(String html) {
        List<HtmlElement> htmlElements = htmlService.getElements(html);
        Map<HtmlElement, TreeSet<TemporalExtraction>> map = new HashMap<HtmlElement, TreeSet<TemporalExtraction>>();
        for (HtmlElement htmlElement : htmlElements) {
            TreeSet<TemporalExtraction> results = null;
            try {
                results = extractDatesAndTimeFromText(htmlElement.getExtractedText());
            } catch (Exception ex) {
                logger.error("Sentence: " + htmlElement.getExtractedText() + " message: " + ex);
            }
            if (results != null && results.size() > 0) {
                map.put(htmlElement, results);
            }
        }

        Map<String, TreeSet<AnnotationIntervalHtml>> result = getAnnotationIntervalsForHtml(map);
        return result;
    }

    public TreeSet<TemporalExtraction> extractDatesAndTimeFromText(String text) {
        if (text == null) {
            return null;
        }
        TreeSet<TemporalExtraction> temporals = new TreeSet<TemporalExtraction>();
        List<RegexResult> results = service.getTemporals(text);
        for (RegexResult result : results) {
            Rule rule = result.getRule();
            TemporalExtraction temporal = new TemporalExtraction();
            temporal.setFromPosition(result.getStart());
            temporal.setToPosition(result.getEnd());
            if (rule != null) {
                temporal.setConfidence(rule.getConfidence());
                temporal.setLocale(rule.getLocale());
                temporal.setTemporal(rule.getTemporal(result.getText()));
                if (rule.getType() != null && temporal.getTemporal() != null && temporal.getTemporal().get(0) != null) {
                    temporal.getTemporal().get(0).setType(rule.getType());
                }

            }
            temporal.setTemporalExpression(result.getText());
            temporal.setClassOfRuleType(result.getRuleName());
            temporals.add(temporal);
        }

        // composite rules service
        temporals = combineRulesService.combinationRule(temporals, text);
        return temporals;
    }

    private Map<String, TreeSet<AnnotationIntervalHtml>> getAnnotationIntervalsForHtml(Map<HtmlElement, TreeSet<TemporalExtraction>> map) {
        Map<String, TreeSet<AnnotationIntervalHtml>> resultMap = new HashMap<String, TreeSet<AnnotationIntervalHtml>>();
        int count = 1;
        for (Map.Entry<HtmlElement, TreeSet<TemporalExtraction>> entry : map.entrySet()) {
            TreeSet<AnnotationIntervalHtml> list = new TreeSet<AnnotationIntervalHtml>();
            HtmlElement element = entry.getKey();
            TreeSet<TemporalExtraction> annotations = entry.getValue();

            for (TemporalExtraction extraction : annotations) {
                AnnotationIntervalHtml interval = new AnnotationIntervalHtml();
                String extractedText = (element.getExtractedText());

                int from = extractedText.indexOf(extraction.getTemporalExpression());
                if (from == -1) {
                    from = 0;
                }
                int to = from + extraction.getTemporalExpression().length();

                List<DTOTemporal> extracted = converter.convert(extraction);
                List<Temporal> extractions = extraction.getTemporal();
                if (extractions != null) {
                    if (extractions.get(0) != null && extractions.get(0).getType() != null) {
                        Type type = converter.getGeneralType(extractions.get(0).getType());
                        interval.setTemporalType(type);
                    }
                }

                interval.setFrom(from);
                interval.setTo(to);
                interval.setHtmlTagFrom(element.getTextFrom());
                interval.setHtmlTagTo(element.getTextTo());
                interval.setTag(element.getTag());
                interval.setExtractedTemporal(extracted);
                interval.setTo(to);
                interval.setConfidence(extraction.getConfidence());
                interval.setLocale(extraction.getLocale());
                list.add(interval);
            }

            resultMap.put(Integer.valueOf(count).toString(), list);
            count++;
        }
        return resultMap;
    }

    public Map<String, TreeSet<TemporalExtraction>> extractDatesAndTimeFromMultipleText(List<BaseText> baseTexts) {
        Map<String, TreeSet<TemporalExtraction>> extractions = new HashMap<>();
        for (BaseText text : baseTexts) {
            TreeSet<TemporalExtraction> extracted = extractDatesAndTimeFromText(text.getText());
            extractions.put(text.getId(), extracted);
        }
        return extractions;
    }

    public Map<String, TreeSet<AnnotationInterval>> getAllAnnotations(Map<String, TreeSet<TemporalExtraction>> extractedTemporal) {
        Map<String, TreeSet<AnnotationInterval>> result = new HashMap<String, TreeSet<AnnotationInterval>>();
        for (String key : extractedTemporal.keySet()) {
            TreeSet<TemporalExtraction> annotated = extractedTemporal.get(key);
            TreeSet<AnnotationInterval> annotations = getAllAnotations(annotated);
            result.put(key, annotations);
        }
        return result;
    }

    private TreeSet<AnnotationInterval> getAllAnotations(TreeSet<TemporalExtraction> annotated) {
        TreeSet<AnnotationInterval> intervals = new TreeSet<AnnotationInterval>();
        if (annotated == null) {
            return intervals;
        }
        for (TemporalExtraction temporal : annotated) {
            AnnotationInterval interval = new AnnotationInterval();
            List<DTOTemporal> temporals = converter.convert(temporal);
            int from = temporal.getFromPosition();
            int to = temporal.getToPosition();
            interval.setFrom(from);
            interval.setTo(to);
            Locale locale = temporal.getLocale();
            interval.setLocale(locale);
            interval.setExtractedTemporal(temporals);
            if (temporal.getTemporal() != null && temporal.getTemporal().get(0) != null && temporal.getTemporal().get(0).getType() != null) {
                Type type = converter.getGeneralType(temporal.getTemporal().get(0).getType());
                interval.setTemporalType(type);
            }
            intervals.add(interval);
        }
        return intervals;
    }

    public static void main(String[] args) {
        TemporalExtractionService service = new TemporalExtractionService();
        System.out.println(service.extractDatesAndTimeFromText("between morning and 4pm"));

    }
}
