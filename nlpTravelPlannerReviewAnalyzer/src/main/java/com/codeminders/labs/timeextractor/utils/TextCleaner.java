package com.codeminders.labs.timeextractor.utils;

public class TextCleaner {

	public String cleanText(String text) {
		if (text == null) {
			return text;
		}
		return text.replace("–", "-");
	}

}
