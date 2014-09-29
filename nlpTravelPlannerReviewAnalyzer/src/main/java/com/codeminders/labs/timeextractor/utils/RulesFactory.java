package com.codeminders.labs.timeextractor.utils;

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
import com.codeminders.labs.timeextractor.rules.date.MonthOfYear1;
import com.codeminders.labs.timeextractor.rules.date.TodayTomorrowEtc;
import com.codeminders.labs.timeextractor.rules.date.YearRule;
import com.codeminders.labs.timeextractor.rules.dateinterval.DateInterval2;
import com.codeminders.labs.timeextractor.rules.dateinterval.DateInterval3;
import com.codeminders.labs.timeextractor.rules.dateinterval.DateInterval4;
import com.codeminders.labs.timeextractor.rules.dateinterval.DayOfWeekIntervalRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.MidLateMonthRule;
import com.codeminders.labs.timeextractor.rules.dateinterval.MonthDaysIntervalRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.MonthToMonthRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.SeasonRules;
import com.codeminders.labs.timeextractor.rules.dateinterval.SeasonRules2;
import com.codeminders.labs.timeextractor.rules.dateinterval.WeekDay;
import com.codeminders.labs.timeextractor.rules.dateinterval.WeekEnd;
import com.codeminders.labs.timeextractor.rules.duration.DurationRule1;
import com.codeminders.labs.timeextractor.rules.duration.DurationRule2;
import com.codeminders.labs.timeextractor.rules.duration.DurationRule3;
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

// factory that returns object of concrete  rule class

public class RulesFactory {

    public Rule getBaseRule(Object obj, String name) {
        switch (name) {
        case ("YearRule"):
            return (YearRule) obj;
        case ("DayOfWeekRule1"):
            return (DayOfWeekRule1) obj;
        case ("DayOfWeekRule2"):
            return (DayOfWeekRule2) obj;
        case ("DayOfWeekRule3"):
            return (DayOfWeekRule3) obj;
        case ("MonthDayYearRule1"):
            return (MonthDayYearRule1) obj;
        case ("MonthDayYearRule2"):
            return (MonthDayYearRule2) obj;
        case ("Holidays"):
            return (Holidays) obj;
        case ("DayOfWeekOrderRule1"):
            return (DayOfWeekOrderRule1) obj;
        case ("DayOfWeekOrderRule2"):
            return (DayOfWeekOrderRule2) obj;
        case ("DayOrderAndMonthRule1"):
            return (DayOrderAndMonthRule1) obj;
        case ("MonthAndDayRule1"):
            return (MonthAndDayRule1) obj;
        case ("MonthAndDayRule2"):
            return (MonthAndDayRule2) obj;
        case ("MonthAndDayRule4"):
            return (MonthAndDayRule4) obj;
        case ("MonthAndDayRule5"):
            return (MonthAndDayRule5) obj;
        case ("MonthAndDayRule6"):
            return (MonthAndDayRule6) obj;
        case ("MonthAndDayOrderRule1"):
            return (MonthAndDayOrderRule1) obj;
        case ("MonthAndYearRule1"):
            return (MonthAndYearRule1) obj;
        case ("MonthOfYear1"):
            return (MonthOfYear1) obj;
        case ("TodayTomorrowEtc"):
            return (TodayTomorrowEtc) obj;
        case ("FrequencyTime"):
            return (FrequencyTime) obj;
        case ("AllPeriod"):
            return (AllPeriod) obj;
        case ("EveryPeriod"):
            return (EveryPeriod) obj;
        case ("Time1Rule"):
            return (Time1Rule) obj;
        case ("Time2Rule"):
            return (Time2Rule) obj;
        case ("Time3Rule"):
            return (Time3Rule) obj;
        case ("Time4Rule"):
            return (Time4Rule) obj;
        case ("Time5Rule"):
            return (Time5Rule) obj;
        case ("Time6Rule"):
            return (Time6Rule) obj;
        case ("Time7Rule"):
            return (Time7Rule) obj;
        case ("Time8Rule"):
            return (Time8Rule) obj;
        case ("TimeZone"):
            return (TimeZone) obj;
        case ("TimeOfDayRule"):
            return (TimeOfDayRule) obj;
        case ("TimeIntervalRule2"):
            return (TimeIntervalRule2) obj;
        case ("TimeIntervalRule3"):
            return (TimeIntervalRule3) obj;
        case ("TimeIntervalRule4"):
            return (TimeIntervalRule4) obj;
        case ("TimeIntervalRule5"):
            return (TimeIntervalRule5) obj;
        case ("TimeIntervalRule6"):
            return (TimeIntervalRule6) obj;
        case ("TimeIntervalRule7"):
            return (TimeIntervalRule7) obj;
        case ("TimeIntervalRule8"):
            return (TimeIntervalRule8) obj;
        case ("TimeIntervalRule9"):
            return (TimeIntervalRule9) obj;
        case ("TimeIntervalRule10"):
            return (TimeIntervalRule10) obj;
        case ("WeekEnd"):
            return (WeekEnd) obj;
        case ("WeekDay"):
            return (WeekDay) obj;
        case ("SeasonRules"):
            return (SeasonRules) obj;
        case ("SeasonRules2"):
            return (SeasonRules2) obj;
        case ("MidLateMonthRule"):
            return (MidLateMonthRule) obj;
        case ("DayOfWeekIntervalRule1"):
            return (DayOfWeekIntervalRule1) obj;
        case ("MonthDaysIntervalRule1"):
            return (MonthDaysIntervalRule1) obj;
        case ("MonthToMonthRule1"):
            return (MonthToMonthRule1) obj;
        case ("DateInterval3"):
            return (DateInterval3) obj;
        case ("DateInterval2"):
            return (DateInterval2) obj;
        case ("DurationRule1"):
            return (DurationRule1) obj;
        case ("DurationRule2"):
            return (DurationRule2) obj;
        case ("DurationRule3"):
            return (DurationRule3) obj;
        case ("DateInterval4"):
            return (DateInterval4) obj;

        }
        return null;
    }

}
