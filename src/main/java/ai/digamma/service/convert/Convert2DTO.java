package ai.digamma.service.convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import ai.digamma.entities.Rule;
import ai.digamma.temporal.entities.Temporal;
import ai.digamma.temporal.entities.Type;
import ai.digamma.dto.DTODuration;
import ai.digamma.dto.DTODurationInterval;
import ai.digamma.dto.DTORule;
import ai.digamma.dto.DTOSet;
import ai.digamma.dto.DTOTemporal;
import ai.digamma.dto.DTOTimeDate;
import ai.digamma.entities.TemporalExtraction;

/**
 * <h1>Convert Annotation objects to DTOTemporal Objects Class</h1> is used for
 * convering found time expression objects to DTO objects to use further in REST
 * service
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */

public class Convert2DTO {

    public List<DTOTemporal> convert(TemporalExtraction temporals) {
        List<DTOTemporal> htmlTemporals = new ArrayList<DTOTemporal>();
        if (temporals == null || temporals.getTemporal() == null) {
            return null;
        }
        List<Temporal> annotations = temporals.getTemporal();
        for (Temporal temporal : annotations) {
            if (temporal == null) {
                continue;
            }
            if (temporal.getType() == Type.DURATION) {
                DTODuration duration = new DTODuration(temporal);
                htmlTemporals.add(duration);
            } else if (temporal.getType() == Type.SET || temporal.getType() == Type.DAY_OF_WEEK_SET || temporal.getType() == Type.DAY_OF_WEEK_WEEK_OF_MONTH_SET) {
                DTOSet set = new DTOSet(temporal);
                htmlTemporals.add(set);
            }

            else if (temporal.getType() == Type.DURATION_INTERVAL) {
                DTODurationInterval interval = new DTODurationInterval(temporal);
                htmlTemporals.add(interval);
            } else {
                DTOTimeDate timeDate = new DTOTimeDate(temporal);
                htmlTemporals.add(timeDate);
            }
        }
        return htmlTemporals;
    }

    public Map<String, TreeSet<DTORule>> convertRulesToDto(TreeSet<Rule> rules) {
        Map<String, TreeSet<DTORule>> map = new HashMap<String, TreeSet<DTORule>>();
        TreeSet<String> rulePackages = new TreeSet<String>();
        Iterator<Rule> itr = rules.iterator();
        while (itr.hasNext()) {
            Rule rule = itr.next();
            DTORule dtoRule = new DTORule(rule);
            String packageName = rule.getClass().getPackage().getName();
            if (rulePackages.contains(packageName)) {
                TreeSet<DTORule> dtoRules = map.get(packageName);
                dtoRules.add(dtoRule);
            } else {
                TreeSet<DTORule> dtoRules = new TreeSet<DTORule>();
                dtoRules.add(dtoRule);
                rulePackages.add(packageName);
                map.put(packageName, dtoRules);
            }
        }
        return map;
    }

    public Type getGeneralType(Type type) {
        if (type == null) {
            return null;
        } else if (type == Type.SET) {
            return Type.SET;
        } else if (type == Type.DATE_INTERVAL) {
            return Type.DATE_INTERVAL;
        } else if (type == Type.DURATION) {
            return Type.DURATION;
        } else if (type == Type.PERIODIC) {
            return Type.PERIODIC;
        } else {
            return Type.TIME_DATE;
        }

    }

}
