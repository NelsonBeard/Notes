package com.geekbrains.notes;

public class Note {
    private String headline;
    private String fullText;
    private String date;


    public Note(String headline, String fullText, String date) {
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
