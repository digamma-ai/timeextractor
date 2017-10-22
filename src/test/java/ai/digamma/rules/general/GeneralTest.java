package ai.digamma.rules.general;

import org.junit.BeforeClass;

import ai.digamma.entities.Settings;
import ai.digamma.service.TemporalExtractionService;

public class GeneralTest {
    protected static TemporalExtractionService service;
    protected static Settings settings;

    @BeforeClass
    public static void before() throws Exception {
       /* service = new TemporalExtractionService();
        settings = new Settings(null, "0", null, 0);*/
    }

}
