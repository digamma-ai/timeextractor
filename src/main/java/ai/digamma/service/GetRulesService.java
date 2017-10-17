package ai.digamma.service;

import java.util.Map;
import java.util.TreeSet;

import ai.digamma.dto.DTORule;
import ai.digamma.entities.Rule;
import ai.digamma.service.convert.Convert2DTO;

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
