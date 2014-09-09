package com.codeminders.labs.timeextractor.rules.general;

import org.junit.BeforeClass;

import com.codeminders.labs.timeextractor.service.SUTimeService;

public class GeneralTest {
    protected static SUTimeService service;

    @BeforeClass
    public static void before() {
        service = new SUTimeService();
    }

}
