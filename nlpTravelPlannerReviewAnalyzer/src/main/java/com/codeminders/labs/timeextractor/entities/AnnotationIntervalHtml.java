package com.codeminders.labs.timeextractor.entities;

public class AnnotationIntervalHtml extends AnnotationInterval {

    private String tag;
    private String text;
    private int htmlTagFrom;
    private int htmlTagTo;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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

    @Override
    public String toString() {
        return "AnnotationIntervalHtml [tag=" + tag + ", text=" + text + ", htmlTagFrom=" + htmlTagFrom + ", htmlTagTo=" + htmlTagTo + ", from=" + from + ", to=" + to + ", confidence=" + confidence
                + ", locale=" + locale + "]";
    }

}
