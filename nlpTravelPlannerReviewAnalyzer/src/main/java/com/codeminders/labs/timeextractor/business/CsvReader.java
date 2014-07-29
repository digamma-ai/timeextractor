package com.codeminders.labs.timeextractor.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.codeminders.labs.timeextractor.entities.Tip;

public class CsvReader {

    public List<Tip> getTipsFromFile(String csvFileLocation, String sep) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(
                new FileInputStream(csvFileLocation), "UTF-8"), ',', '"', 1);
        List<Tip> tips = new ArrayList<Tip>();
        try {

            String[] values = reader.readNext();
            while (values != null) {
                Tip tip = new Tip();
                tip.setVenueId(values[0]);
                tip.setCreatedAt(Long.parseLong(values[1]));
                tip.setTipText(values[2]);
                tips.add(tip);
                values = reader.readNext();
            }
        } finally {
            reader.close();
        }
        return tips;

    }

    public static void main(String[] args) throws IOException {

        CsvReader reader = new CsvReader();
        String csv = CsvReader.class.getResource("/training.csv").getPath();
        List<Tip> tips = reader.getTipsFromFile(csv, ",");
        System.out.println(tips);
    }

}
