package com.codeminders.labs.timeextractor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
		document.outputSettings(new Document.OutputSettings()
				.prettyPrint(false));
		Elements htmlElements = document.body().select("*");
		for (Element element : htmlElements) {
			if (element.childNodes().size() > 1) {
				continue;
			}
			if (element.ownText().isEmpty() || element.ownText().length() < 2
					|| element.toString().length() > 10000) {
				continue;
			}
			String text = element.ownText();
			try {
				String elementString = element.toString();

				if (!html.contains(elementString)) {
					System.out.println("Element :" + element);
					System.out.println("Text :" + text);

				}
				if (html.contains(elementString)) {

					HtmlElement htmlEl = new HtmlElement();
					htmlEl.setTag(element.tagName());
					htmlEl.setTextFrom(html.indexOf(element.toString()));
					htmlEl.setTextTo(html.lastIndexOf(element.toString()));
					htmlEl.setText(text);
					elements.add(htmlEl);
				}

			} catch (PatternSyntaxException ex) {
				System.out.println("Exception: " + ex);
			}
		}

		return elements;
	}

	public static void main(String[] args) throws IOException {

		Document document = Jsoup.connect(
				"http://www.euronews.com/news/europe/").get();
		String htmlString = document.toString();
		SUTimeService service = new SUTimeService();
		List<AnnotationIntervalHtml> extractedDatesAndTimes = service
				.extractDatesAndTimeFromHtml(htmlString);
		System.out.println(extractedDatesAndTimes.size());

		System.out.println(extractedDatesAndTimes);

	}
}
