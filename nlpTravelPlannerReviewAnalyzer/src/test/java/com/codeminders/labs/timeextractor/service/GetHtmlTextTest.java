package com.codeminders.labs.timeextractor.service;

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
        // System.out.println(htmlElements);
        // assertEquals(2, htmlElements.size());
    }

    @Test
    public void getHtmlElements3() {
        String html = "<span class=\"st\"><span class=\"f\">1 сент. 2010 г. - </span>THE <em>TWENTY</em>-<em>FOUR DAYS</em> BEFORE <em>CHRISTMAS</em> by Madeleine L'Engle ... for the holiday by doing one special thing each <em>day</em> of December.</span>";
        ArrayList<HtmlElement> htmlElements = service.getElements(html);
        // System.out.println(htmlElements);
        // assertEquals(2, htmlElements.size());

    }

    @Test
    public void getHtmlElements4() {
        String html = " <ul class=\"tabsSecondLevel clearAfter\"><li><a class=\"first  active\" href=\"#\" title=\"Today\">Today</a></li><li><a class=\"  \" href=\"#\" title=\"Yesterday\">Yesterday</a></li><li><a class=\"  \" href=\"#\" title=\"7 Days\">7 Days</a></li></ul>";
        ArrayList<HtmlElement> htmlElements = service.getElements(html);
        // System.out.println(htmlElements);
        // assertEquals(2, htmlElements.size());

    }

    @Test
    public void getHtmlElements5() {
        String html = "<li class=\"clearAfter fixedHeight\"><a class=\"imgLink leftFloat\" title=\"Italy: Teatro dell’Opera sacks orchestra and chorus\" href=\"/2014/10/04/italy-teatro-dell-opera-sacks-orchestra-and-chorus/\"><img src=\"http://static.euronews.com/articles/283410/130x73_283410.jpg?1412376602\" title=\"Italy: Teatro dell’Opera sacks orchestra and chorus\" alt=\"Italy: Teatro dell’Opera sacks orchestra and chorus\"></a><div class=\"titleWrap rightFloat\"><span class=\"artDate\">      ROME - 04/10 00:50 CET                                </span><h2 class=\"themeArtTitle\"><a href=\"/2014/10/04/italy-teatro-dell-opera-sacks-orchestra-and-chorus/\" title=\"Italy: Teatro dell’Opera sacks orchestra and chorus\">               Italy: Teatro dell’Opera sacks orchestra and chorus   </a></h2><p> In a first for Italy the Teatro dell’Opera in Rome has sacked its entire orchestra and chorus and will replace them with…</p> </div></li>";
        ArrayList<HtmlElement> htmlElements = service.getElements(html);
        System.out.println(htmlElements);
        // assertEquals(2, htmlElements.size());

    }
}
