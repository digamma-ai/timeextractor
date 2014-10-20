package com.codeminders.labs.timeextractor.service;

import java.util.List;
import java.util.TreeSet;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Type;

public class FilterRulesService {

    public TreeSet<TemporalExtraction> removeSimpleTemporals(List<TemporalExtraction> list) {
        for (int i = 0; i < list.size(); i++) {
            TemporalExtraction current = list.get(i);
            List<Temporal> temporals = current.getTemporal();
            if (temporals != null && temporals.get(0) != null && temporals.get(0).getType() != null) {
                Type currentType = current.getTemporal().get(0).getType();
                if (currentType == Type.TIMEZONE || currentType == Type.EVERY || current.getClassOfRuleType().equals("MonthAndDayRule5")) {
                    list.remove(i);
                    i = i - 1;
                }
            }
        }
        return new TreeSet<TemporalExtraction>(list);
    }

}
