package com.codeminders.labs.timeextractor.rest.entities;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.Type;

/* Class transforms annotation object to htmltemporal object */

public class Annotation2DTOTemporalConversion {

	public List<DTOTemporal> convert(TemporalExtraction annotation) {
		List<DTOTemporal> htmlTemporals = new ArrayList<DTOTemporal>();
		if (annotation == null || annotation.getTemporal() == null) {
			return null;
		}
		List<Temporal> annotations = annotation.getTemporal();
		for (Temporal temporal : annotations) {
			if (temporal == null) {
				continue;
			}
			if (temporal.getType() == Type.DURATION) {
				DTODuration duration = new DTODuration(temporal);
				htmlTemporals.add(duration);
			} else if (temporal.getType() == Type.SET) {
				DTOSet set = new DTOSet(temporal);
				htmlTemporals.add(set);
			} else if (temporal.getType() == Type.DATE_INTERVAL_TIME_INTERVAL) {
				// TODO:
			} else {
				DTOTimeDate timeDate = new DTOTimeDate(temporal);
				htmlTemporals.add(timeDate);
			}
		}
		return htmlTemporals;
	}

	public Type getGeneralType(Type type) {
		if (type == null) {
			return null;
		} else if (type == Type.SET) {
			return Type.SET;
		} else if (type == Type.DATE_INTERVAL) {
			return Type.DATE_INTERVAL;
		} else if (type == Type.DURATION) {
			return Type.DURATION;
		} else {
			return Type.TIME_DATE;
		}

	}

}
