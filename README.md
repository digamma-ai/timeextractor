# timeextractor
Time Extractor NLP project - locate dates and times in text documents
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
In the following example, the string `inputText` will be used as the input. 
```
String inputText = "Reduced entrance fee after 16:30 except for Thursdays. Closed on Mondays.";

// finding time expression service
TemporalExtractionService service = new TemporalExtractionService();

// settings for specific extraction scenarios
// default values is used here
Settings settings = new Settings();

// extracting dates and time
TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText(inputText, settings);

// printing extracted results
for (TemporalExtraction elem : extracted) {
     System.out.println(elem);
}
```
The output will be:
```
1 after 16:30, TimeIntervalRule3, [Temporal [type=TIME_INTERVAL, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=16, minutes=30, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=18, dayOfWeek=null, weekOfMonth=null]], endDate=null]], 21, 32

2 Thursdays., DayOfWeekRule1, [Temporal [type=DATE, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=17, minutes=23, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=19, dayOfWeek=TH, weekOfMonth=null]], endDate=TimeDate [time=Time [hours=17, minutes=23, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=19, dayOfWeek=TH, weekOfMonth=null]]]], 44, 54

3 Mondays., DayOfWeekRule1, [Temporal [type=DATE, duration=null, durationInterval=null, set=null, startDate=TimeDate [time=Time [hours=17, minutes=23, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=23, dayOfWeek=MO, weekOfMonth=null]], endDate=TimeDate [time=Time [hours=17, minutes=23, seconds=0, timezoneOffset=0], date=Date [year=2017, month=10, day=23, dayOfWeek=MO, weekOfMonth=null]]]], 65, 73
```

