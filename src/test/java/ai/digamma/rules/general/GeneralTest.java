package ai.digamma.rules.general;

import ai.digamma.entities.TemporalExtraction;
import ai.digamma.utils.SettingsBuilder;
import org.junit.BeforeClass;

import ai.digamma.entities.Settings;

import java.util.TreeSet;

public class GeneralTest {
    protected static TreeSet<TemporalExtraction> extractor;
    protected static Settings settings;

    @BeforeClass
    public static void before() throws Exception {
        settings = new SettingsBuilder().build();
    }

}
