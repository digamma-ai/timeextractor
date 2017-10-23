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
* [joda-time](https://github.com/JodaOrg/joda-time) Library for the Java date and time classes
* [opencsv](http://opencsv.sourceforge.net/) Parser Library 
* [JUnit](http://junit.org/junit5/) Testing Framework 
* [Log4j](https://logging.apache.org/log4j/2.x/) Logging Service
* [Gson](https://github.com/google/gson) Json Serialization/Deserialization library 

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

| **Method** | **Attributes** | **Description** |
| ----| --------- | ----- |
| `addRulesGroup()` |`String` rulesGroup | Adds extraction rules from `rulesGroup` group for extracting date/time fragments |
| `excludeRules()` | `String` ruleToExclude | Excludes extraction rule `ruleToExclude` from extracting rules |
| `addUserDate()` | `String` userDate | Changes found time expression according to specified user date <br> *correct format: "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"*|
| `addTimeZoneOffset()` | `String` timeZoneOffset | Changes found time expression according to specified user time-zone offset in minutes|
| `includeOnlyLatestDates()` | `boolean` includeOnlyLatest | Finds only dates that are current date or after current date |
| `build()` | | Creates a `Settings` instance based on the current configuration.

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

## Extraction rules
All extraction rules are divided into rules groups. 

<table>
  <tr>
          <td><b>Group</b></td>
          <td><b>Description</b></td>
          <td><b>Example</b></td>
  </tr>
  <tr>
    <td>DateGroup</td>
    <td colspan="2">Contains rules associated with the date</td>
  </tr>
  <tr>
          <td>dayOfWeekRule</td>
          <td>Extracts days of week fragments</td>
          <td><em>Come along to celebrate on <ins>Saturday 16</ins></em></td>
  </tr>
  <tr>
          <td>relativeDateRule</td>
          <td>Extracts relative dates fragments</td>
          <td><em>It was <ins>1 week ago</ins>.<br> Went there <ins>today</ins>.</em></td>
  </tr>
  <tr>
          <td>holidaysRule</td>
          <td>Extracts holidays dates fragments</td>
          <td><em>We will meet on <ins>Christmas</ins> day.</em></td>
  </tr>
   <tr>
          <td>monthDayRule</td>
          <td>Extracts month-day dates fragments</td>
          <td><em>The Snowy Day and the Art of Ezra Jack Keats (through <ins>January 29</ins>).</em></td>
  </tr>
           <tr>
          <td>monthYearDayRule</td>
          <td>Extracts year/month/day dates fragments</td>
          <td><em><ins>January 13-19, 2014</ins> Show Times".</em></td>
  </tr>
   </tr>
           <tr>
          <td>monthYearRule</td>
          <td>Extracts year/month dates fragments</td>
          <td><em>In <ins>March 2008</ins>, the Golden Gate Bridge District board approved a resolution to implement congestion pricing.</em></td>
  </tr>
</table>

