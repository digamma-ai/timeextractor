package com.codeminders.labs.timeextractor.rules;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class SummerHours {
    public static String rule = "summer hours";

    @Test
    public void test() {
        String text = "Summer hours, all visitors must leave by <text>7PM</text>, so no sunset";
        Pattern pattern = Pattern.compile(rule, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        assertEquals(true, matcher.find());
        while (matcher.find()) {
            String result = matcher.group();
            assertEquals("Summer hours", result);
        }
    }
}
