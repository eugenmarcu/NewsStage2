package com.example.android.newsstage2;

/**
 * Created by Eugen on 02-Mar-18.
 */


public class News {
    private String headline;
    private String url;
    private String date;
    private String category;
    private String trailText;
    private String author;

    public News(String headline, String url, String date, String category, String trailText, String author) {
        this.headline = headline;
        this.url = url;
        this.date = date;
        this.category = category;
        this.trailText = trailText;
        this.author = author;
    }

    public String getHeadline() {
        return headline;
    }

    public String getTrailText() {
        return trailText;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() { return author; }

}

