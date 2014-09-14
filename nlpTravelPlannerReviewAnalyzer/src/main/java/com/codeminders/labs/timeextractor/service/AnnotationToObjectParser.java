package com.codeminders.labs.timeextractor.service;

import com.codeminders.labs.timeextractor.rules.BaseRule;

import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.utils.RulesFactory;

import edu.stanford.nlp.ling.tokensregex.MatchedExpression;

public class AnnotationToObjectParser {

	private RulesFactory factory;

	public AnnotationToObjectParser() {
		this.factory = new RulesFactory();
	}

	public TemporalExtraction getTemporalExtraction(MatchedExpression expr,
			Object temporal) {
		String type = expr.getValue().getType();
		TemporalExtraction result = null;
		BaseRule extracted = factory.getBaseRule(temporal, type);
		if (extracted != null) {
			result = new TemporalExtraction();
			result.setClassOfRuleType(type);
			result.setTemporalExpression(expr.getText());
			result.setTemporal(extracted.getTemporal());
			result.setConfidence(extracted.getConfidence());
			result.setLocale(extracted.getLocale());
		}

		return result;
	}
}
