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
You will find in `target/` folder a jar named like `timeextractor.jar`.
## Dependencies
This library is built on:
* [joda-time](https://github.com/JodaOrg/joda-time) Library for the Java date and time classes
* [opencsv](http://opencsv.sourceforge.net/) Parser Library 
* [JUnit](http://junit.org/junit5/) Testing Framework 
* [Log4j](https://logging.apache.org/log4j/2.x/) Logging Service
* [Gson](https://github.com/google/gson) Json Serialization/Deserialization library 

## Quickstart
Class `DateTimeExtractor` is the main class for using Timeextractor. `DateTimeExtractor` is used by first constructing a `DateTimeExtractor` instance and then invoking `extract()` method on it. `extract()` is convenience method to extract date/time fragments from input text.

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
1 after 16:30, [Temporal[type=TIME_INTERVAL, group=TimeGroup, rule=timeIntervalRule, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=16, minutes=30, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=24, dayOfWeek=null, weekOfMonth=null]], endDate=null]], 21, 32

2 Thursdays, [Temporal[type=DATE, group=DateGroup, rule=dayOfWeekRule, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=18, minutes=59, seconds=43, timezoneOffset=0], date=Date [year=2017, month=10, day=24, dayOfWeek=TH, weekOfMonth=null]], endDate=TimeDate [time=Time [hours=18, minutes=59, seconds=43, timezoneOffset=0], date=Date [year=2017, month=10, day=24, dayOfWeek=TH, weekOfMonth=null]]]], 44, 54

3 Mondays, [Temporal[type=DATE, group=DateGroup, rule=dayOfWeekRule, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=18, minutes=59, seconds=43, timezoneOffset=0], date=Date [year=2017, month=10, day=24, dayOfWeek=MO, weekOfMonth=null]], endDate=TimeDate [time=Time [hours=18, minutes=59, seconds=43, timezoneOffset=0], date=Date [year=2017, month=10, day=24, dayOfWeek=MO, weekOfMonth=null]]]], 65, 73
```
## Output Description
The ouptut of the extraction process will be `TreeSet` of `TemporalExtraction` class. This class has next attributes:

| **Attributes** | **Description** |
| ---- | ----- |
| `String` temporalExpression | founded date/time fragment |
| `Temporal` temporal | represents date/time fragment's details |

`Temporal` class attributes:

| **Attributes** | **Description** |
| ---- | ----- |
| `String` type | type of founded date/time fragment (date, time, relative date, etc.)|
| `String` group | used group of rules for extracting current date/time fragment |
| `String` rule | used rule for extracting current date/time fragment |
| `Duration` duration | duration of extracting date/time fragment |
| `DurationInterval` temporal | duration interval of extracting date/time fragment |
| `Set` set | set of frequency, interval and days of repetiotion properties |
| `TimeDate` startDate | info about start date of extracting date/time fragment |
| `TimeDate` endDate | info about end date of extracting date/time fragment |

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
## Working with CSV file
For extracting date/time fragments from CSV files, you should invoke `extractFromCsv()` method on `DateTimeExtractor` instance. 
```
// "pathToCsv.csv" - csv file path
// "," - csv file separator
// "outputPath.txt" - output file path

Settings settings = new SettingsBuilder().build();
TreeSet<TemporalExtraction> result = DateTimeExtractor.extractFromCsv("pathToCsv.csv", ",", "outputPath.txt", settings);
```
## Extraction rules
All extraction rules are divided into rules groups. 
<table>
  <tr>
          <td style="font-size: 75px;"><b>Group</b></td>
          <td><b>Description</b></td>
          <td><b>Example</b></td>
  </tr>
  <tr>
          <td><b>DateGroup</b></td>
    <td colspan="2"><b>Contains rules associated with the date</b></td>
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
  <tr>
          <td>yearRule</td>
          <td>Extracts year dates fragments</td>
          <td><em><ins>2013</ins> is also the 850th anniversary of Notre-Dame.</em></td>
  </tr>
  <tr>
    <td><b>DateIntervalGroup</b></td>
    <td colspan="2"><b>Contains rules associated with the period between two dates</b></td>
  </tr>
   <tr>
          <td>dateIntervalRule</td>
          <td>Extracts intervals between dates</td>
          <td><em>$3 off general admission with your uberX receipt from <ins>10/16/13 - 10/18/13</ins>!<br>Best time to visit is <ins>from Tuesday to Thursday</ins>.<br>In main season (<ins>May - Sep</ins> ) the boat leaves <text>daily</text> exc.</em></td>
  </tr>
   <tr>
    <td><b>DurationGroup</b></td>
    <td colspan="2"><b>Contains rules associated with the period of time.</b></td>
  </tr>
  <tr>
    <td>intervalDurationRule</td>
    <td>Extracts duration intervals</td>
        <td><em>It's acceptable to include <ins>10 - 15 years</ins> of experience. </em></td>
  </tr>
    <tr>
    <td>durationRule</td>
    <td>Extracts periods of time</td>
        <td><em>Buy a combined ticket it <ins>lasts two days</ins><br>Was told that the <ins>last 30min</ins> before closing is free.</em></td>
  </tr>
     <tr>
    <td><b>RepeatedGroup</b></td>
    <td colspan="2"><b>Contains rules associated with repeated events.</b></td>
  </tr>
      <tr>
    <td>repeatedRule</td>
    <td>Extracts repeated events</td>
        <td><em>Free organ show <ins>every Sunday at 4</ins>.<br>Try San Francisco City Guides, who offer free <ins> weekly </ins> tours</em></td>
  </tr>
   <tr>
    <td><b>SeasonGroup</b></td>
    <td colspan="2"><b>Contains rules associated with seasons of the year.</b></td>
  </tr>
      <tr>
    <td>seasonRule</td>
    <td>Extracts seasons of the year</td>
        <td><em><ins>In summer months</ins> , the park is an anti-urban oasis along the riverfront.<br>Catch the post-impressionist exhibit <ins>in the fall</ins>!</em></td>
  </tr>
     <tr>
    <td><b>TimeGroup</b></td>
    <td colspan="2"><b>Contains rules associated with the time.</b></td>
  </tr>
      <tr>
    <td>timeRule</td>
    <td>Extracts the time</td>
        <td><em>Go <ins>before 4pm PST</ins> and get there in time for the Tower.<br>The 'Long Walk' on route to the races <ins>at about 1.30pm</ins></em></td>
  </tr>
   <tr>
    <td>timeIntervalRule</td>
    <td>Extracts time intervals</td>
        <td><em>Happy hour <ins>from 19 till 20</ins> !!<br>Best between <ins>2:00 pm and 4:00 pm</ins> to enjoy the sun</em></td>
  </tr>
  <tr>
    <td>timeZoneRule</td>
    <td>Extracts time zones</td>
        <td><em>Closed <ins>by 21:00CET</ins>.<br>Last entry <ins>04:15 UTC</ins> </em></td>
  </tr>
  <td><b>WeekendGroup</b></td>
    <td colspan="2"><b>Contains rules associated with weekends</b></td>
  </tr>
      <tr>
    <td>weekendRule</td>
    <td>Extracts seasons of the year</td>
        <td><em><ins>Weekend</ins> happy hour 11am-7pm</em></td>
  </tr>
</table>

