import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeminders.labs.timeextractor.business.CsvReader;
import com.codeminders.labs.timeextractor.business.CsvWriter;
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
        CsvWriter writer = new CsvWriter();
        String fileToWrite = "C:/test/results.txt";

        int tp = 0;
        int fp = 0;
        int tn = 0;
        int fn = 0;

        List<Tip> tips = reader.getTipsFromFile(csv, ",");

        main: for (Tip tip : tips) {
            String patternString = "<text>(.*?)</text>";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(tip.getTipText());
            HashSet<String> annotated = new HashSet<String>();

            while (matcher.find()) {
                String result = matcher.group().replace("<text>", "").replace("</text>", "").replace("?", "-").replace("�", "-");
                annotated.add(result.trim());
            }
            String text = tip.getTipText().replace("<text>", "").replace("</text>", "").replace("?", "-").replace("�", "-");

            List<CoreMap> predicted = service.extractDatesAndTimeFromText(text, date);
            System.out.println(text);
            System.out.println(predicted);
            if (predicted.size() == 0 && annotated.size() == 0) {
                tn++;
                continue;
            }
            if (annotated.size() == 0 && predicted.size() != 0) {
                System.out.println("False positive " + predicted + " " + annotated);
                writer.writeToFile(fileToWrite, (text));
                writer.writeToFile(fileToWrite, "False positive " + predicted + " " + annotated);
                fp++;
                continue;
            }

            if (predicted.size() == 0 && annotated.size() != 0) {
                System.out.println("False negative " + predicted + " " + annotated);
                writer.writeToFile(fileToWrite, (text));
                writer.writeToFile(fileToWrite, "False negative " + predicted + " " + annotated);

                fn++;
                continue;

            }
            HashSet<String> predictions = new HashSet<String>();
            for (CoreMap cm : predicted) {
                predictions.add(cm.toString().trim());
            }

            for (String annotation : annotated) {
                if (!predictions.contains(annotation)) {
                    System.out.println(text);
                    System.out.println("False negative " + predictions + " " + annotated);
                    writer.writeToFile(fileToWrite, (text));
                    writer.writeToFile(fileToWrite, ("False negative " + predictions + " " + annotated));
                    fn++;
                }
            }

            for (String prediction : predictions) {
                if (!annotated.contains(prediction)) {
                    System.out.println(tip.getTipText());
                    System.out.println("False positive " + predictions + " " + annotated);
                    writer.writeToFile(fileToWrite, (text));
                    writer.writeToFile(fileToWrite, ("False positive " + predictions + " " + annotated));
                    fp++;
                }
            }

            tp++;
        }

        FScore score = new FScore();

        System.out.println("True positive " + tp);
        System.out.println("False positive " + fp);
        System.out.println("True negative " + tn);
        System.out.println("False negative " + fn);

        writer.writeToFile(fileToWrite, ("True positive " + tp));
        writer.writeToFile(fileToWrite, ("False positive " + fp));
        writer.writeToFile(fileToWrite, ("True negative " + tn));
        writer.writeToFile(fileToWrite, ("False negative " + fn));

        double accuracy = score.accuracy(tp, tn, fp, fn);
        double precision = score.precision(tp, fp);
        double recall = score.recall(tp, fn);
        double fScore = score.f1Score(precision, recall);

        System.out.println("F-Score: " + fScore);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
        System.out.println("Accuracy: " + accuracy);

        writer.writeToFile(fileToWrite, ("F-Score: " + fScore));
        writer.writeToFile(fileToWrite, ("Precision: " + precision));
        writer.writeToFile(fileToWrite, ("Recall: " + recall));
        writer.writeToFile(fileToWrite, ("Accuracy: " + accuracy));

    }
}
