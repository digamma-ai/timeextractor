package com.codeminders.labs.timeextractor.rules.general;

import org.junit.Before;

import com.codeminders.labs.timeextractor.service.SUTimeService;

public class GeneralTest {
    protected SUTimeService service;

    @Before
    public void before() {
        service = new SUTimeService();
    }

}
