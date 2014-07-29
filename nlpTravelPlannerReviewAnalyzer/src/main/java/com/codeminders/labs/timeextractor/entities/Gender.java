package com.codeminders.labs.timeextractor.entities;


public enum Gender {
	
	NONE("none"), FEMALE("female"), MALE("male");
	
	private String value;
		
	private Gender(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static Gender getGender(String gender){
		 if (gender.equals("male")){
			 return Gender.MALE;
		 } 
		 
		 if (gender.equals("female")){
			 return Gender.FEMALE;
		 } 
		 return Gender.NONE;
	 }

		
}
