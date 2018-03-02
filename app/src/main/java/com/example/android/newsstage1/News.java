package com.example.android.newsstage1;

/**
 * Created by Eugen on 02-Mar-18.
 */


public class News {
    private String mHeadline;
    private String mUrl;
    private String mDate;
    private String mCategory;
    private String mTrailText;

    public News(String headline, String url, String date, String category, String trailText) {
        mHeadline = headline;
        mUrl = url;
        mDate = date;
        mCategory = category;
        mTrailText = trailText;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public String getTrailText() {
        return mTrailText;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getDate() {
        return mDate;
    }

    public String getCategory() {
        return mCategory;
    }

}

