import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.business.CsvReader;
import com.codeminders.labs.timeextractor.business.FScore;
import com.codeminders.labs.timeextractor.entities.Tip;
import com.codeminders.labs.timeextractor.service.SUTimeService;

import edu.stanford.nlp.util.CoreMap;

public class Main {

    public static void main(String[] args) throws IOException {

        SUTimeService service = new SUTimeService();
        CsvReader reader = new CsvReader();
        String csv = CsvReader.class.getResource("/training.csv").getPath();
        String date = "2013-07-14";
        int tp = 0;
        int fp = 0;
        int tn = 0;
        int fn = 0;

        List<Tip> tips = reader.getTipsFromFile(csv, ",");

        for (Tip tip : tips) {
            String patternString = "<text>(.*?)</text>";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(tip.getTipText());
            HashSet<String> annotated = new HashSet<String>();

            // using Matcher find(), group(), start() and end() methods
            while (matcher.find()) {
                String result = matcher.group().replace("<text>", "").replace("</text>", "");
                annotated.add(result.trim());
            }

            List<CoreMap> predicted = service.extractDatesAndTimeFromText(
                    new String[] { tip.getTipText() }, date);

            System.out.println(annotated + " " + predicted);
            if (predicted.size() == 0 && annotated.size() == 0) {
                tn++;
                continue;
            }
            if (annotated.size() == 0) {
                fn++;
                continue;
            }

            for (CoreMap cm : predicted) {
                if (!annotated.contains(cm.toString())) {
                    fp++;
                    continue;
                }
            }
            tp++;
        }

        FScore score = new FScore();
        double accuracy = score.accuracy(tp, tn, fp, fn);
        double precision = score.precision(tp, fp);
        double recall = score.recall(tp, fn);
        double fScore = score.f1Score(precision, recall);
        System.out.println("F-Score: " + fScore);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
        System.out.println("Accuracy: " + accuracy);

    }
}
