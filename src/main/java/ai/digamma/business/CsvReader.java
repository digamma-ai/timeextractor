package ai.digamma.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ai.digamma.exceptions.ExceptionMessages;
import au.com.bytecode.opencsv.CSVReader;

import ai.digamma.entities.Tip;
import org.apache.log4j.Logger;

public class CsvReader {
    private static final Logger logger = Logger.getLogger(CsvReader.class);
    public List<Tip> getTipsFromFile(String csvFileLocation, String sep) throws Exception{
        CSVReader reader = null;
        List<Tip> tips = new ArrayList<Tip>();
        try {
            FileInputStream fileStream = new FileInputStream(csvFileLocation);
            InputStreamReader streamReader = new InputStreamReader(fileStream, "UTF-8");
            reader = new CSVReader(streamReader, ',', '"', 1);
            String[] values = reader.readNext();
            while (values != null) {
                Tip tip = new Tip();
                tip.setVenueId(values[0]);
                tip.setCreatedAt(Long.parseLong(values[1]));
                tip.setTipText(values[2]);
                tips.add(tip);
                values = reader.readNext();

            }
        }
        catch (IOException ex){
            logger.error("File not found, provide the right file location");
            throw new Exception(ExceptionMessages.INPUT_FILE_NOT_FOUND);
        }
        reader.close();
        return tips;
    }

    public static void main(String[] args) throws Exception {

        CsvReader reader = new CsvReader();
        String csv = CsvReader.class.getResource("/training.csv").getPath();
        List<Tip> tips = reader.getTipsFromFile(csv, ",");
        System.out.println(tips);
    }
}
