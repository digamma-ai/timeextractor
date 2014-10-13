package com.codeminders.labs.timeextractor.rules.general;

import org.junit.BeforeClass;

import com.codeminders.labs.timeextractor.entities.Settings;
import com.codeminders.labs.timeextractor.service.TemporalExtractionService;

public class GeneralTest {
    protected static TemporalExtractionService service;
    protected static Settings settings;

    @BeforeClass
    public static void before() {
        service = new TemporalExtractionService();
        settings = new Settings(null, "0");
    }

}
