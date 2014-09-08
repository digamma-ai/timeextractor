package com.codeminders.labs.timeextractor.service;

import com.codeminders.labs.timeextractor.rules.date.DayOfWeekOrderRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekOrderRule2;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule2;
import com.codeminders.labs.timeextractor.rules.date.DayOrderAndMonthRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule0;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule2;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule3;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule4;
import com.codeminders.labs.timeextractor.rules.date.MonthAndYearRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthOfYear1;
import com.codeminders.labs.timeextractor.rules.date.YearRule;
import com.codeminders.labs.timeextractor.rules.time.Time1Rule;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

import edu.stanford.nlp.ling.tokensregex.MatchedExpression;

public class AnnotationToObjectParser {

    public TemporalExtraction getTemporalExtraction(MatchedExpression expr, Object temporal) {
        String type = expr.getValue().getType();
        TemporalExtraction result = null;
        switch (type) {

        // 2009
        case ("YearRule"):
            YearRule extracted = (YearRule) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(extracted.getTemporal());
            break;

        // October
        case ("MonthOfYear1"):
            MonthOfYear1 rule1 = (MonthOfYear1) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule1.getTemporal());
            break;

        // October 2012
        case ("MonthAndYearRule1"):
            MonthAndYearRule1 rule2 = (MonthAndYearRule1) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule2.getTemporal());
            break;

        // July 14
        case ("MonthAndDayRule0"):
            MonthAndDayRule0 rule0 = (MonthAndDayRule0) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule0.getTemporal());
            break;

        // July 14th 2014
        case ("MonthAndDayRule1"):
            MonthAndDayRule1 rule3 = (MonthAndDayRule1) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule3.getTemporal());
            break;

        // 14 July
        case ("MonthAndDayRule2"):
            MonthAndDayRule2 rule4 = (MonthAndDayRule2) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule4.getTemporal());

            break;

        // Sunday
        case ("DayOfWeekRule1"):
            DayOfWeekRule1 rule5 = (DayOfWeekRule1) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule5.getTemporal());
            break;

        // the 20th of january
        case ("MonthAndDayRule3"):
            MonthAndDayRule3 rule6 = (MonthAndDayRule3) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule6.getTemporal());
            break;

        // Sunday 16
        case ("DayOfWeekRule2"):
            DayOfWeekRule2 rule7 = (DayOfWeekRule2) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule7.getTemporal());
            break;

        // Jan. 20 2014
        case ("MonthAndDayRule4"):
            MonthAndDayRule4 rule8 = (MonthAndDayRule4) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule8.getTemporal());
            break;

        // the first Tuesday (of the month)
        case ("DayOfWeekOrderRule1"):
            DayOfWeekOrderRule1 rule9 = (DayOfWeekOrderRule1) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule9.getTemporal());
            break;

        // the 1st Tuesday (of the month)
        case ("DayOfWeekOrderRule2"):
            DayOfWeekOrderRule2 rule10 = (DayOfWeekOrderRule2) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule10.getTemporal());
            break;

        // 7 pm
        case ("DayOrderAndMonthRule1"):
            DayOrderAndMonthRule1 rule11 = (DayOrderAndMonthRule1) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule11.getTemporal());
            break;

        // 7 pm, 11 am, etc.
        case ("Time1Rule"):
            Time1Rule rule12 = (Time1Rule) temporal;
            result = new TemporalExtraction();
            result.setClassOfRuleType(type);
            result.setTemporalExpression(expr.getText());
            result.setTemporal(rule12.getTemporal());
            break;
        }

        return result;
    }
}
