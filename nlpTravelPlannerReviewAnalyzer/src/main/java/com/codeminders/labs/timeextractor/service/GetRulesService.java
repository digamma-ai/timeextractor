package com.codeminders.labs.timeextractor.service;

import java.util.Map;
import java.util.TreeSet;

import com.codeminders.labs.timeextractor.dto.DTORule;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.service.convert.Convert2DTO;

/**
 * <h1>Get Rules Service Class</h1> is service class that provides all available
 * rules (that were loaded from specified file)
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */
public class GetRulesService {
    private Convert2DTO convertor;

    public GetRulesService() {
        convertor = new Convert2DTO();
    }

    public Map<String, TreeSet<DTORule>> getAllAvailableRules() {
        TreeSet<Rule> rules = MultipleExtractionService.getGenerator().getRules();
        return convertor.convertRulesToDto(rules);
    }

}
