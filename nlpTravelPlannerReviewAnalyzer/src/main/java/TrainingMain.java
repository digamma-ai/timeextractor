import java.io.IOException;
import java.util.List;

import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.parser.AnnotationToObjectParser;

public class TrainingMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SUTimeService service = new SUTimeService();

        String date = "2014-09-01";
        String toPredict = "the first tuesday of month";

        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, date);
        AnnotationToObjectParser parser = new AnnotationToObjectParser();
        for (TemporalExtraction cm : predicted) {
            List<Temporal> temporal = parser.getTemporal(cm);
            System.out.println(temporal);
            System.out.println("Text: " + cm.getTemporalExpression());
            System.out.println(cm.getFromPosition());
            System.out.println(cm.getToPosition());
            System.out.println("Class of rule: " + cm.getClassOfRuleType());
            System.out.println("----------------------------------");
        }
    }
}