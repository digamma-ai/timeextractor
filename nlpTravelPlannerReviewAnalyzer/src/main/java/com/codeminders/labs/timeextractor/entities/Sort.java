package com.codeminders.labs.timeextractor.entities;

public enum Sort {
	
	RECENT("recent"), POPULAR("popular");
	
	private String value;
		
	private Sort(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
