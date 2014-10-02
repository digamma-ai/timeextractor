package com.codeminders.labs.timeextractor.service;

import java.util.Collections;
import java.util.TreeSet;

import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekOrderRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekOrderRule2;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule2;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule3;
import com.codeminders.labs.timeextractor.rules.date.DayOrderAndMonthRule1;
import com.codeminders.labs.timeextractor.rules.date.Holidays;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayOrderRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule2;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule4;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule5;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule6;
import com.codeminders.labs.timeextractor.rules.date.MonthAndYearRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthDayYearRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthDayYearRule2;
import com.codeminders.labs.timeextractor.rules.date.MonthDayYearRule3;
import com.codeminders.labs.timeextractor.rules.date.MonthOfYear1;
import com.codeminders.labs.timeextractor.rules.date.NTimeAgoRule;
import com.codeminders.labs.timeextractor.rules.date.NTimeAgoRule2;
import com.codeminders.labs.timeextractor.rules.date.TodayTomorrowEtc;
import com.codeminders.labs.timeextractor.rules.date.YearRule;
import com.codeminders.labs.timeextractor.rules.dateinterval.DateInterval2;
import com.codeminders.labs.timeextractor.rules.dateinterval.DateInterval3;
import com.codeminders.labs.timeextractor.rules.dateinterval.DateInterval4;
import com.codeminders.labs.timeextractor.rules.dateinterval.DayOfWeekIntervalRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.MidLateMonthRule;
import com.codeminders.labs.timeextractor.rules.dateinterval.MonthDaysIntervalRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.MonthToMonthInterval;
import com.codeminders.labs.timeextractor.rules.dateinterval.MonthToMonthRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.SeasonRules;
import com.codeminders.labs.timeextractor.rules.dateinterval.SeasonRules2;
import com.codeminders.labs.timeextractor.rules.dateinterval.WeekDay;
import com.codeminders.labs.timeextractor.rules.dateinterval.WeekEnd;
import com.codeminders.labs.timeextractor.rules.duration.DurationRule1;
import com.codeminders.labs.timeextractor.rules.duration.DurationRule2;
import com.codeminders.labs.timeextractor.rules.duration.DurationRule3;
import com.codeminders.labs.timeextractor.rules.duration.DurationRule4;
import com.codeminders.labs.timeextractor.rules.duration.DurationRule5;
import com.codeminders.labs.timeextractor.rules.frequency.FrequencyTime;
import com.codeminders.labs.timeextractor.rules.set.AllPeriod;
import com.codeminders.labs.timeextractor.rules.set.EveryPeriod;
import com.codeminders.labs.timeextractor.rules.time.Time1Rule;
import com.codeminders.labs.timeextractor.rules.time.Time2Rule;
import com.codeminders.labs.timeextractor.rules.time.Time3Rule;
import com.codeminders.labs.timeextractor.rules.time.Time4Rule;
import com.codeminders.labs.timeextractor.rules.time.Time5Rule;
import com.codeminders.labs.timeextractor.rules.time.Time6Rule;
import com.codeminders.labs.timeextractor.rules.time.Time7Rule;
import com.codeminders.labs.timeextractor.rules.time.Time8Rule;
import com.codeminders.labs.timeextractor.rules.time.TimeZone;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule10;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule2;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule3;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule4;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule5;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule6;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule7;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule8;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeIntervalRule9;
import com.codeminders.labs.timeextractor.rules.timeinterval.TimeOfDayRule;

public class MultiplePatternsGenerator {
    private String file;
    private TreeSet<Rule> rules;

    public MultiplePatternsGenerator(String file) {
        this.setFile(file);
        rules = getRulesFromFile();
    }

    public static TreeSet<Rule> getRulesFromFile() {
        TreeSet<Rule> rules = new TreeSet<Rule>(Collections.reverseOrder());
        Rule rule1 = new YearRule();
        Rule rule2 = new MonthDayYearRule1();
        Rule rule3 = new DayOfWeekRule1();
        Rule rule4 = new DayOfWeekRule2();
        Rule rule5 = new DayOfWeekRule3();
        Rule rule6 = new Holidays();
        Rule rule7 = new DayOfWeekOrderRule1();
        Rule rule8 = new DayOfWeekOrderRule2();
        Rule rule9 = new DayOrderAndMonthRule1();
        Rule rule10 = new MonthAndDayRule1();
        Rule rule11 = new MonthAndDayRule2();
        Rule rule13 = new MonthAndDayRule4();
        Rule rule14 = new MonthDayYearRule2();
        Rule rule15 = new MonthAndDayOrderRule1();
        Rule rule16 = new MonthAndYearRule1();
        Rule rule17 = new MonthOfYear1();
        Rule rule18 = new TodayTomorrowEtc();
        Rule rule19 = new FrequencyTime();
        Rule rule20 = new MonthAndDayRule5();
        Rule rule21 = new MonthAndDayRule6();
        Rule rule22 = new AllPeriod();
        Rule rule23 = new EveryPeriod();
        Rule rule24 = new Time1Rule();
        Rule rule25 = new Time2Rule();
        Rule rule26 = new Time3Rule();
        Rule rule27 = new Time4Rule();
        Rule rule28 = new Time5Rule();
        Rule rule29 = new TimeZone();
        Rule rule30 = new Time6Rule();
        Rule rule31 = new TimeOfDayRule();
        Rule rule32 = new TimeIntervalRule2();
        Rule rule33 = new TimeIntervalRule3();
        Rule rule34 = new TimeIntervalRule4();
        Rule rule35 = new TimeIntervalRule5();
        Rule rule36 = new TimeIntervalRule6();
        Rule rule37 = new TimeIntervalRule7();
        Rule rule38 = new TimeIntervalRule8();
        Rule rule39 = new TimeIntervalRule9();
        Rule rule40 = new WeekEnd();
        Rule rule41 = new WeekDay();
        Rule rule42 = new SeasonRules();
        Rule rule43 = new MidLateMonthRule();
        Rule rule44 = new SeasonRules2();
        Rule rule45 = new DayOfWeekIntervalRule1();
        Rule rule46 = new MonthDaysIntervalRule1();
        Rule rule47 = new MonthToMonthRule1();
        Rule rule48 = new DateInterval3();
        Rule rule49 = new DateInterval2();
        Rule rule50 = new DurationRule1();
        Rule rule51 = new DurationRule2();
        Rule rule52 = new DurationRule3();
        Rule rule53 = new DateInterval4();
        Rule rule54 = new Time7Rule();
        Rule rule55 = new TimeIntervalRule10();
        Rule rule56 = new Time8Rule();
        Rule rule57 = new MonthDayYearRule3();
        Rule rule58 = new DurationRule4();
        Rule rule59 = new DurationRule5();
        Rule rule60 = new NTimeAgoRule();
        Rule rule61 = new NTimeAgoRule2();
        Rule rule62 = new MonthToMonthInterval();

        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);
        rules.add(rule4);
        rules.add(rule5);
        rules.add(rule6);
        rules.add(rule7);
        rules.add(rule8);
        rules.add(rule9);
        rules.add(rule10);
        rules.add(rule11);
        rules.add(rule13);
        rules.add(rule14);
        rules.add(rule15);
        rules.add(rule16);
        rules.add(rule17);
        rules.add(rule18);
        rules.add(rule19);
        rules.add(rule20);
        rules.add(rule21);
        rules.add(rule22);
        rules.add(rule23);
        rules.add(rule24);
        rules.add(rule25);
        rules.add(rule26);
        rules.add(rule27);
        rules.add(rule28);
        rules.add(rule29);
        rules.add(rule30);
        rules.add(rule31);
        rules.add(rule32);
        rules.add(rule33);
        rules.add(rule34);
        rules.add(rule34);
        rules.add(rule35);
        rules.add(rule36);
        rules.add(rule37);
        rules.add(rule38);
        rules.add(rule39);
        rules.add(rule40);
        rules.add(rule41);
        rules.add(rule42);
        rules.add(rule43);
        rules.add(rule44);
        rules.add(rule45);
        rules.add(rule46);
        rules.add(rule47);
        rules.add(rule48);
        rules.add(rule49);
        rules.add(rule50);
        rules.add(rule51);
        rules.add(rule52);
        rules.add(rule53);
        rules.add(rule54);
        rules.add(rule55);
        rules.add(rule56);
        rules.add(rule57);
        rules.add(rule58);
        rules.add(rule59);
        rules.add(rule60);
        rules.add(rule61);
        rules.add(rule62);

        return rules;

    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public TreeSet<Rule> getRules() {
        return rules;
    }

    public void setRules(TreeSet<Rule> rules) {
        this.rules = rules;
    }

}
