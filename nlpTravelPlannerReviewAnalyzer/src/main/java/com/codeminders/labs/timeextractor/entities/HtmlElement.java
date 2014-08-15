package com.codeminders.labs.timeextractor.entities;

public class HtmlElement {

    private String text;
    private String htmlElement;
    private int htmlFrom;
    private int htmlTo;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtmlElement() {
        return htmlElement;
    }

    public void setHtmlElement(String htmlElement) {
        this.htmlElement = htmlElement;
    }

    public int getHtmlFrom() {
        return htmlFrom;
    }

    public void setHtmlFrom(int htmlFrom) {
        this.htmlFrom = htmlFrom;
    }

    public int getHtmlTo() {
        return htmlTo;
    }

    public void setHtmlTo(int htmlTo) {
        this.htmlTo = htmlTo;
    }

    @Override
    public String toString() {
        return "HtmlElement [text=" + text + ", htmlElement=" + htmlElement + ", htmlFrom=" + htmlFrom + ", htmlTo=" + htmlTo + "]";
    }

}
