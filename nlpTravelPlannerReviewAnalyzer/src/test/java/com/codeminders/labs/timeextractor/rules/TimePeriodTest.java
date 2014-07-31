package com.codeminders.labs.timeextractor.rules;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TimePeriodTest {

    public static String rule = "((([1-9])|([1][0-2]?))(([p,P]([.])?[m,M]?[.]?)|([a,A][.]?[m,M]?[.]?))?[/s]*-[/s]*(([1-9])|([1][0-2]))[/s]?(([p,P][m,M])|([a,A][m,M])))";

    @Test
    public void test() {
        String text = "This time 10am-5pm";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String result = matcher.group();
            assertEquals("10am-5pm", result);
        }
    }

    @Test
    public void test2() {
        String text = "This time 10-5pm";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String result = matcher.group();
            assertEquals("10-5pm", result);
        }
    }

    @Test
    public void test3() {
        String text = "This time 11-12am";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String result = matcher.group();
            assertEquals("11-12am", result);
        }
    }

    @Test
    public void test4() {
        String text = "This time 1111-1231";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(text);
        assertEquals(false, matcher.find());
    }

    @Test
    public void test5() {
        String text = "This time 11-12";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(text);
        assertEquals(false, matcher.find());
    }

    @Test
    public void test6() {
        String text = "This time 10am-5pm";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String result = matcher.group();
            assertEquals("10am-5pm", result);
        }
    }

}
