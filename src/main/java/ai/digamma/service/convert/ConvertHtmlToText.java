package ai.digamma.service.convert;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import ai.digamma.entities.HtmlElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import ai.digamma.constants.RestParameters;

/**
 * <h1>Convert Html To Text Class</h1> is designed for processing of html
 * document to text fragments from html document tags
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-30
 */

public class ConvertHtmlToText {

    private final boolean PRETTY_PRINT = false;
    private final EscapeMode ESCAPE_MODE = EscapeMode.xhtml;
    private final int MAX_HTML_STRING_LENGTH = 7000;
    private final int MIN_TEXT_LENGTH = 2;

    public ConvertHtmlToText() {
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

            if (element.toString().length() > MAX_HTML_STRING_LENGTH || element.ownText().length() < MIN_TEXT_LENGTH) {
                continue;
            }

            if (element.tagName().equals("em") || element.tagName().equals("i") || element.tagName().equals("b") || element.tagName().equals("font")) {
                continue;
            }

            String text = element.text();
            if (text.length() <= MIN_TEXT_LENGTH) {
                continue;
            }
            String temporalId = element.attr(RestParameters.TEMPORAL_ID);
            try {
                HtmlElement htmlEl = new HtmlElement();
                htmlEl.setExtractedText(text);
                htmlEl.setTemporalId(temporalId);
                elements.add(htmlEl);

            } catch (PatternSyntaxException ex) {
                System.out.println("Exception: " + ex);
            }
        }

        return elements;
    }

}
