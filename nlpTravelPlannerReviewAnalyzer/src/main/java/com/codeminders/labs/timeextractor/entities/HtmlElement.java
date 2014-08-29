package com.codeminders.labs.timeextractor.entities;

public class HtmlElement {

    private String fullTag;
    private String extractedText;
    private String tag;
    private String id;
    private String tagClass;
    private int textFrom;
    private int textTo;

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

    public String getTagClass() {
        return tagClass;
    }

    public void setTagClass(String tagClass) {
        this.tagClass = tagClass;
    }

    public int getTextFrom() {
        return textFrom;
    }

    public void setTextFrom(int textFrom) {
        this.textFrom = textFrom;
    }

    public int getTextTo() {
        return textTo;
    }

    public void setTextTo(int textTo) {
        this.textTo = textTo;
    }

    public String getFullTag() {
        return fullTag;
    }

    public void setFullTag(String fullTag) {
        this.fullTag = fullTag;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    @Override
    public String toString() {
        return "HtmlElement [fullTag=" + fullTag + ", extractedText=" + extractedText + ", tag=" + tag + ", id=" + id + ", tagClass=" + tagClass + ", textFrom=" + textFrom + ", textTo=" + textTo
                + "]";
    }

}
