# timeextractor
Time Extractor NLP project - locate dates and times in text documents

## Introduction
The project was started and coded by [Digamma.ai](http://digamma.ai/). We decided to build time/date extraction library. 

The main goal is to highlight texts fragments that are anyhow related to time/date/period (exact date, time of day, day of the week, months, seasons, time intervals, etc.) and make structural forms from them.  

## Installation
Clone the repository and create `.jar` file, with
```
git clone https://github.com/digamma-ai/timeextractor.git timeextractor
cd timeextractor
maven clean install
```
you will find in `target/` folder a jar named like `timeextractor.jar`.
## Dependencies
This library is built on:
* [joda-time Library for the Java date and time classes](https://github.com/JodaOrg/joda-time)
* [opencsv Parser Library for Java](http://opencsv.sourceforge.net/)
* [JUnit Testing Framework for Java](http://junit.org/junit5/)
* [Log4j Logging Service](https://logging.apache.org/log4j/2.x/)

## Quickstart
Class `DateTimeExtractor` is the main class for using Timeextractor. `DateTimeExtractor` is used by first constructing a DateTime Extractor instance and then invoking `extract()` method on it. `extract()` is convenience method to extract date/time fragments from input text.

`TemporalExtraction` class representing an element of extracted date/time fragments.  

Here is an example of how `DateTimeExtractor` and `TemporalExtraction` are used:
```
// input string
String inputText = "Reduced entrance fee after 16:30 except for Thursdays. Closed on Mondays.";
        
// extract date/times fragments
TreeSet<TemporalExtraction> result = DateTimeExtractor.extract(inputText);

// print extracted results
for (TemporalExtraction elem : result) {
     System.out.println(elem);
}
```

The output will be:
```
1 after 16:30, TimeIntervalRule3, [Temporal [type=TIME_INTERVAL, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=16, minutes=30, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=18, dayOfWeek=null, weekOfMonth=null]], endDate=null]], 21, 32

2 Thursdays, DayOfWeekRule1, [Temporal [type=DATE, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=17, minutes=23, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=19, dayOfWeek=TH, weekOfMonth=null]], endDate=TimeDate [time=Time [hours=17, minutes=23, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=19, dayOfWeek=TH, weekOfMonth=null]]]], 44, 54

3 Mondays, DayOfWeekRule1, [Temporal [type=DATE, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=17, minutes=23, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=23, dayOfWeek=MO, weekOfMonth=null]], endDate=TimeDate [time=Time [hours=17, minutes=23, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=23, dayOfWeek=MO, weekOfMonth=null]]]], 65, 73
```
## Advanced settings
You can modify default extraction settings for some specific scenarios, like:
* find closest day of week according to current date for relative date;
* find closest date according to current date for relative date;
* change found time expression according to specified date and timezone;
* filter extraction rules;
* find only dates that are current date or after current date.

A `Settings` can be applied to specify some additional extraction options, like setting local user date/time, time-zone offset, filtering extraction rules and finding latest dates.
`SettingsBuilder` is used for constructing `Settings` instance when you need to set configuration options other than the default. `SettingsBuilder` is best used by creating it, and then invoking its various configuration methods, and finally calling build.

The following is an example shows how to use the `SettingsBuilder` to construct a `Settings` instance:
```
Settings settings = new SettingsBuilder()
         .addRulesGroup("DateGroup")
         .excludeRules("holidaysRule")
         .addUserDate("2017-10-23T18:40:40.931Z")
         .addTimeZoneOffset("100")
         .includeOnlyLatestDates(true)
         .build();

```
