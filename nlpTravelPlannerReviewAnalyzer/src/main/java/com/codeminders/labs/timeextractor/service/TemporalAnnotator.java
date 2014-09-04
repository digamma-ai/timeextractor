package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtractionAnnotation;

import edu.stanford.nlp.ie.NumberNormalizer;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.tokensregex.CoreMapExpressionExtractor;
import edu.stanford.nlp.ling.tokensregex.MatchedExpression;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.time.GenericTimeExpressionPatterns;
import edu.stanford.nlp.time.Options;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.time.TimeExpressionPatterns;
import edu.stanford.nlp.util.CoreMap;

public class TemporalAnnotator implements Annotator {

	CoreMapExpressionExtractor<? extends MatchedExpression> expressionExtractor;
	TimeExpressionPatterns timexPatterns;

	public TemporalAnnotator(String name, Properties properties) {
		timexPatterns = new GenericTimeExpressionPatterns(new Options(name,
				properties));
		this.expressionExtractor = timexPatterns.createExtractor();
	}

	@Override
	public void annotate(Annotation annotation) {

		String docDate = annotation
				.get(CoreAnnotations.DocDateAnnotation.class);
		List<CoreMap> sentences = annotation
				.get(CoreAnnotations.SentencesAnnotation.class);
		List<TemporalExtraction> extractedExpressions = new ArrayList<TemporalExtraction>();
		List<CoreMap> mergedNumbers = NumberNormalizer
				.findAndMergeNumbers(annotation);
		annotation.set(CoreAnnotations.NumerizedTokensAnnotation.class,
				mergedNumbers);
		List<? extends MatchedExpression> matchedExpressions = expressionExtractor
				.extractExpressions(annotation);
		List<TimeExpression> timeExpressions = new ArrayList<TimeExpression>(
				matchedExpressions.size());

		for (MatchedExpression expr : matchedExpressions) {
			System.out.println(expr);

			String text = expr.getText();
			int from = expr.getCharOffsets().getBegin();
			int to = expr.getCharOffsets().getEnd();
			String classOfRule = expr.getValue().getType();

			TemporalExtraction extraction = new TemporalExtraction(text, from,
					to, classOfRule);
			extractedExpressions.add(extraction);
		}

		annotation
				.set(TemporalExtractionAnnotation.class, extractedExpressions);
	}

	@Override
	public Set<Requirement> requirementsSatisfied() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Requirement> requires() {
		// TODO Auto-generated method stub
		return null;
	}

}
