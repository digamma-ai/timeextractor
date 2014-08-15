import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.codeminders.labs.timeextractor.entities.HtmlElement;
import com.codeminders.labs.timeextractor.service.SUTimeService;

import edu.stanford.nlp.util.CoreMap;

public class GetHtmlText {

    public static ArrayList<HtmlElement> getElements(String html) {
        ArrayList<HtmlElement> elements = new ArrayList<HtmlElement>();
        Document document = Jsoup.parse(html);
        Elements htmlElements = document.body().select("*");
        for (Element element : htmlElements) {
            if (element.ownText().isEmpty()) {
                continue;
            }
            String text = element.ownText();
            String htmlElement = element.toString();
            int startIndex = html.indexOf(htmlElement);
            int endIndex = startIndex + htmlElement.length();

            if (startIndex != -1) {
                HtmlElement htmlEl = new HtmlElement();
                htmlEl.setHtmlElement(htmlElement);
                htmlEl.setText(text);
                htmlEl.setHtmlFrom(startIndex);
                htmlEl.setHtmlTo(endIndex);
                elements.add(htmlEl);
            }
        }
        return elements;
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://edition.cnn.com/").get();
        String htmlString = document.toString();
        ArrayList<HtmlElement> elements = getElements(htmlString);
        SUTimeService service = new SUTimeService();
        Map<HtmlElement, List<CoreMap>> extracted = service.extractDatesAndTimeFromHtml(elements);

        for (Map.Entry<HtmlElement, List<CoreMap>> entry : extracted.entrySet()) {
            System.out.println(entry.getKey().getHtmlElement() + "/" + entry.getValue());
            String noHTMLString = entry.getKey().getHtmlElement().replaceAll("\\<.*?\\>", "");
            System.out.println("No html: "+noHTMLString);
        }

    }
}
