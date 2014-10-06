package com.codeminders.labs.timeextractor.constants;

public class TemporalConstants {

    public static final String DAY_OF_WEEK = "(sunday|monday|tuesday|wednesday|thursday|friday|saturday)";
    public static final String DAY_OF_WEEK_EASY = "(sun|mon|tue|wed|thur|thu|fri|sat)";
    public static final String SEASON = "(fall|winter|summer|spring|autumn)";
    public static final String MONTH_OF_YEAR = "(january|february|march|april|may|june|july|august|september|october|november|december)";
    public static final String MONTH_OF_YEAR_EASY = "(jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)";
    public static final String HOLIDAYS = "((halloween)|(christmas eve)|(christmas day)|(christmas)|(new year's day)|(new year day)|(New Year s' Eve)|(New Year's Eve)|(new year)|(independence day)|(thanksgiving day)|(thanksgiving)|(Veterans Day)|(Columbus Day)|(Labor Day)|(Memorial Day)|(Washington's Birthday)|(Martin Luther King, Jr. Day)|(Martin Luther King Day)|(Inauguration Day)|((st[.]?|saint)[\\s]*(valentine|valentine's|valentines)[\\s]*(day)?))";
    public static final String BASIC_ORDER = "(first|second|third|fourth|fifth|sixth|seventh|eighth|ninth|tenth|eleventh|twelfth|thirteenth|fourteenth|fifteenth|sixteenth|seventeenth|eighteenth|nineteenth|twentieth|twenty-first|twenty-second|twenty-third|twenty-fourth|twenty-fifth|twenty-sixth|twenty-seventh|twenty-eights|twenty-ninth|thirtieth|thirty-first|fortieth|fiftieth|sixtieth|seventieth|eightieth|ninetieth|hundredth|last)";
    public static final String TIME_ZONE = "((PST|(Pacific Time))|BST|PT|EST|(EEST|(Eastern Europe Summer Time))|ET|(EDT|Eastern Daylight Time)|CST|(CET|Central European Time)|(CEST|Central European Summer Time)|BST|PDT|GMT|UTC|IST|MSD|MSK|WEST|WET|JST|IDT|HKT)";
    public static final String TIME_OF_DAY = "(morning|afternoon|evening|night|midday|noon|midnight|(lunch time))";
    public static final String DURATION = "(seconds|sec|minutes|minute|mins|min|mns|mn|hours|hour|hrs|hr|weeks|week|months|month|years|year|days|day|nights|night)";
    public static final String BASIC_NUMBER_ONE_TEN = "(one|two|three|four|five|six|sixth|seven|eight|nine|ten)";
    public static final String BASIC_NUMBER_ELEVEN_NINETEEN = "(eleven|twelve|thirteen|fourteen|fifteen|sixteen|seventeen|eighteen|nineteen)";
    public static final String BASIC_NUMBER_TWENTY_HUNDRED = "(twenty|thirty|forty|fifty|sixty|seventy|eighty|ninety|hundred)";

}
