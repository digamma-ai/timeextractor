package com.codeminders.labs.timeextractor.rules.composite;

import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;

import edu.stanford.nlp.util.CoreMap;

// 934 CET, September, 27, 2014; 934 CET, Sep 27 2014
public class CompositeTime4RuleMonthAndDayRule0 extends BaseRule {

	public CompositeTime4RuleMonthAndDayRule0(String Test) {
		System.out.println(Test);
	}

	public CompositeTime4RuleMonthAndDayRule0(ArrayList<CoreMap> time,
			ArrayList<CoreMap> date) {
		System.out.println(time + " " + date);
	}

	@Override
	public Type getType() {
		return Type.TIMEDATE;
	}

	@Override
	public List<Temporal> getTemporal() {
		// TODO Auto-generated method stub
		return null;
	}

}
