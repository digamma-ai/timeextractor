package com.codeminders.labs.timeextractor.exceptions;

public class ExceptionMessages {
    public static String FILLED_FIELEDS = "Missing parameter: At least one field (text or html) should be filled";
    public static String TIMEZONE = "Missing parameter 'timezone_offset': Should be properly field";
    public static String FIELD_RULES = "UUID's of rules should be well-formed";
    public static String DATE_RULES = "Date should be well-formed";
    public static String API_KEY_VALIDATION = "Your API Key is not valid";
    public static String API_KEY_VALIDATION_EMAIL = "Your API key is not valid as such email wasn't used for registration";
    public static String VALIDATION_EMAIL = "You need to provide 'email' parameter";
    public static String VALIDATION_KEY = "You need to provide API 'key' parameter";
    public static String REGISTRATION_ERROR = "For this email you have already received API key";
    public static String FILE_NOT_FOUND = "Server error. Registration file not found";
    public static String IO_EXCEPTION = "I/O Exception";

}
