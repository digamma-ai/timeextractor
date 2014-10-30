package com.codeminders.labs.timeextractor.entities;

public class LogData {

    private String apiKey;
    private String userEmail;
    private String utcDate;
    private String service;

    public LogData(String apiKey, String userEmail, String utcDate, String service) {
        this.apiKey = apiKey;
        this.userEmail = userEmail;
        this.utcDate = utcDate;
        this.service = service;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}
