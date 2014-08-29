package com.codeminders.labs.timeextractor.entities;

public class AnnotationIntervalHtml extends AnnotationInterval {

    private int htmlTagFrom;
    private int htmlTagTo;
    private String extractedText;
    private String tag;

    public int getHtmlTagFrom() {
        return htmlTagFrom;
    }

    public void setHtmlTagFrom(int htmlTagFrom) {
        this.htmlTagFrom = htmlTagFrom;
    }

    public int getHtmlTagTo() {
        return htmlTagTo;
    }

    public void setHtmlTagTo(int htmlTagTo) {
        this.htmlTagTo = htmlTagTo;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "AnnotationIntervalHtml [htmlTagFrom=" + htmlTagFrom + ", htmlTagTo=" + htmlTagTo + ", extractedText=" + extractedText + ", from=" + from + ", to=" + to + ", confidence=" + confidence
                + ", locale=" + locale + "]";
    }

}
