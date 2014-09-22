package com.codeminders.labs.timeextractor.rest.entities;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

/* Class transforms annotation object to htmltemporal object */

public class Annotation2HtmlTemporalConversion {

	public List<HtmlTemporal> convert(TemporalExtraction annotation) {
		List<HtmlTemporal> htmlTemporals = new ArrayList<HtmlTemporal>();
		if (annotation == null || annotation.getTemporal() == null) {
			return null;
		}
		List<Temporal> annotations = annotation.getTemporal();
		for (Temporal temporal : annotations) {
			if (temporal == null) {
				continue;
			}
			if (temporal.getType() == Type.DURATION) {
				DurationHtml duration = new DurationHtml(temporal,
						annotation.getLocale(), annotation.getConfidence());
				htmlTemporals.add(duration);
			} else if (temporal.getType() == Type.SET) {
				SetHtml set = new SetHtml(temporal, annotation.getLocale(),
						annotation.getConfidence());
				htmlTemporals.add(set);
			} else if (temporal.getType() == Type.DATE_INTERVAL_TIME_INTERVAL) {
				// TODO:
			} else {
				TimeDateHtml timeDate = new TimeDateHtml(temporal,
						annotation.getLocale(), annotation.getConfidence());
				htmlTemporals.add(timeDate);
			}
		}
		return htmlTemporals;
	}

}
