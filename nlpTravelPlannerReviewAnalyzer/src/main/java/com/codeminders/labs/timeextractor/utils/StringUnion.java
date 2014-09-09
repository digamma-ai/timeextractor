package com.codeminders.labs.timeextractor.utils;

public class StringUnion {
	
	public static String sutimeMainRules(String... args){
		StringBuilder s = new StringBuilder();
		for(int i=0; i<args.length-1;i++){
			s.append(args[i]);
			s.append(",");
		}
		s.append(args[args.length-1]);
		return s.toString();
	}

}
