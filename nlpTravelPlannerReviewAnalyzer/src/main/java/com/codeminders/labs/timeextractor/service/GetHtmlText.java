package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import com.codeminders.labs.timeextractor.entities.HtmlElement;

/* */

public class GetHtmlText {

    private final boolean PRETTY_PRINT = false;
    private final EscapeMode ESCAPE_MODE = EscapeMode.xhtml;
    private final int MAX_HTML_STRING_LENGTH = 7000;
    private final int MIN_TEXT_LENGTH = 2;

    public GetHtmlText() {
    }

    public ArrayList<HtmlElement> getElements(String html) {

        ArrayList<HtmlElement> elements = new ArrayList<HtmlElement>();
        Document document = Jsoup.parse(html);
        Document.OutputSettings settings = document.outputSettings();
        settings.prettyPrint(PRETTY_PRINT);
        settings.escapeMode(ESCAPE_MODE);
        Elements htmlElements = document.body().select("*");
        for (int i = 0; i < htmlElements.size(); i++) {
            Element element = htmlElements.get(i);

            if (element.toString().length() > MAX_HTML_STRING_LENGTH || element.ownText().isEmpty()) {
                continue;
            }

            String text = element.text();
            if (text.length() <= MIN_TEXT_LENGTH) {
                continue;
            }
            try {
                String elementString = element.toString().replace("&apos;", "'").replace("&quot;", "\"");
                if (html.contains(elementString)) {
                    int beginning = html.indexOf(elementString);
                    int end = beginning + elementString.length();
                    String tagName = element.tagName();
                    HtmlElement htmlEl = new HtmlElement();
                    htmlEl.setTag(element.tagName());
                    htmlEl.setTextFrom(beginning);
                    htmlEl.setTextTo(end);
                    htmlEl.setExtractedText(text);
                    htmlEl.setFullTag(element.toString());
                    htmlEl.setTag(tagName);
                    elements.add(htmlEl);
                }

            } catch (PatternSyntaxException ex) {
                System.out.println("Exception: " + ex);
            }

        }

        return elements;
    }

    public static void main(String[] args) {
        String html = "<body><p class=\"cnn_storypgraphtxt cnn_storypgraph76\"><i><a href=\"http://www.restaurant-lemagret.com/\" target=\"_blank\">Le Magret</a></i><i>, Marche Victor Hugo, 31000 Toulouse; +33 (0)5 61 23 21 32; Tuesday-Friday 11.45 a.m.-2.30 p.m., Saturday-Sunday 11</i><i>.</i><i>45 a.m.-3 p.m.; moderate</i></p></body>";
        GetHtmlText service = new GetHtmlText();
        ArrayList<HtmlElement> htmlElements = service.getElements(html);
        for (HtmlElement element : htmlElements) {
            System.out.println("extracted: " + element.getExtractedText());
        }
    }

}
