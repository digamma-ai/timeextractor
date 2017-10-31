import ai.digamma.entities.Settings;
import ai.digamma.entities.TemporalExtraction;
import ai.digamma.service.DateTimeExtractor;
import ai.digamma.utils.SettingsBuilder;

import java.util.TreeSet;

public class Examples {

    // extract from string with default settings
    public static void extractFromText() throws Exception{
        String text = "Friday night music 6:30pm, lecture 7pm, cash bar";
        Settings settings = new SettingsBuilder()
                .build();
        TreeSet<TemporalExtraction> predicted = DateTimeExtractor.extract(text,settings);
        System.out.println(predicted);
    }

    // extract from string with custom settings
    public static void extractWithCustomSettings() throws Exception{
        String text = "Catch the post-impressionist exhibit after 19pm! Free organ show every Sunday at 4!";
        Settings settings = new SettingsBuilder()
                                .addRulesGroup("TimeGroup")
                                .excludeRules("timeRule")
                                .build();
        TreeSet<TemporalExtraction> predicted = DateTimeExtractor.extract(text,settings);
        System.out.println(predicted);
    }

    //extract from csv file
    public static void extractFromCsvFile() throws Exception{
        String pathToCsv = System.getProperty("user.dir") + "/data/train.csv";
        String outputPath = System.getProperty("user.dir") + "/examples/java/example_csv_output.txt";
        String csvSeparator = ",";
        Settings settings = new SettingsBuilder()
                .build();
        TreeSet<TemporalExtraction> predicted = DateTimeExtractor.extractFromCsv(pathToCsv, csvSeparator, outputPath, settings);
        for (TemporalExtraction temp : predicted){
            System.out.println(temp);
        }
    }

    //extract in JSON format
    public static void extractToJson() throws Exception{
        String text = "Monet Exhibition finishes September 2013 $26 ticket. 10am-5pm";
        Settings settings = new SettingsBuilder()
                .addTimeZoneOffset("100")
                .addUserDate("2017-10-20T18:40:40.931Z")
                .build();
        String predictedJson  = DateTimeExtractor.extractJSON(text, settings);
        System.out.println(predictedJson);
    }

    //extract from csv in JSON format
    public static void extractFromCsvToJson() throws Exception{
        String pathToCsv = System.getProperty("user.dir") + "/data/train.csv";
        String outputPath = System.getProperty("user.dir") + "/examples/java/example_csv_output.txt";
        String csvSeparator = ",";
        Settings settings = new SettingsBuilder()
                .build();
        String predictedJson = DateTimeExtractor.extractJSONFromCsv(pathToCsv, csvSeparator, outputPath, settings);
        System.out.println(predictedJson);
    }


    public static void main(String[] args) throws Exception {

        extractFromText();
        extractWithCustomSettings();
        extractFromCsvFile();
        extractToJson();
        extractFromCsvToJson();
    }
}
