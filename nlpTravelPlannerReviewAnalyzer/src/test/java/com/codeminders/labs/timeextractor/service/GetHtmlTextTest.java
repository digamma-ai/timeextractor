package com.codeminders.labs.timeextractor.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.HtmlElement;
import com.codeminders.labs.timeextractor.service.GetHtmlText;

public class GetHtmlTextTest {
    private GetHtmlText service;

    @Before
    public void before() {
        service = new GetHtmlText();
    }

    @Test
    public void getHtmlElements2() {

        String html = "<body><div class=\"artDate\"><a title=\"reporter\" href=\"/programs/reporter/\">REPORTER</a>|05/09 11:02 CET</div></body>";
        ArrayList<HtmlElement> htmlElements = service.getElements(html);
        assertEquals(2, htmlElements.size());
    }

}
