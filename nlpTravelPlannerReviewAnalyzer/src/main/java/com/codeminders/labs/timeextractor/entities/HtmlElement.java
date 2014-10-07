package com.codeminders.labs.timeextractor.entities;

public class HtmlElement {

    private String extractedText;
    private String tag;
    private String id;
    private String temporalId;

    public HtmlElement() {

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public String getTemporalId() {
        return temporalId;
    }

    public void setTemporalId(String temporalId) {
        this.temporalId = temporalId;
    }

}
