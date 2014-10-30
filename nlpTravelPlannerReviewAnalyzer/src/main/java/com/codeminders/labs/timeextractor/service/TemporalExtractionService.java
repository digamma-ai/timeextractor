package com.codeminders.labs.timeextractor.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;

import com.codeminders.labs.timeextractor.dto.DTOTemporal;
import com.codeminders.labs.timeextractor.entities.AnnotationInterval;
import com.codeminders.labs.timeextractor.entities.AnnotationIntervalHtml;
import com.codeminders.labs.timeextractor.entities.BaseText;
import com.codeminders.labs.timeextractor.entities.HtmlElement;
import com.codeminders.labs.timeextractor.entities.RegexResult;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.entities.Settings;
import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.service.combine.CombineRulesService;
import com.codeminders.labs.timeextractor.service.convert.Convert2DTO;
import com.codeminders.labs.timeextractor.service.convert.ConvertHtmlToText;
import com.codeminders.labs.timeextractor.service.filter.FilterRulesService;
import com.codeminders.labs.timeextractor.service.process.ProcessRulesService;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

/**
 * <h1>TemporalExtractionService Class</h1> is used for finding time expressions
 * from texts and html pages. The work flow contains of several steps: finding
 * simple expressions by multiple regex rules, combining them, using extracted
 * time expression types, transforming relative ones according to relative date
 * and filtering.
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */
public class TemporalExtractionService {

    private ConvertHtmlToText htmlService;
    private Convert2DTO converter;
    private CombineRulesService combineRulesService;
    private ProcessRulesService processingService;
    private FilterRulesService filterService;
    private static MultipleExtractionService service = new MultipleExtractionService(null);
    private static final Logger logger = Logger.getLogger(TemporalExtractionService.class);

    public TemporalExtractionService() {
        htmlService = new ConvertHtmlToText();
        converter = new Convert2DTO();
        combineRulesService = new CombineRulesService();
        processingService = new ProcessRulesService();
        filterService = new FilterRulesService();
    }

    public Map<String, TreeSet<AnnotationIntervalHtml>> extractDatesAndTimeFromHtml(String html, Settings settings) {
        List<HtmlElement> htmlElements = htmlService.getElements(html);
        Map<HtmlElement, TreeSet<TemporalExtraction>> map = new HashMap<HtmlElement, TreeSet<TemporalExtraction>>();
        for (HtmlElement htmlElement : htmlElements) {
            TreeSet<TemporalExtraction> results = null;
            try {
                results = extractDatesAndTimeFromText(htmlElement.getExtractedText(), settings);
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

    public TreeSet<TemporalExtraction> extractDatesAndTimeFromText(String text, Settings settings) {
        // extract dates and times
        TreeSet<TemporalExtraction> temporals = extractDatesAndTimes(text, settings);
        // process relative date
        temporals = processingService.processRelativeDate(temporals, settings);
        // combine extracted elements
        temporals = combineRulesService.combinationRule(temporals, text);
        // provess days of week according to current date
        temporals = processingService.processRelativeDayOfWeek(temporals, settings);
        // process timezone
        temporals = processingService.changeExpressionsAccordingToUserTimeZoneAndCurrentDate(temporals, settings);
        // filter simple rules
        temporals = filterService.removeSimpleTemporals(new ArrayList<TemporalExtraction>(temporals), settings);
        return temporals;
    }

    /* Method extracts dates and times from text */

    private TreeSet<TemporalExtraction> extractDatesAndTimes(String text, Settings settings) {
        TreeSet<TemporalExtraction> temporals = new TreeSet<TemporalExtraction>();

        if (text == null) {
            return null;
        }
        List<RegexResult> results = service.getTemporals(text);
        for (RegexResult result : results) {
            Rule rule = result.getRule();
            if (rule == null) {
                continue;
            }
            TemporalExtraction temporal = new TemporalExtraction(result);
            if (rule.getType() != null && temporal.getTemporal() != null && temporal.getTemporal().get(0) != null) {
                temporal.getTemporal().get(0).setType(rule.getType());
            }
            temporals.add(temporal);
        }
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
                List<DTOTemporal> extracted = converter.convert(extraction);
                List<Temporal> extractions = extraction.getTemporal();
                AnnotationIntervalHtml interval = new AnnotationIntervalHtml(extraction, element);
                if (extractions != null) {
                    if (extractions.get(0) != null && extractions.get(0).getType() != null) {
                        Type type = converter.getGeneralType(extractions.get(0).getType());
                        interval.setTemporalType(type);
                    }
                }
                interval.setExtractedTemporal(extracted);
                list.add(interval);
            }
            resultMap.put(Integer.valueOf(count).toString(), list);
            count++;
        }
        return resultMap;
    }

    public Map<String, TreeSet<TemporalExtraction>> extractDatesAndTimeFromMultipleText(List<BaseText> baseTexts, Settings settings) {
        Map<String, TreeSet<TemporalExtraction>> extractions = new HashMap<>();
        for (BaseText text : baseTexts) {
            TreeSet<TemporalExtraction> extracted = extractDatesAndTimeFromText(text.getText(), settings);
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
            List<DTOTemporal> temporals = converter.convert(temporal);
            int from = temporal.getFromPosition();
            int to = temporal.getToPosition();
            Locale locale = temporal.getLocale();
            AnnotationInterval interval = new AnnotationInterval(from, to, locale, temporals);
            if (temporal.getTemporal() != null && temporal.getTemporal().get(0) != null && temporal.getTemporal().get(0).getType() != null) {
                Type type = converter.getGeneralType(temporal.getTemporal().get(0).getType());
                interval.setTemporalType(type);
            }
            intervals.add(interval);
        }
        return intervals;
    }

    public static void main(String[] args) throws Exception {
        TemporalExtractionService service = new TemporalExtractionService();
        String parserRule = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat sdf = new SimpleDateFormat(parserRule);
        Date dateStr = sdf.parse("2014-10-27T18:40:40.931Z");
        LocalDateTime localDate = new LocalDateTime(dateStr);
        Settings settings = new Settings(localDate, "0", null, 0);
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText("", settings);
        System.out.println(extracted);
    }
}
