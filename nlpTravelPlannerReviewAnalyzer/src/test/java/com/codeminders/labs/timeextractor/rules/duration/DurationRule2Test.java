package com.codeminders.labs.timeextractor.rules.duration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entites.Type;

public class DurationRule2Test extends GeneralTest {

	@Test
	public void durationRule2Test1() {

		String toPredict = "three minutes";
		List<TemporalExtraction> predicted = service
				.extractDatesAndTimeFromText(toPredict, null);
		assertEquals("three minutes", predicted.get(0).getTemporalExpression());
		assertEquals(Type.DURATION, predicted.get(0).getTemporal().get(0)
				.getType());
		assertEquals(3, predicted.get(0).getTemporal().get(0).getDuration()
				.getMinutes());

	}

}
