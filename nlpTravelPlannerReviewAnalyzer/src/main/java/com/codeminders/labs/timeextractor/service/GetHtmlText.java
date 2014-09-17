package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import com.codeminders.labs.timeextractor.entities.HtmlElement;

public class GetHtmlText {

    public GetHtmlText() {
    }

    public ArrayList<HtmlElement> getElements(String html) {

        ArrayList<HtmlElement> elements = new ArrayList<HtmlElement>();
        Document document = Jsoup.parse(html);

        Document.OutputSettings settings = document.outputSettings();
        settings.prettyPrint(false);
        settings.escapeMode(EscapeMode.xhtml);

        Elements htmlElements = document.body().select("*");
        for (Element element : htmlElements) {
            if (element.ownText().isEmpty()) {
                continue;
            }
            if (element.ownText().length() < 2 || element.toString().length() > 7000) {
                continue;
            }
            String text = element.text();

            try {
                String elementString = element.toString();

                if (html.contains(elementString)) {
                    HtmlElement htmlEl = new HtmlElement();
                    htmlEl.setTag(element.tagName());
                    int beginning = html.indexOf(elementString);
                    int end = beginning + elementString.length();
                    htmlEl.setTextFrom(beginning);
                    htmlEl.setTextTo(end);
                    htmlEl.setExtractedText(text);
                    htmlEl.setFullTag(element.toString());
                    htmlEl.setTag(element.tagName());
                    elements.add(htmlEl);
                }

            } catch (PatternSyntaxException ex) {
                System.out.println("Exception: " + ex);
            }
        }

        return elements;
    }

}
