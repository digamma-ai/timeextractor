import java.io.IOException;
import java.util.List;

import com.codeminders.labs.timeextractor.service.TemporalExtractionService;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class TrainingMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        TemporalExtractionService service = new TemporalExtractionService();

        String date = "2014-09-01";
        String toPredict = "Wed morning";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, date);
        for (TemporalExtraction cm : predicted) {
            System.out.println("Text: " + cm.getTemporalExpression());
            System.out.println("Class of rule: " + cm.getClassOfRuleType());
            System.out.println(cm.getTemporal());
            System.out.println(cm.getFromPosition());
            System.out.println(cm.getToPosition());
            System.out.println(cm.getConfidence());

            System.out.println("----------------------------------");
        }
    }
}