import java.util.List;

import com.codeminders.labs.timeextractor.service.SUTimeService;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.util.CoreMap;

public class Main {

    public static void main(String[] args) {

        SUTimeService service = new SUTimeService();

        String s = "Book tickets online in advance, its cheaper from 4.00 pm onwards. Great fun for all!";
        String date = "2013-07-14";
        List<CoreMap> timexAnnsAll = service.extractDatesAndTimeFromText(new String[] { s }, date);
        for (CoreMap cm : timexAnnsAll) {
            List<CoreLabel> tokens = cm.get(CoreAnnotations.TokensAnnotation.class);
            System.out.println(cm
                    + " [from char offset "
                    + tokens.get(0).get(CoreAnnotations.CharacterOffsetBeginAnnotation.class)
                    + " to "
                    + tokens.get(tokens.size() - 1).get(
                            CoreAnnotations.CharacterOffsetEndAnnotation.class) + ']' + " --> "
                    + cm.get(TimeExpression.Annotation.class).getTemporal());
        }
    }
}
