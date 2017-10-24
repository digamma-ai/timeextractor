package ai.digamma.service;

import java.text.SimpleDateFormat;
import java.util.*;

import ai.digamma.entities.ExtractionRule;
import ai.digamma.service.combine.CombineRulesService;
import ai.digamma.service.filter.FilterRulesService;
import ai.digamma.service.process.ProcessRulesService;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;

import ai.digamma.entities.RegexResult;
import ai.digamma.entities.Settings;
import ai.digamma.entities.TemporalExtraction;


/**
 * <h1>TemporalExtractionService Class</h1> is used for finding time expressions
 * from texts and html pages. The work flow contains of several steps: finding
 * simple expressions by multiple regex rules.txt, combining them, using extracted
 * time expression types, transforming relative ones according to relative date
 * and filtering.
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */
public class TemporalExtractionService {

    private CombineRulesService combineRulesService;
    private ProcessRulesService processingService;
    private FilterRulesService filterService;
    private static MultipleExtractionService service = new MultipleExtractionService(null);
    private static final Logger logger = Logger.getLogger(TemporalExtractionService.class);

    public TemporalExtractionService() {
        combineRulesService = new CombineRulesService();
        processingService = new ProcessRulesService();
        filterService = new FilterRulesService();
    }


    public TreeSet<TemporalExtraction> extractDatesAndTimeFromText (String text, Settings settings){
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
        // filter simple rules.txt
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
           //!!!!!!!!!!!!!!
            ExtractionRule rule = result.getRule();
            if (rule == null) {
                continue;
            }
            TemporalExtraction temporal = new TemporalExtraction(result);
            if (rule.getType() != null && temporal.getTemporal() != null && temporal.getTemporal().get(0) != null) {
                Map<String, String> ruleInfo = rule.getGroupAndRule();
                temporal.getTemporal().get(0).setGroup(ruleInfo.get("group"));
                temporal.getTemporal().get(0).setRule(ruleInfo.get("rule"));
            }
            temporals.add(temporal);
        }
        return temporals;
    }



   /* public static void main(String[] args) throws Exception {
        TemporalExtractionService service = new TemporalExtractionService();
        String parserRule = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat sdf = new SimpleDateFormat(parserRule);
        String s = "2014-10-27T18:40:40.931Z";
        Date dateStr = sdf.parse("2014-10-27T18:40:40.931Z");
        LocalDateTime localDate = new LocalDateTime(dateStr);
        Settings settings = new Settings(s, "0", null, 0);
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText("An 8-year-old may be very ", settings);
        System.out.println(extracted);
    }*/
}
