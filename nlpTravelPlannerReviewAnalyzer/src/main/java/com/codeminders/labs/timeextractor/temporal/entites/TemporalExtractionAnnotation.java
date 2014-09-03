package com.codeminders.labs.timeextractor.temporal.entites;

import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.util.ErasureUtils;

public class TemporalExtractionAnnotation implements
		CoreAnnotation<List<TemporalExtraction>> {
	public Class<List<TemporalExtraction>> getType() {
		return ErasureUtils.uncheckedCast(List.class);
	}
}