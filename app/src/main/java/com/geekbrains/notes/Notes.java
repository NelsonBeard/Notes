package com.geekbrains.notes;

public class Notes {
    private String headline;
    private String fullText;
    private String date;


    public Notes(String headline, String fullText, String date) {
        this.headline = headline;
        this.fullText = fullText;
        this.date = date;
    }

    public String getHeadline() {
        return headline;
    }

    public String getFullText() {
        return fullText;
    }

    public String getDate() {
        return date;
    }
}
