package com.codeminders.labs.timeextractor.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.HtmlElement;

public class GetHtmlTextTest {
    private GetHtmlText service;

    @Before
    public void before() {
        service = new GetHtmlText();
    }

    @Test
    public void getHtmlElements1() {

        String html = "<body>" + "<div>" + "test<div>test2<a>test5</a></div>" + "<div>test3</div> </div>";
        ArrayList<HtmlElement> htmlElements = service.getElements(html);
        assertEquals(4, htmlElements.size());
        assertEquals("test", htmlElements.get(0).getExtractedText());
    }

    @Test
    public void getHtmlElements2() {

        String html = "<body><div class=\"artDate\"><a title=\"reporter\" href=\"/programs/reporter/\">REPORTER</a>|05/09 11:02 CET</div></body>";
        ArrayList<HtmlElement> htmlElements = service.getElements(html);
        System.out.println(htmlElements);
        assertEquals(2, htmlElements.size());
    }
}
