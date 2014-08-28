package com.codeminders.labs.timeextractor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.codeminders.labs.timeextractor.entities.AnnotationIntervalHtml;
import com.codeminders.labs.timeextractor.entities.HtmlElement;

public class GetHtmlText {

    public ArrayList<HtmlElement> getElements(String html) {

        ArrayList<HtmlElement> elements = new ArrayList<HtmlElement>();
        Document document = Jsoup.parse(html);
        Elements htmlElements = document.body().select("*");
        for (Element element : htmlElements) {
            if (element.ownText().isEmpty() || element.ownText().length() < 2 || element.toString().length() > 10000) {
                continue;
            }
            String text = element.ownText();
            System.out.println("element: " + element);
            try {
                String elementString = text.replace("(", "\\(").replace(")", "\\)").replace("{", "\\{").replace("?", "\\?").replace("}", "\\}").replace(".", "\\.").replace("|", "\\|")
                        .replace("*", "\\*").replaceAll(text, "\\s*(" + text + ")\\s*");
                System.out.println(elementString);
                Pattern p = Pattern.compile(elementString);
                Matcher m = p.matcher(html);
                while (m.find()) {
                    HtmlElement htmlEl = new HtmlElement();
                    htmlEl.setTag(element.tagName());
                    htmlEl.setTextFrom(m.start());
                    htmlEl.setTextTo(m.end());
                    htmlEl.setText(text);
                    elements.add(htmlEl);
                }
            } catch (PatternSyntaxException ex) {
                System.out.println(ex);
            }
        }

        return elements;
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://www.euronews.com/news/europe/").get();
        String htmlString = document.toString();
        SUTimeService service = new SUTimeService();
        List<AnnotationIntervalHtml> extractedDatesAndTimes = service.extractDatesAndTimeFromHtml(htmlString);
        System.out.println(extractedDatesAndTimes.size());

        System.out.println(extractedDatesAndTimes);

    }
}
